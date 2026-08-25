package com.dwl.model.vo.system;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 权限视图对象 VO（树形结构）
 * Permission View Object(Tree Structure)
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-24 00:17
 */
@Data
@Schema(description = """
        Permission View Object
        """)
public class PermissionVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            Permission ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            Parent permission ID
            """, example = "1")
    private Long parentId;

    @Schema(description = """
            Permission name
            """, example = "用户管理")
    private String permissionName;

    @Schema(description = """
            Permission code
            """, example = "system:user:list")
    private String permissionCode;

    @Schema(description = """
            资源管理: menu-菜单 button-按钮 api-接口
            Resource type: menu button api
            """, example = "menu")
    private String permissionType;

    @Schema(description = """
            Sort order
            """, example = "1")
    private Integer sortOrder;

    @Schema(description = """
            状态: 1-启用 0-禁用
            Status: 1-enabled, 0-disabled
            """, example = "1")
    private Integer status;

    @Schema(description = """
            子权限列表
            Child permission list
            """)
    private List<PermissionVO> children;


}
