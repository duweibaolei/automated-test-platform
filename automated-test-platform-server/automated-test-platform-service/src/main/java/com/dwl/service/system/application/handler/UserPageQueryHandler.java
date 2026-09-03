package com.dwl.service.system.application.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dwl.common.result.PageResult;
import com.dwl.dao.system.mapper.UserMapper;
import com.dwl.model.domain.system.aggregate.User;
import com.dwl.service.system.application.query.UserPageQuery;
import com.dwl.service.system.application.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户分页查询处理器
 * User Page Query Handler
 * <p>
 * CQRS 读操作处理器, 直接查数据库组装 VO, 不经过领域模型
 * 读侧可以独立优化(缓存、宽表、ES), 不影响写侧
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserPageQueryHandler {

    private final UserMapper userMapper;

    /**
     * 处理用户分页查询
     * Handle user page query
     *
     * @param query User page query
     * @return Paginated user list
     */
    public PageResult<UserVO> handle(UserPageQuery query) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .like(StringUtils.hasText(query.getUsername()), User::getUsername, query.getUsername())
                .eq(query.getStatus() != null, User::getStatus, query.getStatus())
                .orderByDesc(User::getCreatedAt);

        Page<User> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<User> result = userMapper.selectPage(page, wrapper);

        List<UserVO> voList = result.getRecords().stream()
                .map(this::toVO)
                .collect(Collectors.toList());

        return new PageResult<>(voList, result.getTotal(), query.getPageNum(), query.getPageSize());
    }

    private UserVO toVO(User user) {
        return UserVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .realName(user.getRealName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .status(user.getStatus())
                .lastLoginTime(user.getLastLoginTime())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
