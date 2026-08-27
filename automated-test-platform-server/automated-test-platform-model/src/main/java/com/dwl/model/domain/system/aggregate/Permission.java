package com.dwl.model.domain.system.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.EnableStatus;
import com.dwl.common.enums.system.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 权限聚合根
 * <p>
 * Permission Aggregate Root
 * <p>
 * 系统管理域的聚合根, 封装权限 (菜单/按钮/接口) 的业务规则
 * 权限采用树形结构, 通过 parentId 自引用形成层级关系
 * <p>
 * Aggregate root of the System Management domain, encapsulating permission
 * (menu/button/api) business rules.
 * <p>
 * Permissions use a tree structure,
 * forming a hierarchy through parentId self-reference.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 19:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_permission")
@Schema(description = """
        权限聚合根
        Permission Aggregate Root
        """)
public class Permission extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            权限 ID
            Permission ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            权限编码
            Permission code
            """, example = "system:user:list")
    private String permissionCode;

    @Schema(description = """
            权限名称
            Permission name
            """, example = "用户列表")
    private String permissionName;

    @Schema(description = """
            资源类型
            Resource type
            """, example = "menu",
            implementation = ResourceType.class)
    private String resourceType;

    @Schema(description = """
            父权限 ID
            Parent permission ID
            """, example = "1")
    private Long parentId;

    @Schema(description = """
            Sort order
            """, example = "1")
    private Integer sortOrder;

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
     * 子权限列表
     * <p>
     * Child permission list
     */
    @Builder.Default
    private transient List<Permission> children = new ArrayList<>();

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建权限
     * <p>
     * Factory Method: Create permission
     *
     * @param permissionCode 权限编码
     *                       Permission code
     * @param permissionName 权限名称
     *                       Permission name
     * @param resourceType   资源类型
     *                       Resource type (menu/button/api)
     * @param parentId       父权限 ID
     *                       Parent permission ID
     * @param sortOrder      Sort order
     * @return 新权限聚合根
     * New Permission aggregate root
     */
    public static Permission create(String permissionCode, String permissionName,
                                    String resourceType, Long parentId, Integer sortOrder) {

        if (!ResourceType.exists(resourceType)) {
            throw new IllegalArgumentException("Invalid resource type: " + resourceType);
        }

        return Permission.builder()
                .permissionCode(permissionCode)
                .permissionName(permissionName)
                .resourceType(resourceType)
                .parentId(parentId)
                .sortOrder(sortOrder)
                .status(EnableStatus.ENABLED.getValue())
                .children(new ArrayList<>())
                .build();
    }

    /**
     * 添加子权限
     * Add child permission
     *
     * @param child Child permission
     */
    public void addChild(Permission child) {
        if (Objects.nonNull(child)) {
            this.children.add(child);
        }
    }

    /**
     * 启用权限
     * Enable permission
     */
    public void enable() {
        if (Objects.nonNull(this.status) && this.status == EnableStatus.ENABLED.getValue()) {
            return;
        }
        this.status = EnableStatus.ENABLED.getValue();
    }

    /**
     * 禁用权限
     * Disable permission
     */
    public void disable() {
        if (Objects.nonNull(this.status) && this.status == EnableStatus.DISABLED.getValue()) {
            return;
        }
        this.status = EnableStatus.DISABLED.getValue();
    }

    /**
     * 删除权限 (逻辑删除)
     * Delete permission (logical delete)
     */
    public void delete() {
        this.isDelete = DeletedStatus.DELETED.getValue();
    }

    /**
     * 判断是否为根节点
     * <p>
     * Check if this is a root node
     *
     * @return 是否为根节点
     * true if root node
     */
    public boolean isRoot() {
        return Objects.isNull(this.parentId) || this.parentId == 0L;
    }

    /**
     * 获取不可变的子权限列表
     * <p>
     * Get unmodifiable child permission list
     *
     * @return 不可被子权限列表
     * Unmodifiable child permission list
     */
    public List<Permission> getChildren() {
        return Collections.unmodifiableList(this.children);
    }

}
