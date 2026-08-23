package com.dwl.dao.mapper.agent;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.agent.AgentMemory;
import org.apache.ibatis.annotations.Mapper;

/**
 * Agent 长期记忆
 * Agent Memory Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:47
 */
@Mapper
public interface AgentMemoryMapper extends BaseMapper<AgentMemory> {
}
