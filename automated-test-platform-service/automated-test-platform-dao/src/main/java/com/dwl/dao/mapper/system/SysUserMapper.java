package com.dwl.dao.mapper.system;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 * System User Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:18
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}