package com.dwl.common.enums.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 环境编码枚举
 * <p>
 * Environment code enumeration
 * <p>
 * 定义常见的测试环境类型：
 * - TEST: 测试环境
 * - STAGING: 预发布环境
 * - PRE_PRODUCTION: 准生产环境
 * - PRODUCTION: 生产环境
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-28 16:30
 */
@Getter
@AllArgsConstructor
public enum EnvCode {

    /**
     * 测试环境
     * Test environment
     */
    TEST("test", "测试环境"),

    /**
     * 开发测试环境
     * Development test environment
     */
    DEV_TEST("dev_test", "开发测试环境"),

    /**
     * 集成测试环境
     * Integration test environment
     */
    INTEGRATION("integration", "集成测试环境"),

    /**
     * 预发布环境
     * Staging environment
     */
    STAGING("staging", "预发布环境"),

    /**
     * 准生产环境
     * Pre-production environment
     */
    PRE_PRODUCTION("pre_production", "准生产环境"),

    /**
     * 生产环境
     * Production environment
     */
    PRODUCTION("production", "生产环境");

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
    public static EnvCode fromCode(String code) {
        for (EnvCode env : values()) {
            if (env.getCode().equals(code)) {
                return env;
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
