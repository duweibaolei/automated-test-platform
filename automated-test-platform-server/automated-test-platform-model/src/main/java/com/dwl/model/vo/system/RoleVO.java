package com.dwl.model.vo.system;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Role View Object
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-24 00:04
 */
@Data
@Schema(description = """
        Role View Object
        """)
public class RoleVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            Role ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            Role Code
            """, example = "ADMIN")
    private String roleCode;

    @Schema(description = """
            Role Name
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

    @Schema(description = """
            Creation time
            """)
    private LocalDateTime createTime;

    @Schema(description = """
            权限列表
            Permission list
            """)
    private List<PermissionVO> permissions;
}
