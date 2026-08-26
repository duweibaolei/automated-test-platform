package com.dwl.dao.test_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.test_management.entity.CaseVersion;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用例版本历史 Mapper 接口
 * <p>
 * Case Version Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:33
 */
@Mapper
public interface CaseVersionMapper extends BaseMapper<CaseVersion> {
}
