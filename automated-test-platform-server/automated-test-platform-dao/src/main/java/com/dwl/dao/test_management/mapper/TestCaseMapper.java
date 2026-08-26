package com.dwl.dao.test_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.test_management.aggregate.TestCase;
import org.apache.ibatis.annotations.Mapper;

/**
 * 测试用例 Mapper 接口
 * Test Case Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:37
 */
@Mapper
public interface TestCaseMapper extends BaseMapper<TestCase> {
}
