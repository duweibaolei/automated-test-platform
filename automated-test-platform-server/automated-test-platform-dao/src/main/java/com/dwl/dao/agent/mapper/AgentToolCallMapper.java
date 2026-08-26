package com.dwl.dao.agent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.agent.entity.AgentToolCall;
import org.apache.ibatis.annotations.Mapper;

/**
 * Agent 工具调用 Mapper 接口
 * <p>
 * Agent Tool Call Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:02
 */
@Mapper
public interface AgentToolCallMapper extends BaseMapper<AgentToolCall> {
}
