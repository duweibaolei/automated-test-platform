package com.dwl.common.enums.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Git 仓库认证方式枚举
 * <p>
 * Git repository credential type enumeration
 * <p>
 * 定义 Git 仓库的三种认证方式：
 * - ssh: SSH 密钥认证
 * - token: Token 认证
 * - password: 密码认证
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-28 16:05
 */
@Getter
@AllArgsConstructor
public enum CredentialType {

    /**
     * SSH 密钥认证
     * SSH key authentication
     */
    SSH("ssh", "SSH 密钥认证"),

    /**
     * Token 认证
     * Token authentication
     */
    TOKEN("token", "Token 认证"),

    /**
     * 密码认证
     * Password authentication
     */
    PASSWORD("password", "密码认证");

    /**
     * Code
     */
    private final String code;

    /**
     * Description
     */
    private final String description;

    /**
     * 根据编码获取枚举
     * <p>
     * Get enum by code
     *
     * @param code Code
     * @return Enum value, or null if not found
     */
    public static CredentialType fromCode(String code) {
        for (CredentialType type : values()) {
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
