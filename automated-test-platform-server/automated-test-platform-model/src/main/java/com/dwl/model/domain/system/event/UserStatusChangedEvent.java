package com.dwl.model.domain.system.event;

import com.dwl.common.ddd.DomainEvent;
import com.dwl.common.enums.EnableStatus;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.util.Objects;

/**
 * 用户状态已变更事件
 * <p>
 * User Status Changed Event
 * <p>
 * 当 User 聚合根调用 enable() 或 disable() 时发布
 * 下游可用于: 禁用用户时使其会话失效、同步读模型状态
 * <p>
 * Published when User aggregate root calls enable() or disable()
 * <p>
 * Downstream uses: invalidate sessions when disabling, sync read model status
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:21
 */
@Getter
@ToString(callSuper = true)
public class UserStatusChangedEvent extends DomainEvent {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * User ID
     */
    private final Long userId;

    /**
     * Old status
     */
    private final Integer oldStatus;

    /**
     * New status
     */
    private final Integer newStatus;

    /**
     * 构造函数
     * Constructor
     *
     * @param userId    User ID
     * @param oldStatus Old status
     * @param newStatus New status
     */
    public UserStatusChangedEvent(Long userId, Integer oldStatus, Integer newStatus) {
        super();
        this.userId = userId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    /**
     * 是否为禁用操作
     * Is this a disable operation
     *
     * @return 是否为禁用
     * true if disabling
     */
    public boolean isDisabling() {
        return Objects.nonNull(newStatus) && newStatus == EnableStatus.DISABLED.getValue();
    }

    /**
     * 是否为启用操作
     * Is this an enable operation
     *
     * @return 是否为启用 / true if enabling
     */
    public boolean isEnabling() {
        return Objects.nonNull(newStatus) && newStatus == EnableStatus.ENABLED.getValue();
    }

}
