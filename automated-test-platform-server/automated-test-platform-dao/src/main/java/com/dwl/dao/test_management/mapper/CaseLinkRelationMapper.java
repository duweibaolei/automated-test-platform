package com.dwl.dao.test_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.test_management.entity.CaseLinkRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用例与链路关联 Mapper 接口
 * Case Link Relation Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:29
 */
@Mapper
public interface CaseLinkRelationMapper extends BaseMapper<CaseLinkRelation> {
}
