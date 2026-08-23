package com.dwl.model.entity.agent;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Agent 工具调用记录实体
 * Agent Tool Call Entity
 * 对应表 agent_tool_call,记录 Agent 对工具的调用情况,用于审计和优化
 * Maps to table agent_tool_call, recording Agent's tool invocation for auditing and optimization
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 16:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("agent_tool_call")
@Schema(description = """
        Agent 工具调用记录
        Agent Tool Call
        """)
public class AgentToolCall extends BaseEntity {


    @Schema(description = """
            会话 ID
            Session ID
            """, example = "CA-20260704-0001")
    private String sessionId;

    @Schema(description = """
            Agent 类型
            Agent type
            """, example = "risk_analysis")
    private String agentType;

    @Schema(description = """
            工具名称
            Tool name
            """, example = "git_diff")
    private String toolName;

    @Schema(description = """
            输入哈希
            Input hash
            """)
    private String inputHash;

    @Schema(description = """
            输出哈希
            Output hash
            """)
    private String outputHash;

    @Schema(description = """
            耗时(毫秒)
            Latency in ms
            """, example = "1500")
    private Integer latencyMs;

    @Schema(description = """
            状态: success failed timeout
            Status: success failed timeout
            """, example = "success")
    private String status;

    @Schema(description = """
            错误信息
            Error message
            """)
    private String errorMessage;

}
