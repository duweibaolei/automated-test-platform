package com.dwl.model.entity.agent;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Agent 对话记录实体
 * Agent Conversation Entity
 * 对应表 agent_conversation,记录 Agent 与用户之间的对话历史
 * Maps to table agent_conversation, recording conversation history between Agent and users
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 16:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("agent_conversation")
@Schema(description = """
        Agent 对话记录
        Agent Conversation
        """)
public class AgentConversation extends BaseEntity {

    @Schema(description = """
            会话 ID
            Session ID
            """, example = "CA-20260704-0001")
    private String sessionId;

    @Schema(description = """
            Role: user agent system
            """, example = "user")
    private String role;

    @Schema(description = """
            消息内容
            Message content
            """)
    private String content;

    @Schema(description = """
            Agent 类型
            Agent type
            """, example = "risk_analysis")
    private String agentType;

    @Schema(description = """
            子 Agent 结果(JSON)
            Sub-agent results (JSON)
            """)
    private String subResults;

    @Schema(description = """
            LLM 模型版本
            LLM model version
            """, example = "gpt-4o")
    private String modelVersion;

    @Schema(description = """
            Token 使用量(JSON)
            Token usage (JSON)
            """)
    private String tokenUsage;
}
