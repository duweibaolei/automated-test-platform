# 自动化测试平台项目背景

## 为什么想做这样一个平台

不知不觉从 2019 年入行做开发至今, 写了多年的业务代码, 踩过无数项目坑, 也彻底读懂了《人月神话》的经典结论: 

> **软件项目的结构性问题, 没有靠单纯堆人力/疯狂加班就能挽回进度的捷径.** 无序的人力堆叠, 只会让沟通成本暴涨/团队协作愈发混乱, 进一步加剧项目困境

同时, 我也经历过很多次整体项目大版本升级/架构升级. 在这些情况下, 都需要去统计数据表/接口/依赖关系, 梳理整个代码系统的业务链路/模块关联和数据流转, 但这些往往没有人能够完全说得清楚, 每次都要靠人工看代码去梳理, **这真的是件很烦人的事情, 不仅枯燥, 而且对个人能力的提升也没有一点帮助**

除此之外, 我们在日常的测试中更加相信的是**所见即所得**式测试(毕竟确实我们能直观看到的也只有页面和数据库), 在这种情况下, **很难明确本次迭代的测试覆盖率究竟是多少**

同时, 在进行测试验证的时候, 也缺乏对整个链路数据变化的判断, 我多次看到, 虽然数据最终入库是正确的, 但是中间的流转过程极其不合理, 当然这种要求对于测试或者评价指标来说太困难了, 毕竟总不能每次都跟着审查一遍代码吧

实质上, 对于后端开发来说, 什么是业务呢? 

**本质上就是数据的流转**

因此, 如果能有一个全链路的数据追踪, 真的会太方便了, 毕竟每次都去一台专门的内网机上看数据确实有点麻烦, 有时候还那么卡, 真的是锻炼了我的耐性, 另外, 我本人其实不太喜欢自行测试(当然这是我的不对, 习惯确实不好), 导致每次都会出现很多 `bug`, 也亏了组内的测试同事脾气好. 也不得不吐槽, `N` 网的开发环境数据真的是一塌糊涂, 导致我每次想要获取指定的数据时, 都会直接去改数据, **这也间接加剧了数据的混乱**

最终在 `2025` 年, 我萌生了做一个自动化测试平台的想法, 为了寻找参考, 我查看了一些网上的开源产品, 但怎么说呢, 总是感觉差点什么, 它们大多数还是基于人工录入测试用例的方式来进行管理, 我本质上感觉这种模式节省不了太多时间, **既然号称自动化, 怎么还能有大量用例需要人工录入呢？** 虽然也有 `AI` 生成, 但针对测试数据的构造, 还是没那么方便

综合考虑, 最终还是觉得自己做一个更合适, 一方面可以提升一下自己的能力, 整合一下技术栈；另一方面, 我也希望借此机会, 能打磨出一个完美的开源产品

---

## 技术选型

其实前期搭建过多个项目, 早期都是使用 `Java` + ` Vue`, 每次都是写一点就会半途而废, 总是感觉和心里的预期存在着偏差, 不是我理想中的项目

也是想了很久, 为什么总是感觉不合心意, 一方面是这个项目确实比较难, 与我平时写的业务代码存在着巨大的差异, 对我来说是一个蛮大的挑战, 其次这个项目的重点在于代码分析, 不得不承认我还是个菜鸡

因此, 如果还是采用传统的前后端分离模式去写, 限制会很大. 并且当前 `AI` 的冲击拉低了入行的门槛, 未来的发展趋势一定会是要求广度, 而不是守着单语言的模式, **更多的应该是根据语言的特性发挥其最大性能**

最终决定采用 **`C` / `Python` / `Java` / `Vue` 的混合模式**: 

- **`Java`**: 本身侧重于业务, 因此只专心做好业务逻辑/分层架构/任务调度/数据存储这些核心流程

- **`C`**: 本身侧重于计算, 因此实时的 `diff` 比对/依赖图构建/变更影响面计算由它来做更合适

- **`Python`**: 毕竟叫自动化测试平台, 怎么能少了 `AI` 的参与呢, 负责智能编排/用例生成/根因分析/语义匹配这些能力

- **`Vue`**: 专职页面展示即可

  ![项目大纲.drawio](F:\work-space\idea\BeingDeveloped\automated-test-platform\project-documentation\picture\项目大纲.drawio.png)

