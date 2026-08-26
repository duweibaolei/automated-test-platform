package com.dwl.dao.execution.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.execution.aggregate.TaskExecution;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务执行记录 Mapper 接口
 * <p>
 * Task Execution Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:10
 */
@Mapper
public interface TaskExecutionMapper extends BaseMapper<TaskExecution> {
}
