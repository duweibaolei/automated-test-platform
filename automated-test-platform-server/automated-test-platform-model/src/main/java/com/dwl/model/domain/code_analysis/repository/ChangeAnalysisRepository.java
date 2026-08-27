package com.dwl.model.domain.code_analysis.repository;

import com.dwl.common.ddd.Repository;
import com.dwl.model.domain.code_analysis.aggregate.ChangeAnalysis;

import java.util.Optional;

/**
 * 变更分析仓储接口
 * <p>
 * Change Analysis Repository Interface
 * <p>
 * 定义在领域层, 实现在基础设施层
 * <p>
 * Defined in the domain layer, implemented in the infrastructure layer
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 21:00
 */
public interface ChangeAnalysisRepository extends Repository<ChangeAnalysis, Long> {

    /**
     * 根据分析编号查找
     * <p>
     * Find by analysis number
     *
     * @param analysisNo 分析编号
     *                   Analysis number
     * @return 变更分析, 未找到则为空
     * Change analysis, or empty if not found
     */
    Optional<ChangeAnalysis> findByAnalysisNo(String analysisNo);

}
