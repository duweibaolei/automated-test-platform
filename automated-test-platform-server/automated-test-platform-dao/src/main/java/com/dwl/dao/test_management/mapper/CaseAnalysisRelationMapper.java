package com.dwl.dao.test_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.test_management.entity.CaseAnalysisRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用例与分析关联 Mapper 接口
 * <p>
 * Case Analysis Relation Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:28
 */
@Mapper
public interface CaseAnalysisRelationMapper extends BaseMapper<CaseAnalysisRelation> {
}
