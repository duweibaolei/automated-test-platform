package com.dwl.dao.test_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.test_management.aggregate.CaseTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用例标签 Mapper 接口
 * Case Tag Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:31
 */
@Mapper
public interface CaseTagMapper extends BaseMapper<CaseTag> {
}
