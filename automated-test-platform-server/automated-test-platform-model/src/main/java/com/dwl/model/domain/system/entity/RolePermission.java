package com.dwl.model.domain.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 角色权限关联实体
 * <p>
 * Role-Permission Association Entity
 * <p>
 * Role 聚合根内的实体, 表示角色与权限的多对多关联
 * 外部不能直接操作此实体, 必须通过 Role 聚合根的方法
 * <p>
 * Entity within the Role aggregate root, representing the many-to-many association
 * between roles and permissions
 * <p>
 * External objects cannot operate on this entity
 * directly; must go through Role aggregate root methods
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_permission")
@Schema(description = """
        角色权限关联实体
        Role-Permission Association Entity
        """)
public class RolePermission extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            关联 ID
            Association ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            Role ID
            """, example = "1")
    private Long roleId;

    @Schema(description = """
            Permission ID
            """, example = "1")
    private Long permissionId;

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
     * 工厂方法: 创建角色权限关联
     * <p>
     * Factory Method: Create role-permission association
     *
     * @param roleId       Role ID
     * @param permissionId Permission ID
     * @return 新角色权限关联
     * New RolePermission entity
     */
    public static RolePermission create(Long roleId, Long permissionId) {
        return RolePermission.builder()
                .roleId(roleId)
                .permissionId(permissionId)
                .build();
    }

}
