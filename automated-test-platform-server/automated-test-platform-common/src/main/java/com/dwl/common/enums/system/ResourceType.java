package com.dwl.common.enums.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 权限资源类型枚举
 * <p>
 * Permission resource type enumeration
 * <p>
 * 定义权限的三种资源类型：
 * - menu: 菜单权限
 * - button: 按钮权限
 * - api: 接口权限
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-28 16:00
 */
@Getter
@AllArgsConstructor
public enum ResourceType {

    /**
     * 菜单权限
     * Menu permission
     */
    MENU("menu", "菜单权限"),

    /**
     * 按钮权限
     * Button permission
     */
    BUTTON("button", "按钮权限"),

    /**
     * 接口权限
     * API permission
     */
    API("api", "接口权限");

    /**
     * Code
     */
    private final String code;

    /**
     * Description
     */
    private final String description;

    /**
     * Get enum by code
     *
     * @param code Code
     * @return Enum value, or null if not found
     */
    public static ResourceType fromCode(String code) {
        for (ResourceType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    /**
     * Check if code exists
     *
     * @param code Code
     * @return Whether it exists
     */
    public static boolean exists(String code) {
        return Objects.nonNull(fromCode(code));
    }
}
