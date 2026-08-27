package com.dwl.model.domain.agent.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.agent.AgentMemorySourceType;
import com.dwl.common.enums.agent.MemoryType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Agent 长期记忆聚合根
 * <p>
 * Agent Memory Aggregate Root
 * <p>
 * 智能体域的聚合根, 存储 Agent 的历史分析模式与反馈学习数据
 * <p>
 * 包含: 记忆类型、来源类型、模式键值、置信度、命中次数
 * <p>
 * Aggregate root of the Agent domain, storing agent's historical analysis patterns
 * and feedback learning data.
 * <p>
 * Contains: memory type, source type, pattern key-value,
 * confidence, hit count
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("agent_memory")
@Schema(description = """
        Agent 长期记忆聚合根
        Agent Memory Aggregate Root
        """)
public class AgentMemory extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            记忆 ID
            Memory ID
            """)
    private Long id;

    @Schema(description = """
            记忆类型
            Memory type
            """,
            example = "pattern",
            implementation = MemoryType.class)
    private String memoryType;

    @Schema(description = """
            来源类型
            Source type
            """,
            example = "risk_analysis",
            implementation = AgentMemorySourceType.class)
    private String sourceType;

    @Schema(description = """
            来源业务 ID
            Source business ID
            """)
    private Long sourceId;

    @Schema(description = """
            模式键
            Pattern key (e.g., module path)
            """, example = "src/main/java/com/dwl/service")
    private String patternKey;

    @Schema(description = """
            模式值
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

    @Schema(description = """
            Logical delete flag
            """,
            example = "0",
            implementation = DeletedStatus.class)
    private Integer isDelete;


    @Schema(description = """
            Create time
            """)
    private LocalDateTime createdAt;

    @Schema(description = """
            更新时间
            Update time
            """)
    private LocalDateTime updatedAt;

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建 Agent 长期记忆
     * <p>
     * Factory Method: Create agent memory
     *
     * @param memoryType   记忆类型
     *                     Memory type
     * @param sourceType   来源类型
     *                     Source type
     * @param sourceId     来源业务 ID
     *                     Source business ID
     * @param patternKey   模式键
     *                     Pattern key
     * @param patternValue 模式值
     *                     Pattern value
     * @param confidence   置信度
     *                     Confidence
     * @return 新 Agent 长期记忆
     * New agent memory
     */
    public static AgentMemory create(String memoryType, String sourceType, Long sourceId, String patternKey,
                                     String patternValue, BigDecimal confidence) {
        return AgentMemory.builder()
                .memoryType(memoryType).sourceType(sourceType).sourceId(sourceId)
                .patternKey(patternKey).patternValue(patternValue).confidence(confidence).hitCount(1).build();
    }

    /**
     * 创建模式记忆
     * <p>
     * Create pattern memory
     *
     * @param sourceType   Source type
     * @param sourceId     Source business ID
     * @param patternKey   模式键
     *                     Pattern key
     * @param patternValue 模式值
     *                     Pattern value
     * @param confidence   置信度
     *                     Confidence
     * @return 模式记忆
     * Pattern memory
     */
    public static AgentMemory createPattern(String sourceType, Long sourceId, String patternKey,
                                            String patternValue, BigDecimal confidence) {
        return create(MemoryType.PATTERN.getCode(), sourceType, sourceId, patternKey, patternValue, confidence);
    }

    /**
     * 创建偏好记忆
     * <p>
     * Create preference memory
     *
     * @param sourceType   Source type
     * @param sourceId     Source business ID
     * @param patternKey   模式键
     *                     Pattern key
     * @param patternValue 模式值
     *                     Pattern value
     * @param confidence   置信度
     *                     Confidence
     * @return 偏好记忆
     * Preference memory
     */
    public static AgentMemory createPreference(String sourceType, Long sourceId, String patternKey,
                                               String patternValue, BigDecimal confidence) {
        return create(MemoryType.PREFERENCE.getCode(), sourceType, sourceId, patternKey, patternValue, confidence);
    }

    /**
     * 创建反馈记忆
     * <p>
     * Create feedback memory
     *
     * @param sourceType   Source type
     * @param sourceId     Source business ID
     * @param patternKey   模式键
     *                     Pattern key
     * @param patternValue 模式值
     *                     Pattern value
     * @param confidence   置信度
     *                     Confidence
     * @return 反馈记忆
     * Feedback memory
     */
    public static AgentMemory createFeedback(String sourceType, Long sourceId, String patternKey,
                                             String patternValue, BigDecimal confidence) {
        return create(MemoryType.FEEDBACK.getCode(), sourceType, sourceId, patternKey, patternValue, confidence);
    }

    /**
     * 命中计数
     * <p>
     * Hit count
     */
    public void hit() {
        this.hitCount = (Objects.isNull(this.hitCount) ? 0 : this.hitCount) + 1;
    }

    /**
     * 更新置信度
     * Update confidence
     *
     * @param confidence 置信度
     *                   Confidence
     */
    public void updateConfidence(BigDecimal confidence) {
        this.confidence = confidence;
    }

    /**
     * 判断是否为模式记忆
     * <p>
     * Check if it is a pattern memory
     *
     * @return true if pattern memory
     */
    public boolean isPattern() {
        return MemoryType.PATTERN.getCode().equals(this.memoryType);
    }

    /**
     * 判断是否为偏好记忆
     * <p>
     * Check if it is a preference memory
     *
     * @return true if preference memory
     */
    public boolean isPreference() {
        return MemoryType.PREFERENCE.getCode().equals(this.memoryType);
    }

    /**
     * 判断是否为反馈记忆
     * <p>
     * Check if it is a feedback memory
     *
     * @return true if feedback memory
     */
    public boolean isFeedback() {
        return MemoryType.FEEDBACK.getCode().equals(this.memoryType);
    }

}
