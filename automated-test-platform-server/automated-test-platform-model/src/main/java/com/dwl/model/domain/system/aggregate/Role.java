package com.dwl.model.domain.system.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.EnableStatus;
import com.dwl.model.domain.system.entity.RolePermission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 角色聚合根
 * <p>
 * Role Aggregate Root
 * <p>
 * 系统管理域的聚合根, 封装角色的业务规则
 * 外部只能通过 Role 聚合根操作角色及其权限关联, 不能直接操作 RolePermission 实体
 * <p>
 * Aggregate root of the System Management domain, encapsulating role business rules
 * <p>
 * External objects can only operate on roles and their permission associations through
 * the Role aggregate root, not directly on RolePermission entities
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 19:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
@Schema(description = """
        角色聚合根
        Role Aggregate Root
        """)
public class Role extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            Role ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            Role code
            """, example = "ADMIN")
    private String roleCode;

    @Schema(description = """
            Role name
            """, example = "管理员")
    private String roleName;

    @Schema(description = """
            Description
            """, example = "系统管理员")
    private String description;

    @Schema(description = """
            Status: 1-enabled, 0-disabled
            """, example = "1", implementation = EnableStatus.class)
    private Integer status;

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
     * 角色权限关联列表
     * <p>
     * Role-permission association list
     */
    @Builder.Default
    private transient List<RolePermission> permissions = new ArrayList<>();

    /* ================================================================
     * 业务方法 / Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建角色
     * <p>
     * Factory Method: Create role
     *
     * @param roleCode      Role code
     * @param roleName      Role name
     * @param description   Description
     * @param permissionIds 初始权限 ID 列表
     *                      Initial permission ID list
     * @return 新角色聚合根
     * New Role aggregate root
     */
    public static Role create(String roleCode, String roleName, String description,
                              List<Long> permissionIds) {
        Role role = Role.builder()
                .roleCode(roleCode)
                .roleName(roleName)
                .description(description)
                .status(EnableStatus.ENABLED.getValue())
                .permissions(new ArrayList<>())
                .build();

        if (Objects.nonNull(permissionIds) && !permissionIds.isEmpty()) {
            role.assignPermissions(permissionIds);
        }
        return role;
    }

    /**
     * 分配权限 (先清空再分配, 保证幂等)
     * <p>
     * Assign permissions (clear first then assign, ensures idempotency)
     *
     * @param permissionIds 权限 ID 列表
     *                      Permission ID list
     */
    public void assignPermissions(List<Long> permissionIds) {
        this.permissions.clear();
        if (Objects.nonNull(permissionIds)) {
            permissionIds.forEach(pid -> this.permissions.add(RolePermission.create(this.id, pid)));
        }
    }

    /**
     * 启用角色
     * Enable role
     */
    public void enable() {
        if (Objects.nonNull(this.status) && this.status == EnableStatus.ENABLED.getValue()) {
            return;
        }
        this.status = EnableStatus.ENABLED.getValue();
    }

    /**
     * 禁用角色
     * Disable role
     */
    public void disable() {
        if (Objects.nonNull(this.status) && this.status == EnableStatus.DISABLED.getValue()) {
            return;
        }
        this.status = EnableStatus.DISABLED.getValue();
    }

    /**
     * 删除角色 (逻辑删除)
     * Delete role (logical delete)
     */
    public void delete() {
        this.isDelete = DeletedStatus.DELETED.getValue();
    }

    /**
     * 获取不可变的权限关联列表
     * Get unmodifiable permission association list
     *
     * @return 不可变权限列表 / Unmodifiable permission list
     */
    public List<RolePermission> getPermissions() {
        return Collections.unmodifiableList(this.permissions);
    }

}
