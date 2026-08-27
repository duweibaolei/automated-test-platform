package com.dwl.model.domain.agent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.agent.AgentMemorySourceType;
import com.dwl.common.enums.agent.ToolCallStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * Agent 工具调用记录实体
 * <p>
 * Agent Tool Call Entity
 * <p>
 * AgentConversation 聚合根内的实体, 记录 Agent 对工具的调用情况, 用于审计和优化
 * <p>
 * Entity within the AgentConversation aggregate root, recording agent's tool calls
 * for auditing and optimization purposes
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("agent_tool_call")
@Schema(description = """
        Agent 工具调用记录实体
        Agent Tool Call Entity
        """)
public class AgentToolCall extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            调用记录 ID
            Tool call record ID
            """)
    private Long id;

    @Schema(description = """
            Session ID
            """, example = "CA-20260704-0001")
    private String sessionId;

    @Schema(description = """
            Agent type
            """,
            example = "risk_analysis",
            implementation = AgentMemorySourceType.class)
    private String agentType;

    @Schema(description = """
            Tool name
            """, example = "git_diff")
    private String toolName;

    @Schema(description = """
            """)
    private String inputHash;

    @Schema(description = """
            Output hash
            """)
    private String outputHash;

    @Schema(description = """
            耗时
            Latency (milliseconds)
            """, example = "1500")
    private Integer latencyMs;

    @Schema(description = """
            Status
            """,
            example = "success",
            implementation = ToolCallStatus.class)
    private String status;

    @Schema(description = """
            错误信息
            Error message
            """)
    private String errorMessage;

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
            Update time
            """)
    private LocalDateTime updatedAt;

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法：创建工具调用记录
     * <p>
     * Factory Method: Create tool call record
     *
     * @param sessionId    Session ID
     * @param agentType    Agent type
     * @param toolName     Tool name
     * @param inputHash    Input hash
     * @param outputHash   Output hash
     * @param latencyMs    耗时
     *                     Latency
     * @param status       Status
     * @param errorMessage Error message
     * @return New tool call record
     */
    public static AgentToolCall create(String sessionId, String agentType, String toolName, String inputHash,
                                       String outputHash, Integer latencyMs, String status, String errorMessage) {
        return AgentToolCall.builder()
                .sessionId(sessionId).agentType(agentType).toolName(toolName).inputHash(inputHash)
                .outputHash(outputHash).latencyMs(latencyMs).status(status).errorMessage(errorMessage).build();
    }

    /**
     * 创建成功工具调用
     * <p>
     * Create successful tool call
     *
     * @param sessionId  Session ID
     * @param agentType  Agent type
     * @param toolName   Tool name
     * @param inputHash  Input hash
     * @param outputHash Output hash
     * @param latencyMs  耗时
     *                   Latency
     * @return Successful tool call
     */
    public static AgentToolCall createSuccess(String sessionId, String agentType, String toolName, String inputHash,
                                              String outputHash, Integer latencyMs) {
        return create(sessionId, agentType, toolName, inputHash, outputHash, latencyMs,
                ToolCallStatus.SUCCESS.getCode(), null);
    }

    /**
     * 创建失败工具调用
     * <p>
     * Create failed tool call
     *
     * @param sessionId    Session ID
     * @param agentType    Agent type
     * @param toolName     Tool name
     * @param inputHash    Input hash
     * @param latencyMs    耗时 / Latency
     * @param errorMessage Error message
     * @return Failed tool call
     */
    public static AgentToolCall createFailed(String sessionId, String agentType, String toolName, String inputHash,
                                             Integer latencyMs, String errorMessage) {
        return create(sessionId, agentType, toolName, inputHash, null, latencyMs,
                ToolCallStatus.FAILED.getCode(), errorMessage);
    }

    /**
     * 创建超时工具调用
     * <p>
     * Create timeout tool call
     *
     * @param sessionId Session ID
     * @param agentType Agent type
     * @param toolName  Tool name
     * @param inputHash Input hash
     * @param latencyMs 耗时
     *                  Latency
     * @return Timeout tool call
     */
    public static AgentToolCall createTimeout(String sessionId, String agentType, String toolName, String inputHash,
                                              Integer latencyMs) {
        return create(sessionId, agentType, toolName, inputHash, null, latencyMs,
                ToolCallStatus.TIMEOUT.getCode(), "Tool call timed out");
    }

    /**
     * 判断是否成功
     * <p>
     * Check if success
     *
     * @return true if success
     */
    public boolean isSuccess() {
        return ToolCallStatus.isSuccess(this.status);
    }

    /**
     * 判断是否失败
     * <p>
     * Check if failed
     *
     * @return true if failed
     */
    public boolean isFailed() {
        return ToolCallStatus.isFailed(this.status);
    }

}
