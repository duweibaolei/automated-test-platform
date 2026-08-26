package com.dwl.dao.code_analysis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.code_analysis.aggregate.ChangeAnalysis;
import org.apache.ibatis.annotations.Mapper;

/**
 * 变更分析 Mapper 接口
 * <p>
 * Change Analysis Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:05
 */
@Mapper
public interface ChangeAnalysisMapper extends BaseMapper<ChangeAnalysis> {
}
