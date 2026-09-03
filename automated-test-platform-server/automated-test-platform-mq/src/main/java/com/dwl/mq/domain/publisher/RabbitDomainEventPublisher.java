package com.dwl.mq.domain.publisher;


import com.dwl.common.ddd.DomainEvent;
import com.dwl.common.ddd.DomainEventPublisher;
import com.dwl.mq.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 基于 RabbitMQ 的领域事件发布器实现
 * <p>
 * RabbitMQ-based Domain Event Publisher Implementation
 * <p>
 * 将领域事件发送到领域事件交换机, 路由键为 {@code domain.event.{事件类型}},
 * 并在消息头中携带事件类型与事件标识, 供下游分发器路由
 * <p>
 * Sends domain events to the domain event exchange with routing key
 * {@code domain.event.{eventType}}, carrying event type and event id in
 * message headers for downstream dispatcher routing
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-09-01 11:02
 */
@Slf4j
@Component
public class RabbitDomainEventPublisher implements DomainEventPublisher {

    /**
     * 领域事件路由键前缀
     * Domain event routing key prefix
     */
    private static final String ROUTING_KEY_PREFIX = "domain.event.";

    private final RabbitTemplate rabbitTemplate;

    /**
     * 构造函数
     * Constructor
     *
     * @param rabbitTemplate RabbitMQ 操作模板 RabbitMQ template
     */
    public RabbitDomainEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发布实现 MQ 传输契约的领域事件
     * <p>
     * Publish a domain event that implements the MQ transport contract
     *
     * @param event 领域事件 domain event
     */
    @Override
    public void publish(DomainEvent event) {
        send(event.getEventType(), event.getEventId(), event);
    }

    /**
     * 实际发送逻辑: 序列化事件并写入事件头
     * <p>
     * Actual sending logic: serialize the event and set event headers
     *
     * @param eventType 事件类型 event type
     * @param eventId   事件唯一标识 event unique identifier
     * @param payload   事件载荷 event payload
     */
    private void send(String eventType, String eventId, Object payload) {
        String routingKey = ROUTING_KEY_PREFIX + eventType;
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.DOMAIN_EVENT_EXCHANGE,
                routingKey,
                payload,
                message -> {
                    message.getMessageProperties().setHeader(DomainEvent.HEADER_EVENT_TYPE, eventType);
                    message.getMessageProperties().setHeader(DomainEvent.HEADER_EVENT_ID, eventId);
                    return message;
                });
        log.info("领域事件已发布, eventType={}, eventId={}, routingKey={}", eventType, eventId, routingKey);
    }

}
