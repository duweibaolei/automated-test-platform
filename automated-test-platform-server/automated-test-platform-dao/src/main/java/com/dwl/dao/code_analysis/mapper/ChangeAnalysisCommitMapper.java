package com.dwl.dao.code_analysis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.code_analysis.entity.ChangeAnalysisCommit;
import org.apache.ibatis.annotations.Mapper;

/**
 * 变更分析与提交关联 Mapper 接口
 * Change Analysis Commit Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:04
 */
@Mapper
public interface ChangeAnalysisCommitMapper extends BaseMapper<ChangeAnalysisCommit> {
}
