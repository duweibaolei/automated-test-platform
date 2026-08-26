package com.dwl.dao.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.system.aggregate.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色配置 Mapper 接口
 * Role Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:23
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
