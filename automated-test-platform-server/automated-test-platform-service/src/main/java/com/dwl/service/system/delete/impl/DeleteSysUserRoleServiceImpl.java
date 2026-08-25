package com.dwl.service.system.delete.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwl.dao.mapper.system.SysUserRoleMapper;
import com.dwl.model.entity.system.SysUserRole;
import com.dwl.service.system.delete.DeleteSysUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除用户角色关联 服务实现类
 * Delete User Role Association Service Implementation
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-24 01:32
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteSysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements DeleteSysUserRoleService {

    /**
     * 根据用户ID移除其所有角色关联
     * Remove all role associations for a user by user id
     *
     * @param userId User ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeRolesByUserId(Long userId) {
        Wrapper<SysUserRole> wrapper = new LambdaUpdateWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId);
        this.remove(wrapper);
    }
}
