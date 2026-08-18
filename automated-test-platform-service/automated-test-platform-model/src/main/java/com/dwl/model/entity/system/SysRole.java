package com.dwl.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 系统角色实体
 * System Role Entity
 * 对应表 sys_role,定义系统角色及其基本信息
 * Maps to table sys_role, defining system roles and their basic information
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 12:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
@Schema(description = """
        系统角色
        System Role
        """)
public class SysRole extends BaseEntity {

    @Schema(description = """
            角色编码
            Role code
            """, example = "admin")
    private String roleCode;

    @Schema(description = """
            角色名称
            Role name
            """, example = "管理员")
    private String roleName;

    @Schema(description = """
            描述
            Description
            """)
    private String description;

    @Schema(description = """
            状态: 1-启用 0-禁用
            Status: 1-enabled, 0-disabled
            """, example = "1")
    private Integer status;

}
