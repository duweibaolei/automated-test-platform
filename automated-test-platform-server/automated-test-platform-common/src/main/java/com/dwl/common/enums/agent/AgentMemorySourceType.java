package com.dwl.common.enums.agent;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Agent 记忆来源类型枚举
 * Agent Memory Source Type Enumeration
 * <p>
 * 定义 Agent 长期记忆的来源类型,包括风险分析、根因分析和手动标记
 * Defines the source type of Agent long-term memory, including risk analysis,
 * root cause analysis, and manual marking
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 01:43
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        Agent 记忆来源类型枚举
        Agent Memory Source Type Enumeration
        """)
public enum AgentMemorySourceType {

    /**
     * 风险分析来源
     * Risk analysis source
     */
    RISK_ANALYSIS("risk_analysis", "风险分析"),

    /**
     * 根因分析来源
     * Root cause analysis source
     */
    ROOT_CAUSE("root_cause", "根因分析"),

    /**
     * 手动标记来源
     * Manual marking source
     */
    MANUAL_MARK("manual_mark", "手动标记");

    @Schema(description = """
            来源编码
            Source type code
            """, example = "risk_analysis")
    private final String code;

    @Schema(description = """
            来源描述
            Source type description
            """, example = "风险分析")
    private final String description;

}
