package com.dwl.common.ddd;

/**
 * DDD 应用服务标记接口
 * DDD Application Service Marker Interface
 * <p>
 * 应用服务是限界上下文的入口,负责编排领域对象完成用例。
 * 应用服务不包含业务规则,只做: 事务控制、调度聚合根/领域服务、
 * 发布领域事件、数据转换(DTO/VO)。
 * Controller 只依赖应用服务,不直接操作领域对象。
 * <p>
 * Application Service is the entry point of a bounded context, responsible for
 * orchestrating domain objects to complete use cases. Application Services do not
 * contain business rules; they only do: transaction control, dispatching aggregate
 * roots/domain services, publishing domain events, data transformation (DTO/VO).
 * Controllers only depend on Application Services, not domain objects directly.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
public interface ApplicationService {
}
