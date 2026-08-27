# DDD 限界上下文 + 域内 CQRS 架构设计

> 版本: 1.0
> 日期: 2026-08-25
> 适用: 自动化测试平台 JAVA 后端

---

## 一、架构总览

本项目采用 **"域间 DDD + 域内 CQRS"** 的混合架构：

- **域间（限界上下文之间）**：遵循 DDD（Domain-Driven Design），通过限界上下文划分业务域，域间通过防腐层（ACL）和领域事件交互
- **域内（单个限界上下文内部）**：遵循 CQRS（Command Query Responsibility Segregation），写操作走 Command 通道，读操作走 Query 通道，两者完全隔离

```
┌─────────────────────────────────────────────────────────────────────┐
│                        自动化测试平台                                   │
│                                                                       │
│  ┌──────────┐  领域事件   ┌──────────┐  领域事件   ┌──────────┐   │
│  │ 代码分析域 │ ─────────→ │ 测试管理域 │ ─────────→ │ 执行管控域 │   │
│  │CodeAnalysis│            │TestMgmt  │            │Execution │   │
│  └────┬─────┘            └────┬─────┘            └────┬─────┘   │
│       │ ACL                     │ ACL                     │ ACL     │
│       ▼                         ▼                         ▼         │
│  ┌─────────────────────────────────────────────────────────────┐    │
│  │                    系统管理域 (System)                        │    │
│  │         用户/角色/权限/环境/仓库/字典 (上游基础域)             │    │
│  └─────────────────────────────────────────────────────────────┘    │
│                                                                       │
│  ┌──────────┐  领域事件   ┌──────────┐                              │
│  │ 质量报表域 │ ─────────→ │ 智能体域  │                              │
│  │ Quality  │            │  Agent   │                              │
│  └──────────┘            └──────────┘                              │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 二、6 个限界上下文

| 序号 | 限界上下文 | 包名 | 核心职责 | 聚合根 |
|------|-----------|------|---------|--------|
| 1 | 系统管理域 | `system` | 用户、角色、权限、环境、仓库、字典 | `User`, `Role`, `Permission`, `Environment`, `Repository`, `DictType` |
| 2 | 代码分析域 | `codeanalysis` | Git 提交拉取、变更分析、影响范围识别 | `ChangeAnalysis`, `GitCommit` |
| 3 | 测试管理域 | `testmanagement` | 用例、步骤、版本、元素库、业务链路、标签、数据集 | `TestCase`, `BusinessLink`, `PageElement`, `TestDataSet` |
| 4 | 执行管控域 | `execution` | 任务编排、执行节点、用例执行实例、步骤结果 | `TestTask`, `TaskExecution`, `ExecNode` |
| 5 | 质量报表域 | `quality` | 测试报告、AI 根因、手动标记、缺陷记录、质量统计 | `TestReport`, `DefectRecord`, `QualityDailyStats` |
| 6 | 智能体域 | `agent` | 对话会话、长期记忆、工具调用记录 | `AgentConversation`, `AgentMemory` |

---

## 三、上下文映射（域间交互）

### 3.1 交互关系总览

| 上游 | 下游 | 交互内容 | 模式 |
|------|------|---------|------|
| 系统管理域 | 代码分析域 | `repo_id` 仓库配置 | ID引用 + ACL |
| 系统管理域 | 测试管理域 | `env_id`, `created_by` | ID引用 + ACL |
| 系统管理域 | 执行管控域 | `env_id`, `trigger_user_id` | ID引用 + ACL |
| 系统管理域 | 质量报表域 | `created_by`, `resolved_by` | ID引用 + ACL |
| 代码分析域 | 测试管理域 | 变更分析影响用例 | 领域事件 |
| 测试管理域 | 执行管控域 | `case_id` 用例被执行 | ID引用 + ACL |
| 执行管控域 | 质量报表域 | `task_id`, `execution_id` | 领域事件 + ID引用 |
| 质量报表域 | 智能体域 | 报告/缺陷数据供 AI 记忆 | 领域事件 |
| 代码分析域 | 质量报表域 | `commit_id` 关联缺陷 | ID引用 + ACL |

### 3.2 两种交互模式

#### 模式 A：ID 引用 + 防腐层（ACL）

适用于：上下游稳定、查询为主、需要即时返回数据的场景。

```
其他域 → SystemAcl(防腐层接口,在基础设施层)
         → 实际调用: SysUserMapper / 或远程调用
         → 返回: UserInfo(传输对象,不是领域对象)