---

## 数据库设计

嗯, 当下确定了基础架构, 理论上了可以开始写代码了, 但是吧直接写代码还是会陷入到之前的困境当中, 这么多年了也深刻的理解了一个好的系统, 数据库的先行设计是基石, 因为要做的是**全链路的追踪**, 设计的不应该只是单纯的表结构, 更加是核心业务流转的一种思想体现

因此我将整体设计规划为**六大核心业务域**: 

- **系统管理**: 这个系统的基础, 只负责对对应的权限和环境配置
- **代码分析**: 这个是**变更驱动测试**的关键, 连接 `Git` 分析代码, 由程序分析对应项目的变更点
- **测试管理**: 这个是核心资产, 主要负责对象库和业务链
- **执行管理**: 调度中心, 负责分发管理任务调度, 确保执行过程可控/可观测
- **质量报表**: 生成报告/追踪缺陷, 用数据证明质量趋势, 而不在是凭感觉
- **智能体**:  集成 `LLM` 能力, 提供对话/记忆和根源分析



![数据库表全景大纲.drawio](F:\work-space\idea\BeingDeveloped\automated-test-platform\project-documentation\picture\数据库表全景大纲.drawio.png)

### 详细模块设计

#### 1. 系统管理域

这一部分负责平台的基础支撑, 包括用户权限/环境配置以及字典管理

- **用户与权限**: 
  - **用户(`sys_user`)**: 管理登录账号/状态以及基本信息
  - **角色与权限(`sys_role`/`sys_permission`)**: 基于 `RBAC` 模型, 支持菜单/按钮/`API` 级别的细粒度的权限控制
  - **关联关系**: 用户-角色/角色-权限的多对多关联关系
- **基础设施配置**: 
  - **环境配置(`sys_environment`)**: 管理测试环境(如测试/预发布)的`Base URL`
  - **`Git` 仓库配置(`sys_repository`)**: 存储仓库地址/分支策略及认证信息(`SSH`/`Token`), 支持 `Webhook` 触发
- **字典管理**: 
  - **字典类型与数据(`sys_dict_type`, `sys_dict_data`)**: 统一管理系统的枚举值(如触发来源/风险等级等)

#### 2. 代码分析域

是实现**变更驱动测试**的核心逻辑, 主要目的是为了连接代码仓库和测试资产

- **代码追踪** : 
  - **Git 提交记录(`git_commit`)**: 记录 `Commit Hash`/作者/变更文件数及触发来源
  - **影响分析**: 
    - **变更分析(`change_analysis`)**: 核心分析记录, 包含 `AI` 生成的风险等级/变更摘要及测试建议
    - **关联关系(`change_analysis_commit`)**: 分析任务与具体 `Commit` 的关联
    - **影响范围(`affected_scope`)**: 标记受代码变更影响的具体对象(如前端页面/后端 `API`/数据库表)

#### 3. 测试管理域

管理自动化测试的核心资产, 分为对象库模式和业务链模式

- **对象库管理**:  
  - **页面元素(`page_element`)**: 维护页面元素的定位/所属页面以及来源
- **用例管理**: 
  - **测试用例(`test_case`)**: 定义用例的基本信息/优先级/健康度评分和版本控制
  - **用例步骤(`test_step`)**: 详细的步骤操作(动作/输入/断言)以及关联的元素对象
  - **版本历史(`case_version`)**: 利用 `JSON` 快照保存用例的历史版本
- **业务链路**:
  - **业务链路(`business_link`)&节点(`link_node`)**: 将离散的接口或者页面组装成完整的业务流
- **数据与标签**: 
  - **测试数据集(`test_data_set`)**: 管理 `JSON` 格式的测试数据
  - **标签体系(`case_tag`, `case_tag_relation`)**: 支持用例的多维度分类
- **关联映射**: 
  - **用例与变更(`case_analysis_relation`)**: 标记哪些用例受到了特定代码变更影响(如执行/需更新或需新增)
  - **用例与链路(`case_link_relation`)**: 建立用例与业务链路的归属关系

#### 4. 执行管控域

测试任务的调度/分发与结果收集

