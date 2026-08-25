package com.dwl.service.system.create.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwl.common.enums.ErrorCode;
import com.dwl.common.exception.BusinessException;
import com.dwl.dao.mapper.system.SysUserMapper;
import com.dwl.model.dto.system.UserCreateDTO;
import com.dwl.model.entity.system.SysUser;
import com.dwl.service.system.create.CreateSysUserRoleService;
import com.dwl.service.system.create.CreateSysUserService;
import com.dwl.service.system.delete.DeleteSysUserRoleService;
import com.dwl.service.system.query.QuerySysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

/**
 * 创建系统用户 服务实现类
 * Create System User Service Implementation
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-24 00:51
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CreateSysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements CreateSysUserService {

    private final QuerySysUserService querySysUserService;
    private final CreateSysUserRoleService createSysUserRoleService;
    private final DeleteSysUserRoleService deleteSysUserRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(UserCreateDTO dto) {
        SysUser existing = querySysUserService.getByUsername(dto.getUsername());
        if (Objects.nonNull(existing)) {
            throw new BusinessException(ErrorCode.CONFLICT, """
                    用户名已存在
                    User name already exists
                    """);
        }
        SysUser sysUser = SysUser.builder().
                username(dto.getUsername()).
                password(dto.getPassword()).
                realName(dto.getRealName()).
                email(dto.getEmail()).
                build();

        this.save(sysUser);

        /* 分配角色
         * Assign roles
         */
        if (!CollectionUtils.isEmpty(dto.getRoles())) {
            deleteSysUserRoleService.removeRolesByUserId(sysUser.getId());
            createSysUserRoleService.assignRolesToUser(sysUser.getId(), dto.getRoles());
        }

        return sysUser.getId();
    }


}
