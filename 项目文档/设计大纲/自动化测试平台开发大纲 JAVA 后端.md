# 自动化测试平台开发大纲

## 一、技术栈与版本基线

| 组件                     | 版本                     | 说明                                          |
| ------------------------ | ------------------------ | --------------------------------------------- |
| JDK                      | 17                       | 强制要求                                      |
| Spring Boot              | 3.4.1                    | 父 POM 继承 `spring-boot-starter-parent`      |
| 构建工具                 | Maven                    | 多模块聚合                                    |
| 包名                     | `com.dwl`                | 全局统一包前缀                                |
| MyBatis-Plus             | 3.5.9                    | 持久层（`mybatis-plus-spring-boot3-starter`） |
| SpringDoc OpenAPI        | 2.8.3                    | Swagger 文档                                  |
| JWT (jjwt)               | 0.12.6                   | 鉴权                                          |
| gRPC                     | 1.69.0 + protobuf 4.29.3 | C 引擎通信                                    |
| MinIO                    | 8.5.14                   | 对象存储                                      |
| Milvus                   | 2.4.4                    | 向量库                                        |
| Hutool                   | 5.8.34                   | 工具集                                        |
| MapStruct                | 1.6.3                    | DTO/VO 转换                                   |
| Lombok                   | 1.18.36                  | 简化代码                                      |
| Testcontainers           | 1.20.4                   | 集成测试                                      |
| Logstash Logback Encoder | 8.0                      | JSON 结构化日志                               |
| Flyway                   | （随 Spring Boot 版本）  | 数据库迁移                                    |
| MySQL Connector          | （随 Spring Boot 版本）  | 数据库驱动                                    |
| Redis (Lettuce)          | （随 Spring Boot 版本）  | 缓存                                          |
| RabbitMQ                 | （随 Spring Boot 版本）  | 消息队列                                      |

---

## 二、整体架构：9 模块分层

```
automated-test-platform-server (父POM, packaging=pom)
 │
 ├── automated-test-platform-common      基础层：R/ErrorCode/BaseController/BaseEntity/JwtUtil/枚举/全局异常
 │     └ 被几乎所有模块依赖
 │
 ├── automated-test-platform-model        数据模型层：entity / dto / vo
 │     └ 依赖 common
 │
 ├── automated-test-platform-dao         数据访问层：Mapper 接口（MyBatis-Plus）
 │     └ 依赖 model
 │
 ├── automated-test-platform-service      业务逻辑层：Service 接口 + 实现
 │     └ 依赖 dao + common（引入 Spring Security、JWT）
 │
 ├── automated-test-platform-web          Web 层：Controller、WebSocket、SecurityConfig、OpenApiConfig
 │     └ 依赖 service
 │
 ├── automated-test-platform-grpc-client   gRPC 客户端：与 C 引擎通信
 │     └ 依赖 common（含 .proto 编译插件）
 │
 ├── automated-test-platform-ai-client     AI 服务客户端：HTTP 调用 Python AI 服务
 │     └ 依赖 common + model
 │
 ├── automated-test-platform-mq            消息队列模块：RabbitMQ 生产者/消费者
 │     └ 依赖 common + model
 │
 └── automated-test-platform-admin         启动聚合模块（唯一可执行 jar）
       └ 引入 web + grpc-client + ai-client + mq
         + MySQL 驱动 + Flyway + Redis + RabbitMQ + Actuator
```

**依赖链总结**：

- `common → model → dao → service → web` 是主干链路
- `grpc-client`、`ai-client`、`mq` 是独立旁支，直接依赖 common/model
- `admin` 聚合所有模块形成可运行 Spring Boot 应用

---

## 三、分步搭建过程

### 第 1 步：创建父 POM 工程

创建 Maven 项目 `automated-test-platform-server`，`packaging=pom`：

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.4.1</version>
    <relativePath/>
