package com.dwl.service.system.application.vo;

import com.dwl.common.enums.EnableStatus;
import com.dwl.common.enums.SelectedStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


/**
 * 权限视图对象 (树形结构)
 * Permission View Object (Tree Structure)
 * <p>
 * 应用层出参，不暴露领域对象树形结构，包含子权限列表
 * Application layer output parameter, does not expose domain object tree structure, contains child permission list
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
@Data
@Builder
@Schema(description = """
        权限视图对象
        Permission View Object
        """)
public class PermissionVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            权限 ID
            Permission ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            父权限 ID
            Parent permission ID
            """, example = "1")
    private Long parentId;

    @Schema(description = """
            权限名称
            Permission name
            """, example = "用户管理")
    private String permissionName;

    @Schema(description = """
            权限编码
            Permission code
            """, example = "system:user:list")
    private String permissionCode;

    @Schema(description = """
            资源类型：menu-菜单 button-按钮 api-接口
            Resource type: menu-button-api
            """, example = "menu")
    private String permissionType;

    @Schema(description = """
            排序
            Sort order
            """, example = "1")
    private Integer sortOrder;

    @Schema(description = """
            状态：1-启用 0-禁用
            Status: 1-enabled, 0-disabled
            """, example = "1", implementation = EnableStatus.class)
    private Integer status;

    @Schema(description = """
            子权限列表
            Child permission list
            """)
    private List<PermissionVO> children;
}
