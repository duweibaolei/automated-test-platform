package com.dwl.service.system.application.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dwl.common.enums.ErrorCode;
import com.dwl.common.exception.BusinessException;
import com.dwl.dao.system.mapper.RoleMapper;
import com.dwl.dao.system.mapper.UserMapper;
import com.dwl.dao.system.mapper.UserRoleMapper;
import com.dwl.model.domain.system.aggregate.Role;
import com.dwl.model.domain.system.aggregate.User;
import com.dwl.model.domain.system.entity.UserRole;
import com.dwl.service.system.application.query.UserDetailQuery;
import com.dwl.service.system.application.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户详情查询处理器
 * User Detail Query Handler
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailQueryHandler {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;

    /**
     * 处理用户详情查询
     * Handle user detail query
     *
     * @param query User detail query
     * @return User detail VO
     */
    public UserVO handle(UserDetailQuery query) {
        /* 1. 查用户基本信息
         * query user basic info */
        User user = userMapper.selectById(query.getUserId());
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        /* 2. 查用户角色关联
         * query user-role associations */
        LambdaQueryWrapper<UserRole> relationWrapper = new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, query.getUserId());
        List<UserRole> relations = userRoleMapper.selectList(relationWrapper);

        /* 3. 查角色详情
         * query role details */
        List<Long> roleIds = Collections.emptyList();
        List<String> roleNames = Collections.emptyList();
        if (!relations.isEmpty()) {
            roleIds = relations.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            List<Role> roles = roleMapper.selectByIds(roleIds);
            roleNames = roles.stream().map(Role::getRoleName).collect(Collectors.toList());
        }

        /* 4. 组装 VO
         * assemble VO */
        return UserVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .realName(user.getRealName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .status(user.getStatus())
                .lastLoginTime(user.getLastLoginTime())
                .createdAt(user.getCreatedAt())
                .roleIds(roleIds)
                .roleNames(roleNames)
                .build();
    }
}