- **节点管理**: 
  - **执行节点(`exec_node`)**: 管理分布式执行节点(`Node`), 监控其状态(健康/离线/繁忙)及并发能力 
- **任务调度**: 
  - **测试任务(`test_task`)**: 定义任务触发方式(自动/手动/定时)/并发数/重试策略及关联的变更分析 `ID`
  - **任务用例关联(`task_case_relation`)**: 记录任务包含的具体用例列表
- **执行记录**: 
  - **任务执行(`task_execution`)**: 单条用例在某次任务中的执行实例, 记录状态/耗时/错误信息及截图/录像链接
  - **步骤结果(`execution_step_result`)**: 记录每个步骤的详细执行结果及断言情况

#### 5. 质量报表域

质量度量/缺陷追踪和 `AI` 辅助分析

- **报告中心**: 
   - **测试报告(`test_report`)**: 汇总任务结果, 包含 `AI` 生成的分析摘要与修复建议
   - **报告关联(`report_execution_relation`)**: 报告与具体执行记录的映射
- **缺陷管理**: 
   - **缺陷记录(`defect_record`)**: 记录缺陷生命周期(打开/解决/关闭), 关联执行记录与代码提交
   - **失败标记(`manual_failure_mark`)**: 支持人工标记失败原因(业务缺陷/用例失效/环境问题)
- **AI 根因分析**: 
   - **AI 根因 (`ai_root_cause`)**: 存储 `AI` 对执行失败原因的深度分析及置信度
- **质量趋势**: 
   - **日统计(`quality_daily_stats`)**: 按天聚合用例执行数/通过率及缺陷趋势 

#### 6. 智能体域

引入 `AI Agent` 能力

- **交互与记忆**:  
  - **对话记录(`agent_conversation`)**: 存储用户与 `Agent` 的交互历史
  - **长期记忆(`agent_memory`)**: 存储 `Agent` 从风险分析/根因分析中学习到的模式与偏好
- **工具调用**:  
  - **工具调用记录(`agent_tool_call`)**: 记录 `Agent` 调用外部工具的日志与状态

### 核心数据流转逻辑

- **代码提交**: 开发人员提交代码 -> 触发 `Webhook` -> 写入 `git_commit`
- **变更分析**: 系统拉取变更 -> `AI` 分析影响范围 -> 写入 `change_analysis` 和 `affected_scope`
- **用例筛选**: 系统根据影响范围匹配 `case_analysis_relation` -> 确定需执行的 `test_case`
- **任务执行**: 创建 `test_task` -> 分发至 `exec_node` -> 生成 `task_execution` 和 `execution_step_result`
- **结果反馈**: 执行结束 -> 生成 `test_report` -> AI 进行 `ai_root_cause` 分析 -> 若失败则创建 `defect_record`
- **持续学习**: 分析结果与人工标记反馈至 `agent_memory`, 优化后续分析准确性

---

## JAVA 后端相关业务构思与设计

### 架构总览

当前的构思我想的很宏大, 这种情况下如果没有一套好的架构设计会有点说不过去, 传统的**表现层(`UI`) -> 业务逻辑层(`BLL`) -> 数据访问层(`DAL`)** 这种形式更加适合小型项目, 但是放在一个长期项目当中, 随着业务的复杂/AI辅助项目迭代的加快, 这种架构迟早会陷入熵增的困境, 最后导致改一处牵动全身, 维护成本越来越高

同时毕竟是做技术的, 总归需要有一点探索精神, 在之前的项目当中我尝试过 `DDD` 思想结合 `JSON` 模板的形式, 但是那时候限制很多, 技术能力也不是很好, 导致了很多短板, 都是未来的技术债

基于此我重新梳理了架构, 主流的架构设计是: **事件驱动与响应式架构/领域驱动设计(`DDD`)/`CQRS`**, 我会全部用上, 实地实验一下它们的理念思想, 虽然可能会存在过度设计, 但这不是重点

