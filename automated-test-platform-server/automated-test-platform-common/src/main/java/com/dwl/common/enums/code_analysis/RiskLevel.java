package com.dwl.common.enums.code_analysis;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 风险等级枚举
 * <p>
 * Risk Level Enumeration
 * <p>
 * 定义测试用例或缺陷的风险等级
 * <p>
 * Defines the risk level of cases or defects, including high medium and low risk
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-06 01:40
 */
@AllArgsConstructor
@Getter
@Schema(description = """
        风险等级枚举
        Risk Level Enumeration
        """)
public enum RiskLevel {

    HIGH("high", "高风险"), 
    MEDIUM("medium", "中风险"), 
    LOW("low", "低风险");

    @Schema(description = """
            风险等级编码
            Risk level code
            """, example = "high")
    private final String code;

    @Schema(description = """
            风险等级描述
            Risk level description
            """, example = "高风险")
    private final String description;


}
