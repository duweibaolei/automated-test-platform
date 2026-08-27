package com.dwl.common.enums.agent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 对话角色枚举
 * <p>
 * Conversation Role Enumeration
 * <p>
 * 用于表示 Agent 对话中消息的角色
 * <p>
 * Used to represent the role of a message in an Agent conversation.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 23:15
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        对话角色
        Conversation Role
        """)
public enum ConversationRole {

    /**
     * 用户 - 表示用户发送的消息
     * <p>
     * User - represents messages sent by the user
     */
    USER("user", "用户"),

    /**
     * Agent - 表示 Agent 回复的消息
     * <p>
     * Agent - represents messages replied by the agent
     */
    AGENT("agent", "Agent"),

    /**
     * System - 表示系统提示或内部消息
     * <p>
     * System - represents system prompts or internal messages
     */
    SYSTEM("system", "系统");

    /**
     * Role code
     */
    @Schema(description = """
            角色编码
            Role code
            """, example = "user")
    private final String code;

    /**
     * Chinese description
     */
    @Schema(description = """
            中文描述
            Chinese description
            """, example = "用户")
    private final String description;

    /**
     * Get enum by code
     *
     * @param code Role code
     * @return ConversationRole enum, or null if not found
     */
    public static ConversationRole of(String code) {
        for (ConversationRole cr : values()) {
            if (cr.code.equals(code)) {
                return cr;
            }
        }
        return null;
    }

}