- **跨语言/跨服务的通信场景**, 采用**事件驱动与响应式架构**, 保证不同语言的服务之间能松耦合/高效协同
- **平台内部的业务逻辑设计**, 全面落地**领域驱动设计(`DDD`)**, 结合**`CQRS`**把读写链路拆开, 让业务规则的变化能被控制在局部, 不会污染整个模块的结构
  - 这里解释一下为什么**`DDD`**中还要加 **`CQRS`**——实际开发中会发现一个很现实的问题, **读和写的诉求天生就不一样**
  - **读的时候**, 关注点在于查询快不快/展示够不够灵活, 列表要支持多条件筛选/执行报告要聚合统计等等, 这些需求和领域模型几乎没什么关系
  - **写的时候**, 关注点在于业务规则对不对, 创建用例时要校验什么/触发执行时状态怎么流转/调度冲突怎么处理等等, 这些逻辑需要严格的领域模型来保证一致性
  - 如果读写共用一套模型, 为了查询需求去改领域模型, 很容易导致逻辑混乱, 同时引入大量冗余字段, 从而破坏 `DDD` 的建模初衷
  - 因此 `CQRS` 做的事情很简单: 
    - **写链路走领域模型, 保证业务正确性**
    - **读链路走独立的查询模型, 专注优化查询性能**
  - 单纯的 `DDD` 是一套很重的架构，如果只落地 `DDD` 而不配合 `CQRS` 拆分读写链路，就会面临一个很现实的问题, **读和写的诉求共用同一套领域模型**

    - 读的需求变化频繁, 列表要多一个筛选条件/报告要加一个统计维度/页面要换一种展示结构, 这些改动最终都会落到领域模型和聚合根上
      - 但**领域模型本身是为业务规则服务的，不应该承担查询展示的职责**
      - 一旦为了读的需求反复修改领域模型，**模型会越来越臃肿，业务规则的边界也会越来越模糊**
    - 写的需求同样如此, 状态流转/校验规则/业务约束的变更也需要改动同一条链路
      - **读写两端的改动交织在一起，每次需求迭代都会牵动大量代码**，繁琐且容易出错
      - 时间一长，为了赶进度就会开始走捷径**绕过领域模型直接查库/在应用层堆业务逻辑**, `DDD` 的建模初衷就这样被一点点侵蚀，最终名存实亡
    - 我们之前的项目就经历过这个过程, **强推了 `DDD`，但没有引入 `CQRS` 把读写拆开**，结果读的需求每次改动都要动领域模型，改到后来**整个模型面目全非，架构形同虚设**
    - 这也是这次重新设计架构时，一定要把 **`CQRS` 作为 `DDD` 标配来落地**的核心原因之一

**一个项目是无法控制业务递增, 但是好的架构可以控制业务熵的递增**



![Java 业务中台 · 内部架构.drawio](F:\work-space\idea\BeingDeveloped\automated-test-platform\project-documentation\picture\Java 业务中台 · 内部架构.drawio.png)

### 整体架构: 9 模块分层

```
automated-test-platform-server (父POM, packaging=pom)
 │
 ├── automated-test-platform-common      基础层: R/ErrorCode/BaseController/BaseEntity/JwtUtil/枚举/全局异常/DDD基类
 │     └ 被几乎所有模块依赖
 │
 ├── automated-test-platform-model        领域层: 聚合根/实体/值对象/领域事件/仓储接口(按6个限界上下文分包)
 │     └ 依赖 common
 │
 ├── automated-test-platform-dao         基础设施层: Mapper接口/仓储实现/防腐层/消息队列(按6个限界上下文分包)
 │     └ 依赖 model
 │
 ├── automated-test-platform-service      应用层: Command/Query/Handler/应用服务/DTO/VO(按6个限界上下文分包)
 │     └ 依赖 dao + common(引入 Spring Security/JWT)
 │
 ├── automated-test-platform-web          Web 层: Controller/WebSocket/SecurityConfig/OpenApiConfig(按6个限界上下文分包)
 │     └ 依赖 service
 │
 ├── automated-test-platform-grpc-client   gRPC 客户端: 与 C 引擎通信
 │     └ 依赖 common(含 .proto 编译插件)
 │
 ├── automated-test-platform-ai-client     AI 服务客户端: HTTP 调用 Python AI 服务
 │     └ 依赖 common + model
 │
 ├── automated-test-platform-mq            消息队列模块: RabbitMQ 生产者/消费者
 │     └ 依赖 common + model
 │
 └── automated-test-platform-admin         启动聚合模块(唯一可执行 jar)
       └ 引入 web + grpc-client + ai-client + mq
         + MySQL 驱动 + Flyway + Redis + RabbitMQ + Actuator
```
**依赖链**: 

