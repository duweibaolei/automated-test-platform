package com.dwl.service.system.application.handler;

import com.dwl.common.ddd.DomainEventPublisher;
import com.dwl.common.enums.ErrorCode;
import com.dwl.common.exception.BusinessException;
import com.dwl.model.domain.system.aggregate.User;
import com.dwl.model.domain.system.event.UserCreatedEvent;
import com.dwl.model.domain.system.repository.UserRepository;
import com.dwl.service.system.application.command.CreateUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 创建用户命令处理器
 * Create User Command Handler
 * <p>
 * 应用层写操作处理器, 负责编排领域对象完成创建用户用例
 * 流程: 用户名唯一性校验 -> 调用聚合根工厂方法 -> 仓储保存 -> 发布领域事件
 * 注意: 业务规则在聚合根内, Handler 只做编排, 不写业务逻辑
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserCommandHandler {

    private final UserRepository userRepository;
    private final DomainEventPublisher eventPublisher;

    /**
     * 处理创建用户命令
     * Handle create user command
     *
     * @param command Create user command
     * @return New user ID
     */
    @Transactional(rollbackFor = Exception.class)
    public Long handle(CreateUserCommand command) {
        /* 1. 用户名唯一性校验
         * username uniqueness check */
        if (userRepository.existsByUsername(command.getUsername())) {
            throw new BusinessException(ErrorCode.CONFLICT, "用户名已存在");
        }

        /* 2. 调用聚合根工厂方法创建(业务规则封装在聚合根内)
         * Call aggregate root factory method (business rules encapsulated in aggregate root) */
        User user = User.create(
                command.getUsername(),
                command.getPassword(),
                command.getRealName(),
                command.getEmail(),
                command.getRoleIds()
        );

        /* 3. 仓储保存聚合根(含聚合内实体)
         * Repository saves aggregate root (including entities within aggregate) */
        user = userRepository.save(user);
        log.info("User created: id={}, username={}", user.getId(), user.getUsername());

        /* 4. 发布聚合根产生的领域事件(仅创建事件)
         * Publish domain events raised by aggregate root (creation event only) */
        user.pullDomainEvents(UserCreatedEvent.class).forEach(eventPublisher::publish);

        return user.getId();
    }
}
