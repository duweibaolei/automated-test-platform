package com.dwl.dao.mapper.system;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.system.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限关联
 * Role-Permission Association Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:17
 */
@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
}