- `common → model → dao → service → web` 是主干链路
- `grpc-client`/`ai-client`/`mq` 是独立旁支, 直接依赖 `common/model`
- `admin` 聚合所有模块形成可运行 `Spring Boot` 应用

---

###  `DDD` + `CQRS` 架构

#### 设计理念

| 层级   | 模式                | 说明                                                         |
| ------ | ------------------- | ------------------------------------------------------------ |
| 域间   | `DDD` 限界上下文    | `6` 大业务域独立, 通过防腐层(`ACL`) 和领域事件解耦           |
| 域内   | `CQRS` 命令查询分离 | 写操作走 `Command` -> `CommandHandler`, 读操作走 `Query` -> `QueryHandler` |
| 持久化 | 折中方案            | 聚合根直接带 `@TableName`, `Mapper` 泛型用聚合根, 无需 `PO` 转换 |

#### 模版使用说明

##### `common` 模块 - 通用层

**包路径：** `com.dwl.common.ddd`

**职责:**  提供 `DDD` 架构的核心抽象基类, 所有域共享

| 类名                      | 作用                                                         |
| ------------------------- | ------------------------------------------------------------ |
| `Entity.java`             | 实体基类，有唯一标识(`ID`)，定义 `equals/hashCode` 基于 `ID` |
| `AggregateRoot.java`      | 聚合根基类，继承 `Entity`，管理领域事件集合，支持按需拉取/查看/清空事件 |
| `ValueObject.java`        | 值对象基类，无唯一标识，通过属性值判断相等                   |
| `DomainEvent.java`        | 领域事件基类，包含事件 `ID`、发生时间、聚合根 `ID`           |
| `Repository.java`         | 仓储接口标记接口，所有领域层仓储接口继承此接口               |
| `DomainService.java`      | 领域服务标记接口，跨聚合根的业务逻辑放领域服务               |
| `ApplicationService.java` | 应用服务标记接口，编排领域对象、事务边界、事件发布`          |

##### `model` 模块 - 领域层

**包路径**: `com.dwl.model.domain`.<域>.<子包>

**职责**: 纯领域逻辑, 仅依赖 `MyBatis-Plus`

```
com.dwl.model.domain.<域>/
├── aggregate/      # 聚合根(充血模型/有业务方法/领域事件/事务边界)
├── entity/         # 聚合内实体(从属于某个聚合根, 不能独立存在)
├── event/          # 领域事件(继承 DomainEvent)
├── repository/     # 仓储接口(继承 Repository, 定义聚合根的持久化契约)
├── service/    	# 领域服务(不属于单一实体的跨域/业务逻辑)
└── valueobject/    # 值对象(无唯一标识/不可变/通过属性值区分)
```

###### 聚合根编写参考:

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class User extends AggregateRoot<Long> {

    // 基础字段
    private String username;
    private String password;

    // 聚合内实体列表(不持久化, 由仓储加载)
    @Builder.Default
    private transient List<UserRole> roles = new ArrayList<>();

    // 工厂方法(创建聚合根, 初始化状态, 注册领域事件)
    public static User create(String username, String password, ...) {
        User user = User.builder()
                .username(username)
                .password(password)
                .status(1)
                .build();
        user.registerEvent(new UserCreatedEvent(user));
        return user;
    }

    // 业务方法(修改状态, 注册领域事件)
    public void changeStatus(Integer status) {
        this.status = status;
        registerEvent(new UserStatusChangedEvent(this.id, status));
    }

    // 不可变 getter(防止外部直接修改聚合内实体列表)
    public List<UserRole> getRoles() {
        return Collections.unmodifiableList(roles);
    }
}
```

##### `dao` 模块 - 基础设施层

**包路径:** `com.dwl.dao`.<域>.<子包>

**职责:** 实现领域层定义的仓储接口, 基于 `MyBatis Plus` 持久化聚合根, 提供跨域的防腐层(`ACL`)

