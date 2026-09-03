package com.dwl.service.system.application.vo;

import com.dwl.common.enums.EnableStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 角色视图对象
 * Role View Object
 * <p>
 * 应用层出参，不暴露领域对象包含角色基本信息和权限列表
 * Application layer output parameter, does not expose domain objects, contains role basic information and permission list
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
@Data
@Builder
@Schema(description = """
        角色视图对象
        Role View Object
        """)
public class RoleVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            角色 ID
            Role ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            角色编码
            Role code
            """, example = "ADMIN")
    private String roleCode;

    @Schema(description = """
            角色名称
            Role name
            """, example = "管理员")
    private String roleName;

    @Schema(description = """
            描述
            Description
            """, example = "系统管理员")
    private String description;

    @Schema(description = """
            状态：1-启用 0-禁用
            Status: 1-enabled, 0-disabled
            """, example = "1", implementation = EnableStatus.class)
    private Integer status;

    @Schema(description = """
            创建时间
            Created time
            """)
    private LocalDateTime createdAt;

    @Schema(description = """
            权限列表
            Permission list
            """)
    private List<PermissionVO> permissions;
}
