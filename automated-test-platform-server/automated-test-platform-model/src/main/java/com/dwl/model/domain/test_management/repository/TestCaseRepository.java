package com.dwl.model.domain.test_management.repository;

import com.dwl.common.ddd.Repository;
import com.dwl.model.domain.test_management.aggregate.TestCase;

import java.util.Optional;

/**
 * 测试用例仓储接口
 * <p>
 * Test Case Repository Interface
 * <p>
 * 定义在领域层, 实现在基础设施层
 * <p>
 * Defined in the domain layer, implemented in the infrastructure layer
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 21:03
 */
public interface TestCaseRepository extends Repository<TestCase, Long> {

    /**
     * 根据用例编号查找
     * <p>
     * Find by case number
     *
     * @param caseNo Case number
     * @return 测试用例, 未找到则为空
     * Test case, or empty if not found
     */
    Optional<TestCase> findByCaseNo(String caseNo);

}