```

**防腐层的作用**：将外部域的模型转换成自己需要的格式，外部域变更时只改 ACL，不影响自己的领域逻辑。

#### 模式 B：领域事件（Domain Event）

适用于：上游变更需要下游响应、不需要即时返回、解耦要求高的场景。

```
上游聚合根 → registerEvent(领域事件)
           → 应用层保存后 pullDomainEvents()
           → ApplicationEventPublisher.publishEvent()
           → 下游 @EventListener 异步消费
```

**事件的好处**：上游不需要知道下游是谁，完全解耦。新增下游只需要加消费者。

---

## 四、域内 CQRS 分层结构

每个限界上下文内部，统一采用 CQRS 模式，分三层：

```
com.dwl.<module>.<domain>/
│
├── domain/                            # 【领域层】放 model 模块
│   ├── aggregate/                     #   聚合根 (有唯一ID,有业务方法,有事务边界)
│   ├── entity/                        #   实体 (聚合内的实体)
│   ├── valueobject/                   #   值对象 (无ID,不可变)
│   ├── event/                         #   领域事件 (本域发布的事件)
│   ├── repository/                    #   仓储接口 (只定义接口,实现在基础设施层)
│   └── service/                       #   领域服务 (跨聚合的业务逻辑)
│
├── application/                       # 【应用层】放 service 模块
│   ├── command/                       #   写操作命令
│   ├── query/                         #   读操作查询
│   ├── handler/                       #   处理器 (CommandHandler + QueryHandler)
│   ├── dto/                           #   入参 DTO (Controller→Application)
│   ├── vo/                            #   出参 VO (Application→Controller)
│   └── service/                       #   应用服务 (门面,统一入口,编排Handler)
│
└── infrastructure/                    # 【基础设施层】放 dao 模块
    ├── repository/                    #   仓储实现 (基于 MyBatis-Plus Mapper)
    ├── acl/                           #   防腐层 (调用其他域的适配器)
    └── mq/                            #   消息队列 (事件发布/订阅的具体实现)
```

### 4.1 各层职责边界

| 层 | 职责 | 不能做什么 |
|----|------|-----------|
| **领域层(domain)** | 业务规则、聚合根方法、领域事件定义、仓储接口 | 不能依赖 Spring、不能直接操作数据库、不能调其他域 |
| **应用层(application)** | 接收 Command/Query、编排领域对象、事务控制、发布事件 | 不能写业务规则(业务规则在聚合根里)、不能直接操作 Mapper |
| **基础设施层(infrastructure)** | 仓储实现、防腐层、MQ、缓存、外部服务调用 | 不能包含业务逻辑 |

### 4.2 写操作调用链路（以创建用户为例）

```
Controller
  │  接收 HTTP 请求,组装 CreateUserCommand
  ▼
UserApplicationService.createUser(command)
  │  分发到对应 Handler
  ▼
CreateUserCommandHandler.handle(command)
  ├─ 1. 用户名唯一性校验 (userRepository.existsByUsername)
  ├─ 2. 调用聚合根工厂方法 User.create() (业务规则封装在聚合根内)
  ├─ 3. 仓储保存聚合根 (含聚合内实体)
  └─ 4. pullDomainEvents() → 发布领域事件
        │
        ▼ (异步)
     @EventListener 监听器
        └─ 同步读模型 / 发欢迎邮件 / 清理缓存
```

### 4.3 读操作调用链路（以分页查询为例）

```
Controller
  │  接收 HTTP 请求,组装 UserPageQuery
  ▼
UserApplicationService.pageUsers(query)
  │  分发到对应 Handler
  ▼
UserPageQueryHandler.handle(query)
  ├─ 1. 构建 LambdaQueryWrapper
  ├─ 2. sysUserMapper.selectPage(page, wrapper)
  └─ 3. 实体转 VO,返回 PageResult<UserVO>
```

**注意**：读操作不经过领域模型，直接查数据库组装 VO。读侧可以独立优化（缓存、宽表、ES），不影响写侧。

---

## 五、与 Maven 模块的映射

| DDD 层 | Maven 模块 | 包路径 |
|--------|-----------|--------|
| DDD 基类 | `common` | `com.dwl.common.ddd` |
| 领域层(domain) | `model` | `com.dwl.model.<domain>.domain` |
| 应用层(application) | `service` | `com.dwl.service.<domain>.application` |
| 基础设施层(infrastructure) | `dao` | `com.dwl.dao.<domain>.infrastructure` |
| 接口层(Controller) | `web` | `com.dwl.controller.<domain>` |

**依赖链**：`common → model → dao → service → web`（保持不变）

---

## 六、DDD 核心基类说明

所有基类位于 `com.dwl.common.ddd` 包：

| 基类 | 作用 |
|------|------|
| `Entity<ID>` | 实体基类，通过 ID 区分相等性 |
| `AggregateRoot<ID>` | 聚合根基类，继承 Entity，内置领域事件注册/拉取机制 |
| `ValueObject` | 值对象标记接口，无 ID，通过属性值区分 |
| `DomainEvent` | 领域事件基类，内置 eventId、occurredAt、eventType |
| `Repository<AR, ID>` | 仓储标记接口，定义 findById/save/deleteById/existsById |
| `DomainService` | 领域服务标记接口 |
| `ApplicationService` | 应用服务标记接口 |

### 聚合根的领域事件机制

```java
// 1. 聚合根业务方法中注册事件
public void disable() {
    this.status = 0;
    this.registerEvent(new UserStatusChangedEvent(this.id, oldStatus, 0));
}

