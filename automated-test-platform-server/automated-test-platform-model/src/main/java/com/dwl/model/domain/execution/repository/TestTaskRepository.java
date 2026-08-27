package com.dwl.model.domain.execution.repository;

import com.dwl.common.ddd.Repository;
import com.dwl.model.domain.execution.aggregate.TestTask;

import java.util.Optional;

/**
 * 测试任务仓储接口
 * <p>
 * Test Task Repository Interface
 * <p>
 * 定义在领域层, 实现在基础设施层
 * <p>
 * Defined in the domain layer, implemented in the infrastructure layer.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 21:01
 */
public interface TestTaskRepository extends Repository<TestTask, Long> {

    /**
     * 根据任务编号查找
     * <p>
     * Find by task number
     *
     * @param taskNo Task number
     * @return 测试任务, 未找到则为空
     * Test task, or empty if not found
     */
    Optional<TestTask> findByTaskNo(String taskNo);

}
