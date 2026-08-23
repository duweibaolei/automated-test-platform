package com.dwl.dao.mapper.system;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.system.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联
 * User-Role Association Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:19
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}
