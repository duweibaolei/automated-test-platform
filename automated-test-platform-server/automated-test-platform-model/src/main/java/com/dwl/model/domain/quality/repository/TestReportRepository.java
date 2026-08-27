package com.dwl.model.domain.quality.repository;

import com.dwl.common.ddd.Repository;
import com.dwl.model.domain.quality.aggregate.TestReport;

import java.util.Optional;

/**
 * 测试报告仓储接口
 * <p>
 * Test Report Repository Interface
 * <p>
 * 定义在领域层, 实现在基础设施层
 * <p>
 * Defined in the domain layer, implemented in the infrastructure layer
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 21:02
 */
public interface TestReportRepository extends Repository<TestReport, Long> {

    /**
     * 根据报告编号查找
     * <p>
     * Find by report number
     *
     * @param reportNo 报告编号
     *                 Report number
     * @return 测试报告, 未找到则为空
     * Test report, or empty if not found
     */
    Optional<TestReport> findByReportNo(String reportNo);

}
