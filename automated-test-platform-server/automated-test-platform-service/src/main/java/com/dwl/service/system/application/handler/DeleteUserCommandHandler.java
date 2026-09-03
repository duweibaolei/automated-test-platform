package com.dwl.service.system.application.handler;

import com.dwl.common.ddd.DomainEventPublisher;
import com.dwl.common.enums.ErrorCode;
import com.dwl.common.exception.BusinessException;
import com.dwl.model.domain.system.aggregate.User;
import com.dwl.model.domain.system.event.UserDeletedEvent;
import com.dwl.model.domain.system.repository.UserRepository;
import com.dwl.service.system.application.command.DeleteUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 删除用户命令处理器
 * Delete User Command Handler
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteUserCommandHandler {

    private final UserRepository userRepository;
    private final DomainEventPublisher eventPublisher;

    /**
     * 处理删除用户命令(逻辑删除)
     * Handle delete user command (logical delete)
     *
     * @param command Delete user command
     */
    @Transactional(rollbackFor = Exception.class)
    public void handle(DeleteUserCommand command) {
        /* 1. 加载聚合根
         * load aggregate root */
        User user = userRepository.findById(command.getUserId());
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        /* 2. 调用聚合根方法逻辑删除
         * call aggregate root method for logical delete */
        user.delete();

        // 3. save
        userRepository.save(user);
        log.info("User deleted: id={}, username={}", user.getId(), user.getUsername());

        /* 4. 发布领域事件(仅删除事件)
         * publish domain events (deletion event only) */
        user.pullDomainEvents(UserDeletedEvent.class).forEach(eventPublisher::publish);
    }
}