// 2. 应用层保存后拉取并发布事件
User user = userRepository.save(user);
List<DomainEvent> events = user.pullDomainEvents();
events.forEach(eventPublisher::publishEvent);
```

---

## 七、聚合根设计规范

### 7.1 什么是聚合根

- 有全局唯一 ID
- 是事务的边界（一个事务只修改一个聚合根）
- 外部对象只能引用聚合根，不能直接引用聚合内的实体
- 封装业务规则（状态变更必须通过聚合根的方法）

### 7.2 各域聚合根清单

| 域 | 聚合根 | 聚合内实体 |
|----|--------|-----------|
| 系统管理域 | `User` | `UserRole` |
| 系统管理域 | `Role` | `RolePermission` |
| 系统管理域 | `Permission` | (树形自引用) |
| 系统管理域 | `Environment` | `EnvVariable` |
| 系统管理域 | `Repository` | - |
| 系统管理域 | `DictType` | `DictData` |
| 代码分析域 | `ChangeAnalysis` | `ChangeAnalysisCommit`, `AffectedScope` |
| 代码分析域 | `GitCommit` | - |
| 测试管理域 | `TestCase` | `CaseStep`, `CaseVersion` |
| 测试管理域 | `BusinessLink` | `LinkNode` |
| 测试管理域 | `PageElement` | - |
| 测试管理域 | `TestDataSet` | - |
| 执行管控域 | `TestTask` | `TaskCaseRelation` |
| 执行管控域 | `TaskExecution` | `ExecutionStepResult` |
| 执行管控域 | `ExecNode` | - |
| 质量报表域 | `TestReport` | `ReportExecutionRelation`, `AiRootCause`, `ManualFailureMark` |
| 质量报表域 | `DefectRecord` | (跨域聚合) |
| 质量报表域 | `QualityDailyStats` | - |
| 智能体域 | `AgentConversation` | `AgentToolCall` |
| 智能体域 | `AgentMemory` | - |

---

## 八、开发规范

### 8.1 命名规范

| 元素 | 命名规则 | 示例 |
|------|---------|------|
| 聚合根 | 业务名词，不加后缀 | `User`, `TestCase`, `TestTask` |
| 聚合内实体 | 业务名词 | `UserRole`, `CaseStep` |
| 值对象 | 业务名词 | `Address`, `Money` |
| 领域事件 | 过去式动词 + Event | `UserCreatedEvent`, `TaskCompletedEvent` |
| 仓储接口 | 聚合根名 + Repository | `UserRepository`, `TestCaseRepository` |
| 仓储实现 | 聚合根名 + RepositoryImpl | `UserRepositoryImpl` |
| Command | 动词 + 名词 + Command | `CreateUserCommand`, `UpdateUserStatusCommand` |
| Query | 名词 + Query | `UserPageQuery`, `UserDetailQuery` |
| CommandHandler | Command名 + Handler | `CreateUserCommandHandler` |
| QueryHandler | Query名 + Handler | `UserPageQueryHandler` |
| 应用服务 | 聚合根名 + ApplicationService | `UserApplicationService` |
| 防腐层 | 域名 + Acl | `SystemAcl`, `TestManagementAcl` |

### 8.2 编码规范

1. **聚合根必须继承 `AggregateRoot<Long>`**，不能直接继承 `BaseEntity`
2. **所有状态变更必须通过聚合根的方法**，不能在 Handler 里直接 set 属性
3. **CommandHandler 必须加 `@Transactional`**，QueryHandler 不需要
4. **仓储接口定义在领域层**，实现在基础设施层
5. **DTO/VO 放在应用层**，不放在 model 模块
6. **跨域调用必须通过防腐层**，不能直接依赖其他域的 Mapper
7. **领域事件由聚合根注册**，应用层保存后拉取并发布
8. **一个事务只修改一个聚合根**，跨聚合操作通过领域事件异步处理

### 8.3 包结构示例（系统管理域）

```
model 模块:
  com.dwl.model.system.domain
    ├── aggregate/
    │   └── User.java
    ├── entity/
    │   └── UserRole.java
    ├── event/
    │   ├── UserCreatedEvent.java
    │   ├── UserStatusChangedEvent.java
    │   └── UserDeletedEvent.java
    └── repository/
        └── UserRepository.java

