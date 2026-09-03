package com.dwl.mq.domain.consumer;


import com.dwl.common.ddd.DomainEvent;
import com.dwl.mq.config.RabbitMqConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 领域事件分发器
 * <p>
 * Domain Event Dispatcher
 * <p>
 * 统一消费领域事件队列, 根据消息头中的事件类型路由到注册的业务处理器
 * <p>
 * Consumes the domain event queue and routes messages to registered business
 * handlers according to the event type carried in message headers
 * <p>
 * 注意: 模型层事件(如 UserCreatedEvent)若需被本分发器反序列化, 事件类需提供
 * Jackson 可实例化的构造方式(无参构造或 {@code @JsonCreator})
 * <p>
 * Note: model layer events (e.g. UserCreatedEvent) must be Jackson-instantiable
 * (no-arg constructor or {@code @JsonCreator}) to be deserialized by this dispatcher
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-09-01 11:02
 */
@Slf4j
@Component
public class DomainEventDispatcher {

    /**
     * 事件类型 -> 处理器 路由表
     * Event type to handler routing table
     */
    private final Map<String, DomainEventHandler<?>> handlerMap = new HashMap<>();

    /**
     * 事件反序列化器
     * Event deserializer
     */
    private final ObjectMapper objectMapper;

    /**
     * 构造函数: 收集容器内所有领域事件处理器并建立路由表
     * Constructor: collect all domain event handlers in the container and build the routing table
     *
     * @param handlers 领域事件处理器列表 domain event handler list
     */
    public DomainEventDispatcher(List<DomainEventHandler<?>> handlers) {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        for (DomainEventHandler<?> handler : handlers) {
            handlerMap.put(handler.eventType().getSimpleName(), handler);
        }
        log.info("领域事件分发器初始化完成, 已注册处理器数量: {}", handlerMap.size());
    }

    /**
     * 消费领域事件并按事件类型分发
     * <p>
     * Consume domain events and dispatch by event type
     *
     * @param message 原始消息 raw message
     * @param channel RabbitMQ 通道 RabbitMQ channel
     * @throws IOException 反序列化或确认异常 deserialization or acknowledgement exception
     */
    @RabbitListener(queues = RabbitMqConfig.DOMAIN_EVENT_QUEUE, containerFactory = "mq_ListenerContainerFactory")
    public void onMessage(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        Object eventTypeHeader = message.getMessageProperties().getHeader(DomainEvent.HEADER_EVENT_TYPE);
        String eventType = Objects.isNull(eventTypeHeader) ? null : eventTypeHeader.toString();
        try {
            DomainEventHandler<?> handler = Objects.isNull(eventType) ? null : handlerMap.get(eventType);
            if (Objects.isNull(handler)) {
                log.warn("未找到领域事件处理器, eventType={}, 消息将被确认丢弃", eventType);
                channel.basicAck(deliveryTag, false);
                return;
            }
            String body = new String(message.getBody(), StandardCharsets.UTF_8);
            Object event = objectMapper.readValue(body, handler.eventType());
            dispatch(handler, event);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("领域事件处理失败, eventType={}", eventType, e);
            /* 不重回队列, 避免毒消息循环
             * 生产环境建议接入死信队列
             * Do not requeue to avoid poison message loops
             * use a dead letter queue in production
             */
            channel.basicNack(deliveryTag, false, false);
        }
    }

    /**
     * 执行处理器逻辑(类型桥接)
     * Invoke handler logic (type bridge)
     *
     * @param handler 处理器 handler
     * @param event   事件实例 event instance
     */
    @SuppressWarnings("unchecked")
    private void dispatch(DomainEventHandler<?> handler, Object event) {
        ((DomainEventHandler<Object>) handler).handle(event);
    }

    /**
     * 领域事件处理器
     * <p>
     * Domain Event Handler
     * <p>
     * 业务模块实现本接口并注册为 Spring Bean, 即可被分发器自动路由
     * <p>
     * Business modules implement this interface and register it as a Spring Bean
     * to be routed automatically by the dispatcher
     *
     * @param <T> 处理的事件类型 event type to handle
     * @Author Dwl
     * @Version 1.0
     * @Since 2026-09-01 11:02
     */
    public interface DomainEventHandler<T> {

        /**
         * 处理领域事件
         * Handle the domain event
         *
         * @param event 领域事件 domain event
         */
        void handle(T event);

        /**
         * 声明处理的事件类型(与事件类型路由键对应)
         * Declare the event type to handle (matches the event type routing key)
         *
         * @return 事件类型 event type class
         */
        Class<T> eventType();

    }

}
