package com.dwl.service.system.create;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dwl.model.entity.system.SysUserRole;

import java.util.List;

/**
 * 创建用户角色关联 服务接口
 * Create user role association service interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-24 01:44
 */
public interface CreateSysUserRoleService extends IService<SysUserRole> {

    /**
     * 批量插入用户角色关联
     * Batch insert user role associations
     *
     * @param userId  User ID
     * @param roleIds Role ID list
     */
    void assignRolesToUser(Long userId, List<Long> roleIds);
}
