package com.dwl.dao.test_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.test_management.aggregate.TestDataSet;
import org.apache.ibatis.annotations.Mapper;

/**
 * 测试数据集 Mapper 接口
 * <p>
 * Test Data Set Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:38
 */
@Mapper
public interface TestDataSetMapper extends BaseMapper<TestDataSet> {
}
