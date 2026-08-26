package com.dwl.dao.test_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.test_management.entity.CaseStep;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用例步骤 Mapper 接口
 * <p>
 * Case Step Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:30
 */
@Mapper
public interface CaseStepMapper extends BaseMapper<CaseStep> {
}
