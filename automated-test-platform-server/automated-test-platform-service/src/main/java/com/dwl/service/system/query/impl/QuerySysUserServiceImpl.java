package com.dwl.service.system.query.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwl.common.enums.ErrorCode;
import com.dwl.common.exception.BusinessException;
import com.dwl.common.result.PageResult;
import com.dwl.dao.mapper.system.SysUserMapper;
import com.dwl.model.entity.system.SysRole;
import com.dwl.model.entity.system.SysUser;
import com.dwl.model.entity.system.SysUserRole;
import com.dwl.model.vo.system.RoleVO;
import com.dwl.model.vo.system.UserVO;
import com.dwl.service.system.query.QuerySysRoleService;
import com.dwl.service.system.query.QuerySysUserRoleService;
import com.dwl.service.system.query.QuerySysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 查询系统用户 服务实现类
 * Query System User Service Implementation
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-24 00:59
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class QuerySysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements QuerySysUserService {

    private final QuerySysUserRoleService querySysUserRoleService;
    private final QuerySysRoleService querySysRoleService;

    @Override
    public SysUser getByUsername(String username) {
        LambdaQueryWrapper<SysUser> user = new LambdaQueryWrapper<>();
        user.eq(SysUser::getUsername, username);
        return this.getOne(user);
    }

    @Override
    public UserVO getUserDetail(Long id) {
        SysUser user = this.getById(id);
        if (Objects.isNull(user)) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }
        return convertToVO(user);

    }

    @Override
    public PageResult<UserVO> pageUsers(String username, Integer status, int pageNum, int pageSize) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(username) && !username.isEmpty(), SysUser::getUsername, username)
                .eq(Objects.nonNull(status), SysUser::getStatus, status)
                .orderByDesc(SysUser::getCreatedAt);

        Page<SysUser> userPage = new Page<>(pageNum, pageSize);
        Page<SysUser> page = this.page(userPage, wrapper);

        List<UserVO> voList = page.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        return new PageResult<>(voList, page.getTotal(), pageNum, pageSize);
    }


    /**
     * 实体转视图对象
     * Convert entity to view object
     *
     * @param user 系统用户实体 / System user entity
     * @return 用户视图对象 / User view object
     */
    private UserVO convertToVO(SysUser user) {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);

        /* 手动映射字段名不同的属性
         * Manually map fields with different names
         */
        vo.setNickname(user.getRealName());
        vo.setCreateTime(user.getCreatedAt());

        // Query user's roles
        Wrapper<SysUserRole> sysUserRoleWrapper = new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, user.getId());
        List<SysUserRole> userRoles = querySysUserRoleService.list(sysUserRoleWrapper);

        if (!userRoles.isEmpty()) {
            List<Long> roleIds = userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());

            List<SysRole> roles = querySysRoleService.listByIds(roleIds);
            List<RoleVO> roleVOs = roles.stream().map(role -> {
                RoleVO roleVO = new RoleVO();
                BeanUtils.copyProperties(role, roleVO);
                return roleVO;
            }).collect(Collectors.toList());

            vo.setRoles(roleVOs);
        } else {
            vo.setRoles(Collections.emptyList());
        }

        return vo;
    }
}
