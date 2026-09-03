package com.dwl.common.ddd;

/**
 * 领域事件发布器
 * <p>
 * Domain Event Publisher
 * <p>
 * 应用层在保存聚合根后调用本接口发布领域事件, 由 MQ 异步投递给下游限界上下文
 * <p>
 * The application layer publishes domain events through this interface after
 * saving the aggregate root; events are delivered asynchronously to downstream
 * bounded contexts via MQ
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-09-01 11:02
 */
public interface DomainEventPublisher {

    /**
     * 发布实现 MQ 传输契约的领域事件
     * <p>
     * Publish a domain event that implements the MQ transport contract
     *
     * @param event 领域事件 domain event
     */
    void publish(DomainEvent event);

}