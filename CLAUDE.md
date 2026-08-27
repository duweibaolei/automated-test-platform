# CLAUDE.md

本文件为在仓库中工作的 Claude Code 提供指导。

---

## 项目简介

**双模式驱动的 Web 自动化测试平台**，采用 **Java + C + Python + Vue** 混合架构：

| 语言 | 职责 |
|------|------|
| Java | 业务逻辑、分层架构、任务调度、数据存储 |
| C | 实时 diff 比对、依赖图构建、变更影响面计算 |
| Python | 智能编排、用例生成、根因分析、语义匹配 |
| Vue | 页面展示 |

### 技术栈

| 组件 | 版本 |
|------|------|
| JDK | 17 |
| Spring Boot | 3.4.1 |
| MyBatis Plus | 3.5.9 |
| Springdoc OpenAPI | 2.8.3 |
| JWT | 0.12.6 |
| MapStruct | 1.6.3 |
| Lombok | 1.18.36 |

---

## 六大核心域

| 域 | 表数量 | 核心表 |
|------|--------|--------|
| 系统管理域 | 9 | sys_user, sys_role, sys_permission |
| 代码分析域 | 4 | change_analysis, git_commit |
| 测试管理域 | 12 | test_case, business_link, page_element |
| 执行管控域 | 5 | test_task, task_execution |
| 质量报表域 | 6 | test_report, defect_record |
| 智能体域 | 3 | agent_conversation, agent_memory |

### 跨域数据流转

```
Git Webhook → change_analysis → case_analysis_relation → test_task → execution → test_report → agent_memory
```

---

## Java 后端架构

### 模块结构

```
automated-test-platform-server/
├── automated-test-platform-common   # 基础层：R, ErrorCode, BaseEntity, DDD 基类，枚举
├── automated-test-platform-model    # 领域层：聚合根、实体、值对象、领域事件
├── automated-test-platform-dao      # 基础设施层：Mapper 接口、仓储实现、防腐层
└── automated-test-platform-service  # 应用层：Command/Query 处理程序、DTO、VO
```

### DDD + CQRS 设计

每个限界上下文按 CQRS 模式组织：

```
service/{domain}/
├── application/      # 应用服务
├── cqrs/            # CQRS 公共工具
├── create/          # 创建命令
├── delete/          # 删除命令
├── query/           # 查询处理程序
└── update/          # 更新命令
```

### 仓储接口结构

```
dao/{domain}/
├── mapper/          # MyBatis Mapper 接口
├── repository/      # 仓储实现
└── infrastructure/  # 防腐层 (ACL)
```

---

## 开发规范

### 注释格式标准

所有类和方法注释采用中英对照格式：

```java
/**
 * 中文描述
 * English description
 * <p>
 * 详细说明（如有）
 *
 * @Author Dwl
 * @Version 1.0
 * @Since YYYY-MM-DD HH:mm
 */
```

`@Schema` 注解使用多行格式：

```java
@Schema(description = """
        中文描述
        English description
        """, example = "value")
```

### Swagger 注解使用

| 注解 | 用途 |
|------|------|
| `@Tag` | Controller 类 - 接口分组 |
| `@Operation` | 方法 - 接口描述 |
| `@ApiResponses` / `@ApiResponse` | 方法 - 响应状态码 |
| `@Parameter` | 方法参数 - 参数说明 |
| `@Schema` | 实体/字段 - 模型描述 |
| `@Hidden` | 隐藏不公开内容 |

### 异常与错误码

所有自定义异常统一定义在 `automated-test-platform-common` 模块：

**位置：**
- 异常类：`common/src/main/java/com/dwl/common/exception/`
- 错误码：`common/src/main/java/com/dwl/common/enums/ErrorCode.java`

**错误码编号规则：**

| 域 | 编号范围 | 前缀示例 |
|------|----------|----------|
| 通用错误码 | 200-599 | HTTP 标准码 |
| 用户模块 | 1001-1999 | USER_* |
| 测试用例模块 | 2001-2999 | CASE_* |
| 测试任务模块 | 3001-3999 | TASK_* |
| 测试报告模块 | 4001-4999 | REPORT_* |
| 引擎与 AI | 5001-5999 | ENGINE_*, AI_* |
| 代码分析域 | 6001-6999 | CHANGE_*, GIT_* |

---

## 常用命令

### 构建

```bash
# 全量构建（跳过测试）
mvn clean install -DskipTests

# 单模块构建
mvn clean install -pl automated-test-platform-common -am -DskipTests
```

### 测试

```bash
# 运行单测类
mvn test -Dtest={ClassName}Test

# 运行单测试方法
mvn test -Dtest={ClassName}Test#{methodName}
```

### 启动

```bash
cd automated-test-platform-admin
mvn spring-boot:run
```

---

## Skills

### 新增领域异常

1. 在 `ErrorCode.java` 中添加对应错误码（按域分配编号范围）
2. 在 `common/exception/` 下创建异常类（如需要）
3. 提供工厂方法便于调用

### 新增 Mapper 接口

1. 在 `dao/{domain}/mapper/` 下创建接口
2. 继承 `BaseMapper<Entity>`
3. 添加 `@Mapper` 注解
4. 按标准格式编写注释

### 新增查询处理程序

1. 在 `service/{domain}/query/` 下创建类
2. 注入对应的 Repository
3. 返回 VO 而非领域实体
4. 使用 MapStruct 进行对象转换

---

## 文档参考

- `README.md` - 项目背景与技术选型
- `项目文档/数据库/数据库表全景关系.md` - 表结构与关联关系
- `项目文档/数据库/*.sql` - 各域 SQL 脚本
