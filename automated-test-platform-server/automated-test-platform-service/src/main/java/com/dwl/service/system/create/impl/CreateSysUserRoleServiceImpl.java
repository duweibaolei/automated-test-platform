package com.dwl.service.system.create.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwl.dao.mapper.system.SysUserRoleMapper;
import com.dwl.model.entity.system.SysUserRole;
import com.dwl.service.system.create.CreateSysUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建用户角色关联 服务实现类
 * Create User Role Association Service Implementation
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-24 01:47
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CreateSysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements CreateSysUserRoleService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRolesToUser(Long userId, List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return;
        }
        List<SysUserRole> roles = new ArrayList<>();
        roleIds.forEach(roleId -> {
            SysUserRole role = SysUserRole.builder().
                    userId(userId).
                    roleId(roleId).
                    build();
            roles.add(role);
        });
        this.saveOrUpdateBatch(roles);
    }

}
