package com.dwl.dao.execution.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.execution.aggregate.TestTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * 测试任务 Mapper 接口
 * <p>
 * Test Task Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:11
 */
@Mapper
public interface TestTaskMapper extends BaseMapper<TestTask> {
}
