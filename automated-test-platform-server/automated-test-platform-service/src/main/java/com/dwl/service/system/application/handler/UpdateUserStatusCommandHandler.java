package com.dwl.service.system.application.handler;

import com.dwl.common.ddd.DomainEventPublisher;
import com.dwl.common.enums.ErrorCode;
import com.dwl.common.exception.BusinessException;
import com.dwl.model.domain.system.aggregate.User;
import com.dwl.model.domain.system.event.UserStatusChangedEvent;
import com.dwl.model.domain.system.repository.UserRepository;
import com.dwl.service.system.application.command.UpdateUserStatusCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 更新用户状态命令处理器
 * Update User Status Command Handler
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateUserStatusCommandHandler {

    private final UserRepository userRepository;
    private final DomainEventPublisher eventPublisher;

    /**
     * 处理更新用户状态命令
     * Handle update user status command
     *
     * @param command Update user status command
     */
    @Transactional(rollbackFor = Exception.class)
    public void handle(UpdateUserStatusCommand command) {
        /* 1. 加载聚合根
         * load aggregate root */
        User user = userRepository.findById(command.getUserId());
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        /* 2. 调用聚合根方法变更状态(业务规则: 幂等、发布事件)
         * Call aggregate root method to change status (business rules: idempotent, publish event) */
        if (command.getStatus() == 1) {
            user.enable();
        } else {
            user.disable();
        }

        // 3. save
        userRepository.save(user);
        log.info("User status updated: id={}, status={}", user.getId(), user.getStatus());

        /* 4. 发布领域事件(仅状态变更事件)
         * publish domain events (status change event only) */
        user.pullDomainEvents(UserStatusChangedEvent.class).forEach(eventPublisher::publish);
    }
}
