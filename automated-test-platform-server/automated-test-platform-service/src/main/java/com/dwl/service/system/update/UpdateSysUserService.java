package com.dwl.service.system.update;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dwl.model.dto.system.UserUpdateDTO;
import com.dwl.model.entity.system.SysUser;

/**
 * 修改系统用户 服务实现类
 * Update System User Service Implementation
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 01:34
 */

public interface UpdateSysUserService extends IService<SysUser> {

    /**
     * Update user
     *
     * @param id  User ID
     * @param dto Update user request
     */
    void updateUser(Long id, UserUpdateDTO dto);

    /**
     * 重置密码
     * Reset password
     *
     * @param id          User ID
     * @param newPassword New password
     */
    void resultPassword(Long id, String newPassword);

    /**
     * Update last login time
     *
     * @param id User ID
     */
    void updateLastLoginTime(Long id);

    /**
     * Update user status
     *
     * @param id     User ID
     * @param status 状态(1-启用 0-禁用)
     *               Status (1-enabled, 0-disabled)
     */
    void updateStatus(Long id, Integer status);

}
