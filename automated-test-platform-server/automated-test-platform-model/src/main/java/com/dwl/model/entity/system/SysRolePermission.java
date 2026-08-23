package com.dwl.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 角色权限关联实体
 * Role-Permission Association Entity
 * 对应表 sys_role_permission,角色与权限的多对多关联表
 * Maps to table sys_role_permission, many-to-many association between roles and permissions
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 17:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_permission")
@Schema(description = """
        角色权限关联
        Role-Permission Association
        """)
public class SysRolePermission extends BaseEntity {

    @Schema(description = """
            角色ID
            Role ID
            """, example = "1")
    private Long roleId;

    @Schema(description = """
            权限ID
            Permission ID
            """, example = "1")
    private Long permissionId;
}
