package com.dwl.service.system.update.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwl.common.enums.ErrorCode;
import com.dwl.common.exception.BusinessException;
import com.dwl.dao.mapper.system.SysUserMapper;
import com.dwl.model.dto.system.UserUpdateDTO;
import com.dwl.model.entity.system.SysUser;
import com.dwl.service.system.create.CreateSysUserRoleService;
import com.dwl.service.system.delete.DeleteSysUserRoleService;
import com.dwl.service.system.query.QuerySysUserService;
import com.dwl.service.system.update.UpdateSysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

/**
 * 更新用户角色关联 服务实现类
 * Update User Role Association Service Implementation
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 01:36
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateSysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UpdateSysUserService {

    private final QuerySysUserService querySysUserService;
    private final DeleteSysUserRoleService deleteSysUserRoleService;
    private final CreateSysUserRoleService createSysUserRoleService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(Long id, UserUpdateDTO dto) {
        SysUser sysUser = querySysUserService.getById(id);
        if (Objects.isNull(sysUser)) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }
        BeanUtils.copyProperties(dto, sysUser);
        this.updateById(sysUser);
        /* 分配角色
         * Assign roles
         */
        if (!CollectionUtils.isEmpty(dto.getRoles())) {
            deleteSysUserRoleService.removeRolesByUserId(sysUser.getId());
            createSysUserRoleService.assignRolesToUser(sysUser.getId(), dto.getRoles());
        }
        log.info(" User updated successfully, ID: {}", id);
    }

    @Override
    public void resultPassword(Long id, String newPassword) {

    }

    @Override
    public void updateLastLoginTime(Long id) {

    }

    @Override
    public void updateStatus(Long id, Integer status) {

    }
}
