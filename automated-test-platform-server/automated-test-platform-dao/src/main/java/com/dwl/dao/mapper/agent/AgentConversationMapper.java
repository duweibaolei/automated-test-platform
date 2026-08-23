package com.dwl.dao.mapper.agent;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.agent.AgentConversation;
import org.apache.ibatis.annotations.Mapper;

/**
 * Agent对话记录
 * Agent Conversation Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:46
 */
@Mapper
public interface AgentConversationMapper extends BaseMapper<AgentConversation> {
}
