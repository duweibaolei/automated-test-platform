package com.dwl.model.domain.system.event;

import com.dwl.common.ddd.DomainEvent;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;

/**
 * 用户已删除事件
 * <p>
 * User Deleted Event
 * <p>
 * 当 User 聚合根调用 delete() 逻辑删除时发布
 * 下游可用于: 清理读模型、清理关联数据、注销第三方账号
 * <p>
 * Published when User aggregate root calls delete() for logical delete
 * <p>
 * Downstream uses: clean read model, clean associated data, deactivate 3rd-party accounts
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:20
 */
@Getter
@ToString(callSuper = true)
public class UserDeletedEvent extends DomainEvent {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * User ID
     */
    private final Long userId;

    /**
     * Username
     */
    private final String username;

    /**
     * 构造函数
     * Constructor
     *
     * @param userId   User ID
     * @param username Username
     */
    public UserDeletedEvent(Long userId, String username) {
        super();
        this.userId = userId;
        this.username = username;
    }

}
