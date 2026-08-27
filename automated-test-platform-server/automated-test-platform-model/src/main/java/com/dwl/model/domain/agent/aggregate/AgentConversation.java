package com.dwl.model.domain.agent.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.agent.AgentMemorySourceType;
import com.dwl.common.enums.agent.ConversationRole;
import com.dwl.model.domain.agent.entity.AgentToolCall;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Agent 对话记录聚合根
 * <p>
 * Agent Conversation Aggregate Root
 * <p>
 * 智能体域的聚合根, 记录 Agent 与用户之间的对话历史
 * <p>
 * 包含：会话 ID、角色、消息内容、Agent 类型、子 Agent 结果、模型版本、Token 使用量、工具调用记录
 * <p>
 * Aggregate root of the Agent domain, recording conversation history between
 * <p>
 * Agent and user. Contains: session ID, role, message content, agent type,
 * sub-agent results, model version, token usage, tool call records
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("agent_conversation")
@Schema(description = """
        Agent 对话记录聚合根
        Agent Conversation Aggregate Root
        """)
public class AgentConversation extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            对话 ID
            Conversation ID
            """)
    private Long id;

    @Schema(description = """
            会话 ID
            Session ID
            """, example = "CA-20260704-0001")
    private String sessionId;

    @Schema(description = """
            Role
            """, example = "user", implementation = ConversationRole.class)
    private String role;

    @Schema(description = """
            Message content
            """)
    private String content;

    @Schema(description = """
            Agent type
            """,
            example = "risk_analysis",
            implementation = AgentMemorySourceType.class)
    private String agentType;

    @Schema(description = """
            子 Agent 结果
            Sub-agent results (JSON)
            """)
    private String subResults;

    @Schema(description = """
            LLM 模型版本
            LLM model version
            """, example = "gpt-4o")
    private String modelVersion;

    @Schema(description = """
            Token 使用量
            Token usage (JSON)
            """)
    private String tokenUsage;

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

    @Builder.Default
    private transient List<AgentToolCall> toolCalls = new ArrayList<>();

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建 Agent 对话记录
     * <p>
     * Factory Method: Create agent conversation record
     *
     * @param sessionId    会话 ID
     *                     Session ID
     * @param role         Role
     * @param content      Message content
     * @param agentType    Agent type
     * @param modelVersion LLM model version
     * @param tokenUsage   Token 使用量
     *                     Token usage
     * @return 新 Agent 对话记录
     * New agent conversation record
     */
    public static AgentConversation create(String sessionId, String role, String content, String agentType,
                                           String modelVersion, String tokenUsage) {
        return AgentConversation.builder()
                .sessionId(sessionId).role(role).content(content).agentType(agentType)
                .modelVersion(modelVersion).tokenUsage(tokenUsage).toolCalls(new ArrayList<>()).build();
    }

    /**
     * 创建用户消息
     * <p>
     * Create user message
     *
     * @param sessionId    Session ID
     * @param content      Message content
     * @param agentType    Agent type
     * @param modelVersion LLM model version
     * @param tokenUsage   Token usage
     * @return 用户对话记录
     * User conversation record
     */
    public static AgentConversation createUserMessage(String sessionId, String content, String agentType,
                                                      String modelVersion, String tokenUsage) {
        return create(sessionId, ConversationRole.USER.getCode(), content, agentType, modelVersion, tokenUsage);
    }

    /**
     * 创建 Agent 回复消息
     * <p>
     * Create Agent reply message
     *
     * @param sessionId    Session ID
     * @param content      Message content
     * @param agentType    Agent type
     * @param modelVersion LLM model version
     * @param tokenUsage   Token usage
     * @return Agent 对话记录
     * Agent conversation record
     */
    public static AgentConversation createAgentMessage(String sessionId, String content, String agentType,
                                                       String modelVersion, String tokenUsage) {
        return create(sessionId, ConversationRole.AGENT.getCode(), content, agentType, modelVersion, tokenUsage);
    }

    /**
     * 添加工具调用记录
     * <p>
     * Add tool call record
     *
     * @param toolName     Tool name
     * @param inputHash    Input hash
     * @param outputHash   Output hash
     * @param latencyMs    延迟 (毫秒)
     *                     Latency (ms)
     * @param status       Status
     * @param errorMessage Error message
     */
    public void addToolCall(String toolName, String inputHash, String outputHash, Integer latencyMs, String status, String errorMessage) {
        this.toolCalls.add(AgentToolCall.create(this.sessionId, this.agentType, toolName, inputHash, outputHash, latencyMs, status, errorMessage));
    }

    /**
     * 获取工具调用列表
     * <p>
     * Get tool call list
     *
     * @return 不可变工具调用列表
     * Unmodifiable tool call list
     */
    public List<AgentToolCall> getToolCalls() {
        return Collections.unmodifiableList(this.toolCalls);
    }

    /**
     * 判断是否为用户消息
     * <p>
     * Check if it is a user message
     *
     * @return true if user message
     */
    public boolean isUserMessage() {
        return ConversationRole.USER.getCode().equals(this.role);
    }

    /**
     * 判断是否为 Agent 消息
     * <p>
     * Check if it is an Agent message
     *
     * @return true if Agent message
     */
    public boolean isAgentMessage() {
        return ConversationRole.AGENT.getCode().equals(this.role);
    }

}
