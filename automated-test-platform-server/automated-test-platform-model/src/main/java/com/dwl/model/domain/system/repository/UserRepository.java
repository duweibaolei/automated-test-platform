package com.dwl.model.domain.system.repository;

import com.dwl.common.ddd.Repository;
import com.dwl.model.domain.system.aggregate.User;

import java.util.Optional;

/**
 * 用户仓储接口
 * <p>
 * User Repository Interface
 * <p>
 * 定义在领域层, 实现在基础设施层 (UserRepositoryImpl)
 * 仓储只操作 User 聚合根, 负责聚合根的持久化和检索
 * 注意: 仓储接口返回的是聚合根 (含聚合内实体), 不是数据库实体
 * <p>
 * Defined in the domain layer, implemented in the infrastructure layer (UserRepositoryImpl)
 * <p>
 * Repository only operates on User aggregate root, responsible for persistence and retrieval
 * <p>
 * Note: Repository interface returns aggregate root (including entities within aggregate),
 * not database entities.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 21:04
 */
public interface UserRepository extends Repository<User, Long> {

    /**
     * 根据用户名查找用户
     * <p>
     * Find user by username
     *
     * @param username Username
     * @return 用户聚合根, 未找到则为空
     * User aggregate root, or empty if not found
     */
    Optional<User> findByUsername(String username);

    /**
     * 判断用户名是否存在
     * <p>
     * Check if username exists
     *
     * @param username Username
     * @return true if exists
     */
    boolean existsByUsername(String username);

    /**
     * 保存用户及其角色关联 (聚合根整体持久化)
     * <p>
     * Save user and its role associations (aggregate root persistence as a whole)
     *
     * @param user 用户聚合根
     *             User aggregate root
     * @return 保存的用户聚合根
     * Saved user aggregate root
     */
    @Override
    User save(User user);

}
