package com.dwl.common.enums.quality;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 缺陷状态枚举
 * <p>
 * Defect Status Enumeration
 * <p>
 * 用于表示缺陷的生命周期状态
 * <p>
 * Used to represent the lifecycle status of a defect.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 23:45
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        缺陷状态
        Defect Status
        """)
public enum DefectStatus {

    /**
     * 已打开 - 缺陷 newly reported
     * Open - defect newly reported
     */
    OPEN("open", "已打开"),

    /**
     * 已解决 - 缺陷已修复, 待验证
     * Resolved - defect fixed, pending verification
     */
    RESOLVED("resolved", "已解决"),

    /**
     * 已关闭 - 缺陷已验证关闭
     * Closed - defect verified and closed
     */
    CLOSED("closed", "已关闭"),

    /**
     * 重新打开 - 缺陷验证未通过, 重新打开
     * Reopened - defect verification failed, reopened
     */
    REOPENED("reopened", "重新打开"),

    /**
     * 延期处理 - 缺陷暂不修复, 延期处理
     * Deferred - defect not fixed for now, deferred
     */
    DEFERRED("deferred", "延期处理"),

    /**
     * 不予处理 - 缺陷不予处理(如设计如此)
     * Won't fix - defect will not be fixed (by design)
     */
    WONT_FIX("wont_fix", "不予处理");

    /**
     * Status code
     */
    @Schema(description = """
            Status code
            """, example = "open")
    private final String code;

    /**
     * Chinese description
     */
    @Schema(description = """
            Chinese description
            """, example = "已打开")
    private final String description;

    /**
     * Get enum by code
     *
     * @param code Status code
     * @return DefectStatus enum, or null if not found
     */
    public static DefectStatus of(String code) {
        for (DefectStatus ds : values()) {
            if (ds.code.equals(code)) {
                return ds;
            }
        }
        return null;
    }

    /**
     * 判断是否为活跃状态(已打开或重新打开)
     * Check if it is an active status (open or reopened)
     *
     * @param code Status code
     * @return true if active
     */
    public static boolean isActive(String code) {
        return OPEN.code.equals(code) || REOPENED.code.equals(code);
    }

    /**
     * 判断是否为已结束状态(已关闭、延期、不予处理)
     * Check if it is an ended status (closed, deferred, won't fix)
     *
     * @param code 状 Status code
     * @return true if ended
     */
    public static boolean isEnded(String code) {
        return CLOSED.code.equals(code) || DEFERRED.code.equals(code) || WONT_FIX.code.equals(code);
    }


}
