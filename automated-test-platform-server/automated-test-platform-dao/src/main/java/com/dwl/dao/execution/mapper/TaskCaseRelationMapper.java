package com.dwl.dao.execution.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.execution.entity.TaskCaseRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务用例关联 Mapper 接口
 * <p>
 * Task Case Relation Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:09
 */
@Mapper
public interface TaskCaseRelationMapper extends BaseMapper<TaskCaseRelation> {
}