service 模块:
  com.dwl.service.system.application
    ├── command/
    │   ├── CreateUserCommand.java
    │   ├── UpdateUserStatusCommand.java
    │   └── DeleteUserCommand.java
    ├── query/
    │   ├── UserPageQuery.java
    │   └── UserDetailQuery.java
    ├── handler/
    │   ├── CreateUserCommandHandler.java
    │   ├── UpdateUserStatusCommandHandler.java
    │   ├── DeleteUserCommandHandler.java
    │   ├── UserPageQueryHandler.java
    │   └── UserDetailQueryHandler.java
    ├── vo/
    │   └── UserVO.java
    └── service/
        └── UserApplicationService.java

dao 模块:
  com.dwl.dao.system.infrastructure
    ├── repository/
    │   └── UserRepositoryImpl.java
    └── acl/
        └── SystemAcl.java
```

---

## 九、迁移指南（从现有代码迁移）

### 9.1 现有代码结构

- `com.dwl.model.entity.system.SysUser` — 数据库实体
- `com.dwl.model.dto.system.UserCreateDTO` — 请求 DTO
- `com.dwl.model.vo.system.UserVO` — 响应 VO
- `com.dwl.service.system.SysUserService` — 传统 Service 接口
- `com.dwl.service.system.create/query/delete` — 按操作拆分的 Service
- `com.dwl.service.system.cqrs` — CQRS 示例包

### 9.2 迁移步骤

1. **第一步：创建新结构骨架**（已完成）
   - 6个域的目录结构
   - DDD 核心基类
   - 系统管理域完整示例

2. **第二步：逐个域迁移**
   - 先迁系统管理域（已有完整示例参考）
   - 再迁测试管理域、执行管控域（核心业务域）
   - 最后迁代码分析域、质量报表域、智能体域

3. **第三步：旧代码逐步废弃**
   - 新功能走 DDD+CQRS 结构
   - 旧代码逐步重构到新结构
   - 重构完成后删除旧包

### 9.3 迁移注意事项

- **数据库实体（SysUser）和聚合根（User）可以共存**：聚合根继承 AggregateRoot，数据库实体保持不变，仓储实现负责两者转换
- **DTO/VO 迁移到应用层**：旧的 `com.dwl.model.dto` 和 `com.dwl.model.vo` 逐步迁移到 `com.dwl.service.<domain>.application.dto/vo`
- **旧 Service 逐步废弃**：新 Controller 直接依赖 ApplicationService，旧 Controller 逐步改造

---

## 十、参考实现

系统管理域已提供完整参考实现，包含：

- **领域层**：`User` 聚合根（含业务方法）、`UserRole` 实体、3个领域事件、`UserRepository` 接口
- **应用层**：3个 Command、2个 Query、5个 Handler、`UserVO`、`UserApplicationService`
- **基础设施层**：`UserRepositoryImpl`（仓储实现）、`SystemAcl`（防腐层）

其他5个域已提供聚合根占位类和仓储接口，可在此基础上扩展。

---

## 附录：文件清单

### DDD 核心基类（common 模块）
- `com.dwl.common.ddd.Entity`
- `com.dwl.common.ddd.AggregateRoot`
- `com.dwl.common.ddd.ValueObject`
- `com.dwl.common.ddd.DomainEvent`
- `com.dwl.common.ddd.Repository`
- `com.dwl.common.ddd.DomainService`
- `com.dwl.common.ddd.ApplicationService`

### 系统管理域完整实现
- 领域层：`User`, `UserRole`, `UserCreatedEvent`, `UserStatusChangedEvent`, `UserDeletedEvent`, `UserRepository`
- 应用层：`CreateUserCommand`, `UpdateUserStatusCommand`, `DeleteUserCommand`, `UserPageQuery`, `UserDetailQuery`, 5个Handler, `UserVO`, `UserApplicationService`
- 基础设施层：`UserRepositoryImpl`, `SystemAcl`

### 其他域聚合根占位
- 代码分析域：`ChangeAnalysis`, `GitCommit`, `ChangeAnalysisRepository`
- 测试管理域：`TestCase`, `BusinessLink`, `TestCaseRepository`
- 执行管控域：`TestTask`, `TaskExecution`, `TestTaskRepository`
- 质量报表域：`TestReport`, `DefectRecord`, `TestReportRepository`
- 智能体域：`AgentConversation`, `AgentMemory`, `AgentConversationRepository`
