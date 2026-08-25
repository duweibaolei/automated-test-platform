package com.dwl.service.system.create;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dwl.model.dto.system.UserCreateDTO;
import com.dwl.model.entity.system.SysUser;

/**
 * 创建系统用户 服务接口
 * Create System User Service Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-24 00:46
 */
public interface CreateSysUserService extends IService<SysUser> {

    /**
     * Create User
     *
     * @param dto Create user request
     * @return New user ID
     */
    Long createUser(UserCreateDTO dto);

}
