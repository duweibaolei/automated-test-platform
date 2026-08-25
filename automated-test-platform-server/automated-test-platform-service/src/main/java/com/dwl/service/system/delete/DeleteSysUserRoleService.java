package com.dwl.service.system.delete;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dwl.model.entity.system.SysUserRole;

/**
 * 删除用户角色关联 服务接口
 * Delete user Role Association Service Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-24 01:20
 */
public interface DeleteSysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户ID移除其所有角色关联
     * Remove all role associations for a user by user id
     *
     * @param userId User ID
     */
    void removeRolesByUserId(Long userId);
}
