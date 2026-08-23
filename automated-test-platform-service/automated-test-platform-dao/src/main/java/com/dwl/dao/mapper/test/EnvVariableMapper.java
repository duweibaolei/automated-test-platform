package com.dwl.dao.mapper.test;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.test.EnvVariable;
import org.apache.ibatis.annotations.Mapper;

/**
 * 环境变量
 * Environment Variable Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:32
 */
@Mapper
public interface EnvVariableMapper extends BaseMapper<EnvVariable> {
}
