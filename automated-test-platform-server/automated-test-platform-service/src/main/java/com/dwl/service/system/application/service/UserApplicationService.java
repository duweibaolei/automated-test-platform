package com.dwl.service.system.application.service;

import com.dwl.common.ddd.ApplicationService;
import com.dwl.common.result.PageResult;
import com.dwl.service.system.application.command.CreateUserCommand;
import com.dwl.service.system.application.command.DeleteUserCommand;
import com.dwl.service.system.application.command.UpdateUserStatusCommand;
import com.dwl.service.system.application.handler.CreateUserCommandHandler;
import com.dwl.service.system.application.handler.DeleteUserCommandHandler;
import com.dwl.service.system.application.handler.UpdateUserStatusCommandHandler;
import com.dwl.service.system.application.handler.UserDetailQueryHandler;
import com.dwl.service.system.application.handler.UserPageQueryHandler;
import com.dwl.service.system.application.query.UserDetailQuery;
import com.dwl.service.system.application.query.UserPageQuery;
import com.dwl.service.system.application.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户应用服务
 * User Application Service
 * <p>
 * 系统管理域的应用服务入口, Controller 唯一依赖的对象
 * 负责编排 CommandHandler 和 QueryHandler 完成用例, 不包含业务规则
 * 写操作走 CommandHandler, 读操作走 QueryHandler, CQRS 隔离
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserApplicationService implements ApplicationService {

    /* ============================================================
     *  Command Handlers (写操作)
     *  Command Handlers (Write Operations)
     * ============================================================ */
    private final CreateUserCommandHandler createUserCommandHandler;
    private final UpdateUserStatusCommandHandler updateUserStatusCommandHandler;
    private final DeleteUserCommandHandler deleteUserCommandHandler;

    /* ============================================================
     *  Query Handlers (读操作)
     *  Query Handlers (Read Operations)
     * ============================================================ */
    private final UserPageQueryHandler userPageQueryHandler;
    private final UserDetailQueryHandler userDetailQueryHandler;

    /* ================================================================
     * 写操作 — Write Operations
     * ================================================================ */

    /**
     * 创建用户
     * Create user
     *
     * @param command Create user command
     * @return New user ID
     */
    public Long createUser(CreateUserCommand command) {
        log.info("Create user: username={}", command.getUsername());
        return createUserCommandHandler.handle(command);
    }

    /**
     * 更新用户状态(启用/禁用)
     * Update user status (enable/disable)
     *
     * @param command Update user status command
     */
    public void updateUserStatus(UpdateUserStatusCommand command) {
        log.info("Update user status: userId={}, status={}", command.getUserId(), command.getStatus());
        updateUserStatusCommandHandler.handle(command);
    }

    /**
     * 删除用户(逻辑删除)
     * Delete user (logical delete)
     *
     * @param command Delete user command
     */
    public void deleteUser(DeleteUserCommand command) {
        log.info("Delete user: userId={}", command.getUserId());
        deleteUserCommandHandler.handle(command);
    }

    /* ================================================================
     * 读操作 — Read Operations
     * ================================================================ */

    /**
     * 分页查询用户
     * Page query users
     *
     * @param query User page query
     * @return Paginated user list
     */
    public PageResult<UserVO> pageUsers(UserPageQuery query) {
        return userPageQueryHandler.handle(query);
    }

    /**
     * 查询用户详情(含角色)
     * Get user detail (with roles)
     *
     * @param query User detail query
     * @return User detail VO
     */
    public UserVO getUserDetail(UserDetailQuery query) {
        return userDetailQueryHandler.handle(query);
    }
}
