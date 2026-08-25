package com.dwl.common.ddd;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DDD 领域事件基类</br>
 * DDD Domain Event Base Class
 * <p>
 * 领域事件表示领域中发生的有业务意义的事情,由聚合根在业务方法中产生,
 * 应用层在保存聚合根后发布,下游限界上下文异步进行消费</br>
 * Domain Events represent something business-significant that happened in the domain.
 * They are raised by aggregate roots in business methods, published by the application
 * layer after saving the aggregate root, and consumed asynchronously by downstream
 * bounded contexts
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 16:51
 */
@Data
@ToString
@Schema(description = """
        DDD 领域事件基类
        DDD Domain Event Base Class
        """)
public abstract class DomainEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            事件唯一标识
            Event unique identifier
            """)
    private final String eventId;

    @Schema(description = """
            事件发生时间
            Event occurrence timestamp
            """)
    private final LocalDateTime occurredAt;

    @Schema(description = """
            时间类型(默认取类名)
            Event type(defaults to class name)
            """)
    private final String eventType;

    protected DomainEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.occurredAt = LocalDateTime.now();
        this.eventType = this.getClass().getSimpleName();
    }

}
