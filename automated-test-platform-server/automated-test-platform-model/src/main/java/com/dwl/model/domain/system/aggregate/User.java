package com.dwl.model.domain.system.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.EnableStatus;
import com.dwl.model.domain.system.entity.UserRole;
import com.dwl.model.domain.system.event.UserCreatedEvent;
import com.dwl.model.domain.system.event.UserDeletedEvent;
import com.dwl.model.domain.system.event.UserStatusChangedEvent;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 用户聚合根
 * User Aggregate Root
 * <p>
 * 系统管理域的核心聚合根, 封装用户的业务规则
 * 外部只能通过 User 聚合根操作用户及其角色关联, 不能直接操作 UserRole 实体
 * <p>
 * Core aggregate root of the System Management domain, encapsulating user business rules.
 * External objects can only operate on users and their role associations through the User
 * aggregate root, not directly on UserRole entities.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 19:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
@Schema(description = """
        用户聚合根
        User Aggregate Root
        """)
public class User extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            User ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            Username
            """, example = "admin")
    private String username;

    @Schema(description = """
            Password (encrypted)
            """)
    private String password;

    @Schema(description = """
            真实姓名
            Real name
            """, example = "管理员")
    private String realName;

    @Schema(description = """
            Email
            """, example = "admin@example.com")
    private String email;

    @Schema(description = """
            头像 URL
            Avatar URL
            """)
    private String avatar;

    @Schema(description = """
            Status: 1-enabled, 0-disabled
            """, example = "1", implementation = EnableStatus.class)
    private Integer status;

    @Schema(description = """
            最后登录时间
            Last login time
            """)
    private LocalDateTime lastLoginTime;

    @Schema(description = """
            Logical delete flag
            """, example = "0",
            implementation = DeletedStatus.class)
    private Integer isDelete;

    @Schema(description = """
            Create time
            """)
    private LocalDateTime createdAt;

    @Schema(description = """
            Update time
            """)
    private LocalDateTime updatedAt;

    /**
     * 用户角色关联列表
     * <p>
     * User-role association list
     */
    @Builder.Default
    private transient List<UserRole> roles = new ArrayList<>();

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建用户
     * <p>
     * Factory Method: Create user
     *
     * @param username Username
     * @param password 加密密码 / Encrypted password
     * @param realName 真实姓名 / Real name
     * @param email    Email
     * @param roleIds  初始角色 ID 列表
     *                 Initial role ID list
     * @return 新用户聚合根
     * New User aggregate root
     */
    public static User create(String username, String password, String realName,
                              String email, List<Long> roleIds) {
        User user = User.builder()
                .username(username)
                .password(password)
                .realName(realName)
                .email(email)
                .status(EnableStatus.ENABLED.getValue())
                .roles(new ArrayList<>())
                .build();

        if (Objects.nonNull(roleIds) && !roleIds.isEmpty()) {
            user.assignRoles(roleIds);
        }

        user.registerEvent(new UserCreatedEvent(user));
        return user;
    }

    /**
     * 分配角色 (先清空再分配, 保证幂等)
     * <p>
     * Assign roles (clear first then assign, ensures idempotency)
     *
     * @param roleIds 角色 ID 列表
     *                Role ID list
     */
    public void assignRoles(List<Long> roleIds) {
        this.roles.clear();
        if (Objects.nonNull(roleIds)) {
            roleIds.forEach(roleId -> this.roles.add(UserRole.create(this.id, roleId)));
        }
    }

    /**
     * 启用用户
     * Enable user
     */
    public void enable() {
        if (Objects.nonNull(this.status) && this.status == EnableStatus.ENABLED.getValue()) {
            return;
        }
        Integer oldStatus = this.status;
        this.status = EnableStatus.ENABLED.getValue();
        this.registerEvent(new UserStatusChangedEvent(this.id, oldStatus, EnableStatus.ENABLED.getValue()));
    }

    /**
     * 禁用用户
     * Disable user
     */
    public void disable() {
        if (Objects.nonNull(this.status) && this.status == EnableStatus.DISABLED.getValue()) {
            return;
        }
        Integer oldStatus = this.status;
        this.status = EnableStatus.DISABLED.getValue();
        this.registerEvent(new UserStatusChangedEvent(this.id, oldStatus, EnableStatus.DISABLED.getValue()));
    }

    /**
     * 删除用户 (逻辑删除)
     * Delete user (logical delete)
     */
    public void delete() {
        this.isDelete = DeletedStatus.DELETED.getValue();
        this.registerEvent(new UserDeletedEvent(this.id, this.username));
    }

    /**
     * 更新最后登录时间
     * <p>
     * Update last login time
     */
    public void updateLastLoginTime() {
        this.lastLoginTime = LocalDateTime.now();
    }

    /**
     * 获取不可变的角色列表
     * <p>
     * Get unmodifiable role list
     *
     * @return 不可变角色列表
     * Unmodifiable role list
     */
    public List<UserRole> getRoles() {
        return Collections.unmodifiableList(this.roles);
    }

}
