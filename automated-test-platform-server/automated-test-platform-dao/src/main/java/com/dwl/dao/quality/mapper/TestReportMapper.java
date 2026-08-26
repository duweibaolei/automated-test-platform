package com.dwl.dao.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.quality.aggregate.TestReport;
import org.apache.ibatis.annotations.Mapper;

/**
 * 测试报告 Mapper 接口
 * <p>
 * Test Report Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:17
 */
@Mapper
public interface TestReportMapper extends BaseMapper<TestReport> {
}
