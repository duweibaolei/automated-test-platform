package com.dwl.common.ddd;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DDD 领域服务标记接口
 * <p>
 * DDD Domain Service Marker Interface
 * <p>
 * 领域服务封装不属于任何单个聚合根的业务逻辑, 当一个业务规则涉及多个聚合根, 或者不适合放在聚合根上时, 应该使用领域服务, 领域服务是无状态的
 * <p>
 * Domain Services encapsulate business logic that doesn't belong to any single
 * aggregate root
 * <p>
 * <p>
 * When a business rule involves multiple aggregate roots, or is
 * not suitable to be placed on an aggregate root, a Domain Service should be used
 * <p>
 * Domain Services are stateless.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 18:29
 */
@Schema(description = """
        DDD 领域服务标记接口
        DDD Domain Service Marker Interface
        """)
public interface DomainService {
}