```
com.dwl.dao.<域>/
├── infrastructure/
│   ├── repository/   # 仓储实现(实现 model 模块的仓储接口)
│   └── acl/          # 防腐层(跨域调用, 将外部模型转换为内部模型)
└── mapper/           # MyBatis-Plus Mapper(泛型用聚合根)   
```

###### 仓储编写参考

```java
@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    @Override
    public User findById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) return null;
        loadRoles(user);  // 加载聚合内实体
        return user;
    }

    @Override
    public User save(User user) {
        // 保存聚合根主表
        if (user.getId() == null) {
            userMapper.insert(user);
        } else {
            userMapper.updateById(user);
        }
        // 保存聚合内实体(先删后插, 保证幂等)
        saveRoles(user);
        return user;
    }

    // 私有方法加载/保存聚合内实体
    private void loadRoles(User user) { ... }
    private void saveRoles(User user) { ... }
}
```

##### `service` 模块 - 应用层

**包路径模式:** `com.dwl.service`.<域>.`application`.<子包>

**职责:** 编排领域对象, 定义用例(`Command/Query`), 处理事务, 发布领域事件, 返回`VO/DTO`, 不包含业务逻辑(业务逻辑在聚合根/领域服务中)

```
com.dwl.service.<域>.application/
├── command/      # 命令对象(写操作入参)
├── query/        # 查询对象(读操作入参)
├── handler/      # 处理器(CommandHandler / QueryHandler)
├── service/      # 应用服务(编排多个 Handler, 对外门面)
├── vo/           # 视图对象(出参, 返回给前端)
└── dto/          # 数据传输对象(入参, 接收前端请求)
```

###### `CQRS` 调用链路

**写操作(`Command`)**

```
Controller → ApplicationService → CommandHandler → 聚合根(业务方法) → Repository.save() → 发布领域事件
```

**读操作(`Query`)**

```
Controller → ApplicationService → QueryHandler → Mapper(直接查询) → VO
```

###### `Handler` 示例

**`CommandHandler`**

```java
@Component
@RequiredArgsConstructor
public class CreateUserCommandHandler {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional(rollbackFor = Exception.class)
    public Long handle(CreateUserCommand command) {
        // 1. 调用聚合根工厂方法(业务逻辑在聚合根内)
        User user = User.create(command.getUsername(), command.getPassword(), ...);
        // 2. 仓储保存
        userRepository.save(user);
        // 3. 发布领域事件(从聚合根拉取)
        user.pullDomainEvents().forEach(eventPublisher::publishEvent);
        return user.getId();
    }
}
```

**`QueryHandler`**

```java
@Component
@RequiredArgsConstructor
public class UserPageQueryHandler {

    private final UserMapper userMapper;

    public PageResult<UserVO> handle(UserPageQuery query) {
        // 直接用 Mapper 查询, 不走聚合根(读模型可优化)
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(query.getUsername()), User::getUsername, query.getUsername())
               .orderByDesc(User::getCreatedAt);
        Page<User> page = userMapper.selectPage(new Page<>(query.getPageNum(), query.getPageSize()), wrapper);
        // 转换为 VO 返回
        List<UserVO> voList = page.getRecords().stream().map(this::toVO).toList();
        return new PageResult<>(voList, page.getTotal(), query.getPageNum(), query.getPageSize());
    }
}
```

#### 跨域交互规范

##### 防腐层(`ACL`)

跨域调用必须通过防腐层，禁止直接引用其他域的聚合根/实体

```java
// 执行管控域调用系统管理域获取用户信息
@Component
@RequiredArgsConstructor
public class SystemAcl {
	// 直接调用其他域的 Mapper(同进程内)
    private final UserMapper userMapper;  

    public UserBriefDTO getUserBrief(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) return null;
        // 转换为当前域需要的模型(不暴露其他域的聚合根)
        return new UserBriefDTO(user.getId(), user.getUsername(), user.getRealName());
    }
}
```

##### 领域事件

跨域解耦通过领域事件，一个域发布事件，其他域监听处理

```java
// 发布: 在 CommandHandler 中
user.pullDomainEvents(UserCreatedEvent.class);
eventPublisher.publishEvent(userCreatedEvent);

// 监听: 在其他域的 ApplicationService 中
@Component
@RequiredArgsConstructor
public class UserDomainEventListener {

    @EventListener
    public void onUserCreated(UserCreatedEvent event) {
        // 其他域处理逻辑(如智能体域初始化用户记忆)
    }
}
```

