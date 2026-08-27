package com.dwl.common.enums.quality;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 失败原因枚举
 * <p>
 * Failure reason enumeration
 * <p>
 * 定义测试失败的分类原因：
 * - bug: 真实缺陷，需要修复
 * - flaky: 不稳定用例，需要治理
 * - env: 环境问题，需要优化环境配置
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-28 15:30
 */
@Getter
@AllArgsConstructor
public enum FailureReason {

    /**
     * 真实缺陷
     * Real bug - needs to be fixed
     */
    BUG("bug", "真实缺陷，需要修复"),

    /**
     * 不稳定用例
     * Flaky test - needs governance
     */
    FLAKY("flaky", "不稳定用例，需要治理"),

    /**
     * 环境问题
     * Environment issue - needs environment configuration optimization
     */
    ENV("env", "环境问题，需要优化环境配置");

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
     * @return 枚举值 Enum value, or null if not found
     */
    public static FailureReason fromCode(String code) {
        for (FailureReason reason : values()) {
            if (reason.getCode().equals(code)) {
                return reason;
            }
        }
        return null;
    }

    /**
     * 判断是否存在该编码
     * <p>
     * Check if code exists
     *
     * @param code Code
     * @return Whether it exists
     */
    public static boolean exists(String code) {
        return Objects.nonNull(fromCode(code));
    }
}
