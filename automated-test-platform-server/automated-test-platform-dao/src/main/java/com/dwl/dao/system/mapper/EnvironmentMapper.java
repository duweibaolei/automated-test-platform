package com.dwl.dao.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.system.aggregate.Environment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 环境配置 Mapper 接口
 * Environment Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:20
 */
@Mapper
public interface EnvironmentMapper extends BaseMapper<Environment> {
}