##### 跨域引用规则

- **允许**: 通过 `ID` 引用(如 `envId`/`taskId`/`reportId`)
- **允许**: 通过防腐层获取其他域的 `DTO`
- **允许**: 通过领域事件解耦
- **禁止**: 直接引用其他域的聚合根/实体对象
- **禁止**: 直接调用其他域的仓储/`Service`

---

## 前端相关业务构思与设计

![Vue 前端 · 内部架构.drawio](F:\work-space\idea\BeingDeveloped\automated-test-platform\project-documentation\picture\Vue 前端 · 内部架构.drawio.png)



---

## **AI 相关业务构思与设计**

![Python AI 服务 · 内部架构.drawio](F:\work-space\idea\BeingDeveloped\automated-test-platform\project-documentation\picture\Python AI 服务 · 内部架构.drawio.png)



---

## 计算引擎相关业务构思与设计

![C 代码计算引擎.drawio](F:\work-space\idea\BeingDeveloped\automated-test-platform\project-documentation\picture\C 代码计算引擎.drawio.png)

---

## 可行性分析

> 虽然项目的目标颇具挑战性，但幸运的是，市面上已有不少相关产品和开源项目可供借鉴，让整体方案有迹可循、有据可依
> 以下是目前收集到的参考项目，在开发过程中可以从中汲取设计思想与技术方案

### `Java` 后端（`Spring Boot + DDD`）

| 参考项目                          | 核心参考点                                                   |
| :-------------------------------- | :----------------------------------------------------------- |
| **`Spring PetClinic (DDD 版本)`** | 标准 `DDD` 包结构(`Domain, Application, Infrastructure`)，以及在 `Spring `中如何处理领域事件（`@EventListener`）和事务边界 |
| **`Alibaba Cloud Alpha`**         | 企业级 `Spring Boot` 项目的工程规范/配置管理和微服务治理     |

### C/C++ 计算引擎（代码分析）

| 参考项目                    | 核心参考点                                                   |
| :-------------------------- | :----------------------------------------------------------- |
| **`Tree-sitter`**           | 高性能解析器生成器，支持增量解析，非常适合做代码 `Diff` 和 `AST` 分析，是 `C` 语言引擎最佳的核心参考 |
| **`Eclipse JDT Core`**      | `Java AST` 解析的工业级实现，参考其代码依赖分析逻辑          |
| **`gRPC` 官方示例 (`C++`)** | 是学习 `.proto` 文件定义以及 `C++` 端内存管理(避免内存泄漏是 `C++` 服务的痛点) |

### Python AI 服务

| 参考项目        | 核心参考点                                                   |
| :-------------- | :----------------------------------------------------------- |
| **`Dify`**      | 展示了如何封装 `LLM` 接口、如何管理 `Prompt`/如何处理流式响应(`SSE`) |
| **`LangChain`** | 学习 `LLM` 链式调用/`Agent` 设计和工具调用模式               |

### 前端（`Vue 3`）

| 参考项目                      | 核心参考点                                                   |
| :---------------------------- | :----------------------------------------------------------- |
| **`SoybeanAdmin`**            | `Vue3` + `TypeScript` + `Vite` 的顶级脚手架，参考其权限管理(`RBAC`)路由守卫实现 |
| **`Vue-Vben-Admin`**          | 参考其表格封装和表单组件，能快速搭建企业级后台               |
| **`Ant Design Vue` 官方示例** | 参考其组件最佳实践和交互设计规范                             |

### 测试与 CI/CD 生态

| 参考项目                 | 核心参考点                                                   |
| :----------------------- | :----------------------------------------------------------- |
| **`MeterSphere`**        | 国内最流行的开源测试平台，参考其插件化架构 `UI`/`API` 测试管理以及前端交互设计 |
| **`Jenkins` + 插件生态** | 参考其任务调度模型和节点管理（`Agent`/`Node`）机制           |
| **`SonarQube`**          | 参考其代码静态分析逻辑/技术债务计算以及可视化展示            |
| **`Playwright`**         | 浏览器自动化执行引擎，也是关键性的自动化测试工具             |

