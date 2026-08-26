package com.dwl.dao.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.system.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限关联 Mapper 接口
 * <p>
 * Role Permission Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:24
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
}
