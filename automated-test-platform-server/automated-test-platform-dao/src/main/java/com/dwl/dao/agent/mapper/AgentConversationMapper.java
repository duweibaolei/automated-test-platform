package com.dwl.dao.agent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.agent.aggregate.AgentConversation;
import org.apache.ibatis.annotations.Mapper;

/**
 * Agent 对话记录 Mapper 接口
 * <p>
 * Agent Conversation Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:00
 */
@Mapper
public interface AgentConversationMapper extends BaseMapper<AgentConversation> {
}
