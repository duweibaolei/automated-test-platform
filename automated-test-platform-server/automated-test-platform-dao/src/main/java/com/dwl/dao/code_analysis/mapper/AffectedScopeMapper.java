package com.dwl.dao.code_analysis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.code_analysis.entity.AffectedScope;
import org.apache.ibatis.annotations.Mapper;

/**
 * 影响范围 Mapper 接口
 * Affected Scope Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:03
 */
@Mapper
public interface AffectedScopeMapper extends BaseMapper<AffectedScope> {
}
