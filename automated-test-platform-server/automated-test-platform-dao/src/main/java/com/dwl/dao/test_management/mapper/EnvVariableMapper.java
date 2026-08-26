package com.dwl.dao.test_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.test_management.aggregate.EnvVariable;
import org.apache.ibatis.annotations.Mapper;

/**
 * 环境变量 Mapper 接口
 * <p>
 * Env Variable Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:34
 */
@Mapper
public interface EnvVariableMapper extends BaseMapper<EnvVariable> {
}
