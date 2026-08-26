package com.dwl.dao.system.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dwl.dao.system.mapper.UserMapper;
import com.dwl.dao.system.mapper.UserRoleMapper;
import com.dwl.model.domain.system.aggregate.User;
import com.dwl.model.domain.system.entity.UserRole;
import com.dwl.model.domain.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * 用户仓储实现
 * User Repository Implementation
 * <p>
 * 基础设施层,实现领域层定义的 UserRepository 接口
 * 基于 MyBatis-Plus Mapper 直接操作聚合根,聚合根带 @TableName 可直接持久化
 * 保存时同时保存聚合内的 UserRole 实体 (先删后插保证幂等)
 * <p>
 * Infrastructure layer implementing the UserRepository interface defined in the domain layer.
 * Uses MyBatis-Plus Mapper to operate on aggregate roots directly. Aggregate roots with
 *
 * @TableName can be persisted directly. When saving, also save UserRole entities within
 * the aggregate (delete-then-insert for idempotency).
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:41
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    /**
     * 根据 ID 查找用户
     * Find user by ID
     *
     * @param id User ID
     * @return 用户对象,未找到则为 null
     * User object, or null if not found
     */
    @Override
    public User findById(Long id) {
        return Optional.ofNullable(userMapper.selectById(id))
                .map(user -> {
                    loadRoles(user);
                    return user;
                })
                .orElse(null);
    }

    /**
     * 根据用户名查找用户
     * Find user by username
     *
     * @param username Username
     * @return 用户可选值
     * User optional
     */
    @Override
    public Optional<User> findByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username);
        return Optional.ofNullable(userMapper.selectOne(wrapper)).map(user -> {
            loadRoles(user);
            return user;
        });
    }

    /**
     * 判断用户名是否存在
     * Check if username exists
     *
     * @param username Username
     * @return 存在返回 true
     * true if exists
     */
    @Override
    public boolean existsByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username);
        return userMapper.exists(wrapper);
    }

    /**
     * 保存用户
     * Save user
     *
     * @param user User object
     * @return Saved user
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User save(User user) {
        // Save user main table
        userMapper.insertOrUpdate(user);

        // 保存聚合内的角色关联 (先删后插,保证幂等)
        LambdaUpdateWrapper<UserRole> deleteWrapper = new LambdaUpdateWrapper<UserRole>()
                .eq(UserRole::getUserId, user.getId());
        userRoleMapper.delete(deleteWrapper);

        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(ur -> {
                ur.setUserId(user.getId());
                userRoleMapper.insert(ur);
            });
        }

        return user;
    }

    /**
     * 根据 ID 删除用户
     * Delete user by ID
     *
     * @param id User ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        /* 逻辑删除由 MyBatis-Plus @TableLogic 自动处理
         * Logical delete auto-handled by MyBatis-Plus @TableLogic */
        userMapper.deleteById(id);

        /* 同时删除角色关联
         * Also delete role associations */
        LambdaUpdateWrapper<UserRole> wrapper = new LambdaUpdateWrapper<UserRole>()
                .eq(UserRole::getUserId, id);
        userRoleMapper.delete(wrapper);
    }

    /**
     * 判断用户是否存在
     * Check if user exists
     *
     * @param id User ID
     * @return true if exists
     */
    @Override
    public boolean existsById(Long id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getId, id);
        return userMapper.exists(wrapper);
    }

    /* ================================================================
     * 私有方法
     * Private Methods
     * ================================================================ */

    /**
     * 加载聚合内的角色关联
     * Load role associations within aggregate
     *
     * @param user User object
     */
    private void loadRoles(User user) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, user.getId());
        List<UserRole> roles = userRoleMapper.selectList(wrapper);
        user.getRoles().addAll(roles);
    }

}
