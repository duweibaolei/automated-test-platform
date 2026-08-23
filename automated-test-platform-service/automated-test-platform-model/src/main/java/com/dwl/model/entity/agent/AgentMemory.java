package com.dwl.model.entity.agent;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

/**
 * Agent 长期记忆实体
 * Agent Memory Entity
 * 对应表 agent_memory,存储Agent的历史分析模式与反馈学习数据
 * Maps to table agent_memory, storing Agent's historical analysis patterns and feedback learning data
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 16:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("agent_memory")
@Schema(description = """
        Agent长期记忆
        Agent Memory
        """)
public class AgentMemory extends BaseEntity {

    @Schema(description = """
            记忆类型: pattern preference feedback
            Memory type: pattern preference feedback
            """, example = "pattern")
    private String memoryType;

    @Schema(description = """
            来源类型
            Source type
            """, example = "risk_analysis")
    private String sourceType;

    @Schema(description = """
            来源业务ID
            Source business ID
            """)
    private Long sourceId;

    @Schema(description = """
            模式键(如模块路径)
            Pattern key
            """, example = "src/main/java/com/dwl/service")
    private String patternKey;

    @Schema(description = """
            模式值(JSON)
            Pattern value (JSON)
            """)
    private String patternValue;

    @Schema(description = """
            置信度
            Confidence
            """, example = "0.85")
    private BigDecimal confidence;

    @Schema(description = """
            命中次数
            Hit count
            """, example = "5")
    private Integer hitCount;
}
