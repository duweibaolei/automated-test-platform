package com.dwl.dao.mapper.execute;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.execute.TaskCaseRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务-用例关联
 * Task-Case Association Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:40
 */
@Mapper
public interface TaskCaseRelationMapper extends BaseMapper<TaskCaseRelation> {
}
