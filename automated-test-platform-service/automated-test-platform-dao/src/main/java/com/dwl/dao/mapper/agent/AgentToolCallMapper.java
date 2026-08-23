package com.dwl.dao.mapper.agent;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.agent.AgentToolCall;
import org.apache.ibatis.annotations.Mapper;

/**
 * Agent 工具调用记录
 * Agent Tool Call Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:48
 */
@Mapper
public interface AgentToolCallMapper extends BaseMapper<AgentToolCall> {
}
