package com.dwl.dao.mapper.test;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.test.CaseTagRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用例标签关联
 * Case-Tag Association Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:29
 */
@Mapper
public interface CaseTagRelationMapper extends BaseMapper<CaseTagRelation> {
}
