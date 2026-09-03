package com.dwl.common.ddd;


import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * DDD 仓储标记接口
 * <p>
 * DDD Repository Marker Interface
 * <p>
 * 仓储负责聚合根的持久化和检索, 封装数据访问细节, 仓储接口定义在领域层, 实现在基础设施层, 仓储只操作聚合根, 不操作聚合内的实体
 * <p>
 * Repository is responsible for persistence and retrieval of aggregate roots, 
 * encapsulating data access details
 * <p>
 * Repository interfaces are defined in the
 * domain layer, implementations in the infrastructure layer
 * <p>
 * Repository only
 * operates on aggregate roots, not entities within aggregates.
 *
 * @param <AR> Aggregate root type
 * @param <ID> Aggregate root ID type
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 18:31
 */
@Schema(description = """
        DDD 仓储标记接口
        DDD Repository Marker Interface
        """)
public interface Repository<AR extends AggregateRoot<ID>, ID extends Serializable> {

    /**
     * 根据 ID 查找聚合根
     * <p>
     * Find aggregate root by ID
     *
     * @param id Aggregate root ID
     * @return Aggregate root, or null if not found
     */
    AR findById(ID id);


    /**
     * 保存聚合根(新增或更新)
     * <p>
     * Save aggregate root (insert or update)
     *
     * @param aggregateRoot Aggregate root to save
     * @return Saved aggregate root
     */
    AR save(AR aggregateRoot);

    /**
     * 根据 ID 删除聚合根(逻辑删除)
     * <p>
     * Delete aggregate root by ID (logical delete)
     *
     * @param id Aggregate root ID
     */
    void deleteById(ID id);

    /**
     * 判断聚合根是否存在
     * <p>
     * Check if aggregate root exists
     *
     * @param id Aggregate root ID
     * @return true if exists
     */
    boolean existsById(ID id);

}
