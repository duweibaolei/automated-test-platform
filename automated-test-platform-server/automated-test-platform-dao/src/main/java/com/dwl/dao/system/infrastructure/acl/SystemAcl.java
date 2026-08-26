package com.dwl.dao.system.infrastructure.acl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dwl.dao.system.mapper.UserMapper;
import com.dwl.model.domain.system.aggregate.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * 系统管理域防腐层
 * <p>
 * System Management Domain Anti-Corruption Layer (ACL)
 * <p>
 * 供其他限界上下文调用系统管理域的适配器
 * <p>
 * 防腐层的作用: 将外部域的模型转换成系统管理域的模型, 外部域变更时只需要修改 ACL, 不影响系统管理域的领域逻辑
 * <p>
 * Adapter for other bounded contexts to call the System Management domain
 * <p>
 * The ACL translates models from external domains to the System Management domain model
 * <p>
 * When external domains change, only the ACL needs modification, not the domain logic
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:40
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SystemAcl {

    private final UserMapper userMapper;

    /**
     * 根据用户 ID 获取用户简要信息
     * <p>
     * Get user brief info by user ID
     * <p>
     * 供其他域调用, 返回传输对象, 不暴露领域实体
     * <p>
     * Called by other domains, returns DTO, not exposing domain entity
     *
     * @param userId User ID
     * @return User brief info, or empty if not found
     */
    public Optional<UserInfo> getUserInfo(Long userId) {
        if (Objects.isNull(userId)) {
            return Optional.empty();
        }
        User user = userMapper.selectById(userId);
        if (Objects.isNull(user)) {
            return Optional.empty();
        }
        return Optional.of(new UserInfo(user.getId(), user.getUsername(), user.getRealName(), user.getEmail()));
    }

    /**
     * 根据用户名获取用户简要信息
     * <p>
     * Get user brief info by username
     *
     * @param username Username
     * @return User brief info, or empty if not found
     */
    public Optional<UserInfo> getUserInfoByUsername(String username) {
        if (StringUtils.isEmpty(username) || username.isEmpty()) {
            return Optional.empty();
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        if (Objects.isNull(user)) {
            return Optional.empty();
        }
        return Optional.of(new UserInfo(user.getId(), user.getUsername(), user.getRealName(), user.getEmail()));
    }

    /**
     * 判断用户是否存在
     * <p>
     * Check if user exists
     *
     * @param userId User ID
     * @return true if exists
     */
    public boolean userExists(Long userId) {
        return Objects.nonNull(userId) && userMapper.exists(new LambdaQueryWrapper<User>().eq(User::getId, userId));
    }

    /**
     * 用户简要信息传输对象
     * <p>
     * User Brief Info Data Transfer Object
     * <p>
     * 防腐层返回的传输对象, 其他域使用此对象, 不直接依赖 User 实体
     * <p>
     * DTO returned by ACL, other domains use this instead of directly depending on User entity.
     *
     * @param id       User ID
     * @param username Username
     * @param realName 真实姓名
     *                 Real name
     * @param email    Email
     */
    public record UserInfo(
            Long id,
            String username,
            String realName,
            String email
    ) {
    }

}
