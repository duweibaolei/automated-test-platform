package com.dwl.model.domain.system.event;

import com.dwl.common.ddd.DomainEvent;
import com.dwl.model.domain.system.aggregate.User;
import com.dwl.model.domain.system.entity.UserRole;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户已创建事件
 * <p>
 * User Created Event
 * <p>
 * 当 User 聚合根通过工厂方法 create() 创建时发布
 * 下游可用于: 同步读模型、发送欢迎邮件、初始化用户偏好
 * <p>
 * Published when a User aggregate root is created via the factory method create()
 * <p>
 * Downstream uses: sync read model, send welcome email, init user preferences
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:19
 */
@Getter
@ToString(callSuper = true)
public class UserCreatedEvent extends DomainEvent {

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
     * Email
     */
    private final String email;

    /**
     * Role ID list
     */
    private final List<Long> roleIds;

    /**
     * 构造函数
     * Constructor
     *
     * @param user User
     */
    public UserCreatedEvent(User user) {
        super();
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roleIds = user.getRoles().stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
    }

}
