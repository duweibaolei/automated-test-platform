package com.dwl.dao.mapper.execute;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.execute.TaskExecution;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务执行记录
 * Task Execution Record Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:40
 */
@Mapper
public interface TaskExecutionMapper extends BaseMapper<TaskExecution> {
}
