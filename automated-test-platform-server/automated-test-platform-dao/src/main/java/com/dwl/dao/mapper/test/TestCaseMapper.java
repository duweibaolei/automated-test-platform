package com.dwl.dao.mapper.test;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.test.TestCase;
import org.apache.ibatis.annotations.Mapper;

/**
 * 测试用例
 * Test Case Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:34
 */
@Mapper
public interface TestCaseMapper extends BaseMapper<TestCase> {
}
