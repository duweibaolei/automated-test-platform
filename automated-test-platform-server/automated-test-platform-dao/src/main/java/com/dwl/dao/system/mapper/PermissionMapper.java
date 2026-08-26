package com.dwl.dao.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.system.aggregate.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限配置 Mapper 接口
 * <p>
 * Permission Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:22
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
