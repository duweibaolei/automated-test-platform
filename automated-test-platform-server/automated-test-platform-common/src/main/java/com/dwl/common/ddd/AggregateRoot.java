package com.dwl.common.ddd;


import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DDD 聚合根基类
 * <p>
 * DDD Aggregate Root Base Class
 * <p>
 * 聚合根是聚合的入口, 外部对象只能引用聚合根, 不能直接引用聚合内的实体, 聚合根负责
 * 维护聚合内的业务规则一致性, 是事物的边界, 一个事务只能修改一个聚合根
 * <p>
 * Aggregate Root is the entry point of an aggregate
 * <p>
 * External objects can only reference the aggregate root, not entities within the aggregate
 * <p>
 * The aggregate root is responsible for maintaining business rule consistency within the aggregate
 * and is the boundary of a transaction
 * <p>
 * One transaction modifies only one aggregate root
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 16:40
 */
@Schema(description = """
        DDD 聚合根基类
        DDD Aggregate Root Base Class
        """)
public abstract class AggregateRoot<ID extends Serializable> extends Entity<ID> {

    @Serial
    private static final long serialVersionUID = 1L;


    @Schema(description = """
            聚合根产生的领域事件列表
            Domain events raised by this aggregate root
            """)
    private final transient List<DomainEvent> domainEvents = new ArrayList<>();

    /**
     * 注册领域事件(由聚合根的业务方法进行调用)
     * <p>
     * Register a domain event (called by business methods of the aggregate root)
     *
     * @param event Domain event
     */
    protected void registerEvent(DomainEvent event) {
        if (Objects.nonNull(event)) {
            this.domainEvents.add(event);
        }
    }

    /* ================================================================
     * 查看事件(查看但不清空)
     * Peek Events (view without clearing)
     * ================================================================ */

    /**
     * 查看所有领域事件但不清空
     * <p>
     * Peek all domain events without clearing
     * <p>
     * 适用于: 需要检查有哪些事件, 但不消费(不删除)的场景
     * <p>
     * Suitable for: need to check what events exist, but not consume (not delete)
     *
     * @return Unmodifiable list of all domain events
     */
    public List<DomainEvent> peekDomainEvents() {
        return List.copyOf(this.domainEvents);
    }

    /* ================================================================
     * 取出与清空
     * Pull and Clear
     * ================================================================ */

    /**
     * 取出所有领域事件并清空(消费)
     * <p>
     * Pull all domain events and clear them (consume)
     * <p>
     * 适用于: 应用层保存聚合根后, 一次性取走事件并发布, 避免重复发布
     * <p>
     * Suitable for: application layer pulls events once after saving the
     * aggregate root and publishes them, avoiding duplicate publishing
     *
     * @return Unmodifiable list of all domain events
     */
    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = List.copyOf(this.domainEvents);
        this.domainEvents.clear();
        return events;
    }

    /**
     * 按类型取出领域事件并清空(消费)
     * <p>
     * Pull domain events of the specified type and clear them (consume)
     * <p>
     * 适用于: 应用层只关心某一类事件(如创建用户时的 UserCreatedEvent)时,
     * 只取走指定类型, 避免误发布同聚合根上的其它事件
     * <p>
     * Suitable for: application layer only cares about a specific event type
     * (e.g. UserCreatedEvent when creating a user); pulls only the specified
     * type to avoid accidentally publishing other events of the same aggregate
     *
     * @param eventType 事件类型 event type
     * @return Unmodifiable list of domain events of the specified type
     */
    public List<DomainEvent> pullDomainEvents(Class<? extends DomainEvent> eventType) {
        List<DomainEvent> events = this.domainEvents.stream()
                .filter(eventType::isInstance)
                .toList();
        this.domainEvents.removeIf(eventType::isInstance);
        return events;
    }

    /**
     * 清空所有领域事件
     * <p>
     * Clear all domain events
     */
    public void clearDomainEvents() {
        this.domainEvents.clear();
    }

    /**
     * 按类型清空领域事件
     * <p>
     * Clear domain events by type
     *
     * @param eventType Event class
     */
    public void clearDomainEvents(Class<? extends DomainEvent> eventType) {
        this.domainEvents.removeIf(eventType::isInstance);
    }

    /**
     * 判断是否有指定类型的领域事件
     * <p>
     * Check if there are domain events of the specified type
     *
     * @param eventType Event class
     * @return true if has domain events of the specified type
     */
    public boolean hasDomainEvents(Class<? extends DomainEvent> eventType) {
        return this.domainEvents.stream().anyMatch(eventType::isInstance);
    }

    /**
     * 判断是否有领域事件
     * <p>
     * Check if there are any domain events
     *
     * @return true if has any domain events
     */
    public boolean hasDomainEvents() {
        return !this.domainEvents.isEmpty();
    }


}
