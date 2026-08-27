package com.dwl.model.domain.agent.repository;

import com.dwl.common.ddd.Repository;
import com.dwl.model.domain.agent.aggregate.AgentConversation;

import java.util.Optional;

/**
 * Agent对话仓储接口
 * <p>
 * Agent Conversation Repository Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
public interface AgentConversationRepository extends Repository<AgentConversation, Long> {

    /**
     * 根据会话ID查找
     * <p>
     * Find by session ID
     */
    Optional<AgentConversation> findBySessionId(String sessionId);
}
