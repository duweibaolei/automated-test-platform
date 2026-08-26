package com.dwl.dao.agent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.agent.aggregate.AgentMemory;
import org.apache.ibatis.annotations.Mapper;

/**
 * Agent 长期记忆 Mapper 接口
 * Agent Memory Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:01
 */
@Mapper
public interface AgentMemoryMapper extends BaseMapper<AgentMemory> {
}