</parent>
<groupId>com.dwl</groupId>
<artifactId>automated-test-platform-server</artifactId>
<version>1.0.0-SNAPSHOT</version>
<packaging>pom</packaging>
```

在 `<modules>` 中声明 9 个子模块，在 `<properties>` 中定义所有版本号，在 `<dependencyManagement>` 中统一管理内部模块和外部依赖的版本。

关键属性：

```xml
<java.version>17</java.version>
<mybatis-plus-spring-boot3-starter.version>3.5.9</mybatis-plus-spring-boot3-starter.version>
<springdoc-openapi.version>2.8.3</springdoc-openapi.version>
<jjwt.version>0.12.6</jjwt.version>
<grpc.version>1.69.0</grpc.version>
<protobuf-java.version>4.29.3</protobuf-java.version>
...
```

编译插件 `maven-compiler-plugin` 中配置 Lombok + MapStruct 的 `annotationProcessorPaths`。

---

### 第 2 步：搭建 common 基础模块

`packaging=jar`，无内部依赖，是整个项目的基石。

包含内容：

| 包路径      | 类                                                           | 职责                           |
| ----------- | ------------------------------------------------------------ | ------------------------------ |
| `base`      | BaseController, BaseEntity                                   | 统一响应构建、实体公共字段     |
| `result`    | R, PageResult                                                | 统一返回封装 + 分页结果封装    |
| `enums`     | ErrorCode, CaseStatus, ExecutionStatus, TaskStatus, RiskLevel, SourceType, TriggerSource | 全局枚举                       |
| `exception` | BusinessException, GlobalExceptionHandler                    | 业务异常 + 全局异常处理        |
| `constant`  | CommonConstant, RedisKeyConstant                             | Redis Key、通用常量            |
| `utils`     | JwtUtil, SecurityUtil                                        | JWT 生成/解析、当前用户获取    |
| `config`    | MyBatisPlusMetaObjectHandler                                 | 自动填充 createTime/updateTime |

**依赖重点**：hutool-all、jjwt、mybatis-plus-spring-boot3-starter、springdoc-openapi-api、spring-boot-starter-web/security、logstash-logback-encoder、jakarta.validation-api。

---

### 第 3 步：搭建 model 数据模型模块

`packaging=jar`，依赖 common。

分三个子包：

- `entity/` — 数据库实体（SysUser、TestCase、TestTask、TaskExecution、ExecutionStepResult、AffectedScope、AgentConversation、AgentMemory 等 37+ 实体）
- `dto/` — 请求 DTO（LoginDTO、UserCreateDTO、RepositoryCreateDTO 等 + `dto/ai/` 子包：CaseGenerationDTO、RiskAssessmentDTO、RootCauseDTO、SummaryDTO）
- `vo/` — 响应 VO（UserVO、RoleVO、PermissionVO、EnvironmentVO、RepositoryVO、LoginVO、HealthVO、DictDataVO、DictTypeVO）

**依赖重点**：mybatis-plus-spring-boot3-starter（实体使用 `@TableName/@TableId` 注解）、springdoc-openapi-api、jakarta.validation-api。

---

### 第 4 步：搭建 dao 数据访问层

`packaging=jar`，依赖 model。

内容：`mapper/` 包下放置所有 MyBatis-Plus Mapper 接口（`extends BaseMapper<T>`）。

共有 37 个 Mapper（与实体一一对应），如 SysUserMapper、TestCaseMapper、TaskExecutionMapper、AgentConversationMapper 等。

**依赖重点**：mybatis-plus-spring-boot3-starter、lombok。XML 映射文件放在 `classpath*:mapper/**/*.xml`（配置中指定）。

---

### 第 5 步：搭建 service 业务逻辑层

`packaging=jar`，依赖 dao + common。

结构：

- `service/` — 接口（AuthService、SysUserService、SysRoleService、TestCaseService、TestTaskService、TestReportService、TaskExecutionService、ChangeAnalysisService、DefectRecordService、GitCommitService 等）
- `service/impl/` — 实现类（AuthServiceImpl、SysUserServiceImpl、SysRoleServiceImpl 等）

**依赖重点**：spring-boot-starter-security、jjwt-api/impl/jackson（AuthService 需要签发 JWT）、spring-security-crypto（密码加密 BCryptPasswordEncoder）。

---

### 第 6 步：搭建 web Web 层

`packaging=jar`，依赖 service。

结构：

| 包            | 类                                                           | 职责                                             |
| ------------- | ------------------------------------------------------------ | ------------------------------------------------ |
| `controller/` | AuthController、SysUserController、SysRoleController、SysPermissionController、SysRepositoryController、SysEnvironmentController、SysDictController、HealthController | REST API                                         |
| `config/`     | SecurityConfig、JwtAuthenticationFilter、OpenApiConfig       | Spring Security 配置 + JWT 过滤器 + Swagger 配置 |
| `websocket/`  | TaskProgressWebSocketHandler、WebSocketConfig                | 任务进度实时推送                                 |

**依赖重点**：springdoc-openapi-starter-webmvc-ui、spring-boot-starter-websocket、lombok。

**Security 核心逻辑**：

- `SecurityConfig` — 配置 `SecurityFilterChain`，放行 `/auth/**`、`/swagger-ui/**`、`/v3/api-docs/**`，其余请求走 JWT 鉴权
- `JwtAuthenticationFilter` — 每请求解析 `Authorization: Bearer <token>`，解析后注入 `SecurityContextHolder`

---

### 第 7 步：搭建 grpc-client 模块

`packaging=jar`，依赖 common。

内容：

- `proto/code_analysis.proto` — 定义 gRPC 服务（代码分析请求/响应 message + rpc 方法）
- `client/EngineGrpcClient.java` — gRPC 客户端封装，调用 C 引擎的代码分析能力
- `config/GrpcClientConfig.java` — 读取 `grpc.client.engine.host/port/use-tls` 配置，创建 ManagedChannel

**构建要点**：

```xml
<!-- protobuf-maven-plugin 编译 .proto 为 Java -->
<!-- build-helper-maven-plugin 把生成目录注册为 source root -->
<!-- 需 javax.annotation-api:1.3.2 兼容 @javax.annotation.Generated -->
```

---

### 第 8 步：搭建 ai-client 模块

`packaging=jar`，依赖 common + model。

内容：

| 包        | 类              | 职责                                                         |
| --------- | --------------- | ------------------------------------------------------------ |
| `model/`  | AiResponse      | AI 响应封装                                                  |
| `config/` | AiServiceConfig | RestTemplate 配置（`ai.service.url`、`connect-timeout`、`read-timeout`） |
| `client/` | AiServiceClient | HTTP 调用 Python AI 服务（用例生成、风险评估、根因分析、摘要） |

**依赖重点**：spring-boot-starter-web（使用 RestTemplate）。

---

### 第 9 步：搭建 mq 消息队列模块

`packaging=jar`，依赖 common + model。

内容：

| 包          | 类                                                    | 职责                          |
| ----------- | ----------------------------------------------------- | ----------------------------- |
| `config/`   | RabbitMqConfig                                        | 定义 Exchange、Queue、Binding |
| `message/`  | TaskExecuteMessage、TaskLogMessage、TaskResultMessage | 消息实体                      |
| `producer/` | TaskMessageProducer                                   | 发送任务执行消息              |
| `consumer/` | TaskLogConsumer、TaskResultConsumer                   | 消费任务日志/结果             |

**依赖重点**：spring-boot-starter-amqp。

---

### 第 10 步：搭建 admin 启动聚合模块

`packaging=jar`，是整个项目唯一的可执行 Spring Boot 应用。

**依赖关系**：

```
automated-test-platform-admin 直接引入：
  ├── automated-test-platform-web (→ service → dao → model → common)
  ├── automated-test-platform-grpc-client (→ common)
  ├── automated-test-platform-ai-client (→ common, model)
  ├── automated-test-platform-mq (→ common, model)
  ├── spring-boot-starter-actuator (监控)
  ├── mysql-connector-j (runtime)
  ├── flyway-core + flyway-mysql (数据库迁移)
  ├── spring-boot-starter-data-redis (缓存)
  └── spring-boot-starter-amqp (RabbitMQ)
```

**构建配置**：

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <excludes>
            <exclude>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
            </exclude>
        </excludes>
    </configuration>
</plugin>
```

**启动类** `AutomatedTestPlatformApplication.java`：

```java
@SpringBootApplication
@MapperScan("com.dwl.dao.mapper")
@OpenAPIDefinition(info = @Info(title = "AutomatedTestPlatform API", version = "1.0.0", ...))
public class AutomatedTestPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutomatedTestPlatformApplication.class, args);
    }
}
```

**配置文件三件套**：

| 文件                   | 作用                                                         |
| ---------------------- | ------------------------------------------------------------ |
| `application.yml`      | 主配置：MySQL、Redis、RabbitMQ、MyBatis-Plus、JWT、SpringDoc、Actuator、Jackson、Flyway |
| `application-dev.yml`  | 开发环境覆盖：本地 MySQL（`self-automated-test-platform`）、Redis、RabbitMQ、gRPC 引擎（localhost:9090）、AI 服务（localhost:8000）、CORS `*` |
| `application-prod.yml` | 生产环境覆盖（使用环境变量）                                 |

**配置要点**：

- 所有敏感/环境相关的值使用 `${ENV_VAR:default}` 占位符
- Flyway 配置：`enabled=true`、`locations=classpath:db/migration`、`baseline-on-migrate=true`
- MyBatis-Plus：`id-type=assign_id`、逻辑删除 `isDeleted`（0/1）、`map-underscore-to-camel-case=true`
- JWT：`expiration=86400000`（24h）
- RabbitMQ 监听器：`acknowledge-mode=manual`、`prefetch=10`

**数据库迁移**：`db/migration/V1__create_all_tables.sql` — 一份包含所有表结构的 Flyway 初始迁移脚本。

**日志配置** `logback-spring.xml`：

- dev profile → 彩色 Console 输出
- prod profile → JSON 结构化输出（LogstashEncoder）

---

## 四、构建与运行

### 构建全部模块

```powershell
mvn clean install -DskipTests
```

### 运行（admin 模块）

```powershell
mvn -pl automated-test-platform-admin spring-boot:run
```

或打包后运行：

```powershell
mvn -pl automated-test-platform-admin clean package -DskipTests
java -jar automated-test-platform-admin/target/automated-test-platform-admin-1.0.0-SNAPSHOT.jar --spring.profiles.active=dev
```

### 按模块单独构建（示例）

```powershell
# 只编译 common
mvn -pl automated-test-platform-common clean install -DskipTests

# 编译 dao（需先安装 model）
mvn -pl automated-test-platform-dao -am clean install -DskipTests
```

---

## 五、从零搭建的推荐顺序

1. **创建父 POM** — 定义版本与 dependencyManagement
2. **common** — R、ErrorCode、BaseController、异常处理、JwtUtil（所有模块的地基）
3. **model** — entity/dto/vo（数据模型先行，dao/service 都依赖它）
4. **dao** — Mapper 接口（依赖 model 的 entity）
5. **service** — Service 接口 + 实现（依赖 dao，是业务核心）
6. **web** — Controller、SecurityConfig、WebSocket（依赖 service，对外暴露 API）
7. **grpc-client** — .proto 定义 → 编译 gRPC stub → 客户端封装（可并行于第 5-6 步）
8. **ai-client** — RestTemplate 调用 Python AI 服务（可并行）
9. **mq** — RabbitMQ 配置与生产者/消费者（可并行）
10. **admin** — 最后组装：聚合所有模块 + 配置文件 + 启动类 + Flyway 脚本 + 日志配置

**核心原则**：依赖链决定了 common 必须最先完成，admin 必须最后。中间的 model→dao→service→web 是严格顺序，而 grpc-client、ai-client、mq 三者互相独立，可以并行开发。



