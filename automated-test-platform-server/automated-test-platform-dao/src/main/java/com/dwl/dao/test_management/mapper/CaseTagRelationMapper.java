package com.dwl.dao.test_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.test_management.entity.CaseTagRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用例标签关联 Mapper 接口
 * Case Tag Relation Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:32
 */
@Mapper
public interface CaseTagRelationMapper extends BaseMapper<CaseTagRelation> {
}
