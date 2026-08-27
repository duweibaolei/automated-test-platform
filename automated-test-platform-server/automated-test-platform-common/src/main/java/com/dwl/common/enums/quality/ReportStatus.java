package com.dwl.common.enums.quality;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 测试报告状态枚举
 * <p>
 * Test Report Status Enumeration
 * <p>
 * 用于表示测试报告的生命周期状态。
 * <p>
 * Used to represent the lifecycle status of a test report.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 23:55
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        测试报告状态
        Test Report Status
        """)
public enum ReportStatus {

    /**
     * 草稿 - 报告正在生成中或未发布
     * Draft - report is being generated or not published
     */
    DRAFT("draft", "草稿"),

    /**
     * 已发布 - 报告已发布可供查看
     * Published - report is published and available for viewing
     */
    PUBLISHED("published", "已发布");

    /**
     * Status code
     */
    @Schema(description = """
            Status code
            """, example = "draft")
    private final String code;

    /**
     * Chinese description
     */
    @Schema(description = """
            Chinese description
            """, example = "草稿")
    private final String description;

    /**
     * Get enum by code
     *
     * @param code Status code
     * @return ReportStatus enum, or null if not found
     */
    public static ReportStatus of(String code) {
        for (ReportStatus rs : values()) {
            if (rs.code.equals(code)) {
                return rs;
            }
        }
        return null;
    }

}
