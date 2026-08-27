package com.dwl.model.domain.quality.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.quality.DefectSeverity;
import com.dwl.common.enums.quality.DefectStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 缺陷记录聚合根
 * <p>
 * Defect Record Aggregate Root
 * <p>
 * 质量报表域的聚合根, 管理测试过程中发现的缺陷生命周期
 * 业务规则：缺陷状态机{@link DefectStatus}、严重等级管理、关联追溯
 * <p>
 * Aggregate root of the Quality Report domain, managing defect lifecycle
 * discovered during testing.
 * <p>
 * Business rules: defect state machine
 * {@link DefectStatus}, severity level management, traceability.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("defect_record")
@Schema(description = """
        缺陷记录聚合根
        Defect Record Aggregate Root
        """)
public class DefectRecord extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            缺陷 ID
            Defect ID
            """)
    private Long id;

    @Schema(description = """
            缺陷编号
            Defect number
            """, example = "BUG-001")
    private String defectNo;

    @Schema(description = """
            缺陷标题
            Defect title
            """, example = "登录页面提交按钮无响应")
    private String defectTitle;

    @Schema(description = """
            严重等级
            Severity
            """, example = "major",
            implementation = DefectSeverity.class)
    private String severity;

    @Schema(description = """
            缺陷描述
            Defect description
            """)
    private String description;

    @Schema(description = """
            关联执行记录 ID
            Associated execution record ID
            """)
    private Long executionId;

    @Schema(description = """
            关联用例 ID
            Associated case ID
            """)
    private Long caseId;

    @Schema(description = """
            关联提交 ID
            Associated commit ID
            """)
    private Long commitId;

    @Schema(description = """
            关联报告 ID
            Associated report ID
            """)
    private Long reportId;

    @Schema(description = """
            Status
            """, example = "open",
            implementation = DefectStatus.class)
    private String status;

    @Schema(description = """
            录入人 ID
            Creator user ID
            """, example = "1")
    private Long createdBy;

    @Schema(description = """
            解决人 ID
            Resolved by user ID
            """)
    private Long resolvedBy;

    @Schema(description = """
            解决时间
            Resolved at
            """)
    private LocalDateTime resolvedAt;

    @Schema(description = """
            Logical delete flag
            """, example = "0",
            implementation = DeletedStatus.class)
    private Integer isDelete;

    @Schema(description = """
            创建时间
            Create time
            """)
    private LocalDateTime createdAt;

    @Schema(description = """
            更新时间
            Update time
            """)
    private LocalDateTime updatedAt;

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法：创建缺陷记录
     * <p>
     * Factory Method: Create defect record
     *
     * @param defectNo    缺陷编号
     *                    Defect number
     * @param defectTitle 缺陷标题
     *                    Defect title
     * @param severity    严重等级
     *                    Severity
     * @param description 缺陷描述
     *                    Defect description
     * @param executionId 关联执行记录 ID
     *                    Associated execution record ID
     * @param caseId      关联用例 ID
     *                    Associated case ID
     * @param commitId    关联提交 ID
     *                    Associated commit ID
     * @param reportId    关联报告 ID
     *                    Associated report ID
     * @param createdBy   录入人 ID
     *                    Creator user ID
     * @return 新缺陷记录
     * New defect record
     */
    public static DefectRecord create(String defectNo, String defectTitle, String severity, String description,
                                      Long executionId, Long caseId, Long commitId, Long reportId, Long createdBy) {
        return DefectRecord.builder()
                .defectNo(defectNo).defectTitle(defectTitle).severity(severity).description(description)
                .executionId(executionId).caseId(caseId).commitId(commitId).reportId(reportId)
                .createdBy(createdBy).status(DefectStatus.OPEN.getCode()).build();
    }

    /**
     * 创建致命缺陷
     * <p>
     * Create critical defect
     *
     * @param defectNo    缺陷编号
     *                    Defect number
     * @param defectTitle 缺陷标题
     *                    Defect title
     * @param description 缺陷描述
     *                    Defect description
     * @param executionId 关联执行记录 ID
     *                    Associated execution record ID
     * @param caseId      关联用例 ID
     *                    Associated case ID
     * @param createdBy   录入人 ID
     *                    Creator user ID
     * @return 致命缺陷
     * Critical defect
     */
    public static DefectRecord createCritical(String defectNo, String defectTitle, String description,
                                              Long executionId, Long caseId, Long createdBy) {
        return create(defectNo, defectTitle, DefectSeverity.CRITICAL.getCode(), description,
                executionId, caseId, null, null, createdBy);
    }

    /**
     * 解决缺陷
     * Resolve defect
     *
     * @param resolvedBy 解决人 ID
     *                   Resolved by user ID
     */
    public void resolve(Long resolvedBy) {
        if (!DefectStatus.OPEN.getCode().equals(this.status)) {
            throw new IllegalStateException("Only open defects can be resolved, current status: " + this.status);
        }
        this.status = DefectStatus.RESOLVED.getCode();
        this.resolvedBy = resolvedBy;
        this.resolvedAt = LocalDateTime.now();
    }

    /**
     * 关闭缺陷
     * Close defect
     */
    public void close() {
        if (!DefectStatus.RESOLVED.getCode().equals(this.status)) {
            throw new IllegalStateException("Only resolved defects can be closed, current status: " + this.status);
        }
        this.status = DefectStatus.CLOSED.getCode();
    }

    /**
     * 重新打开缺陷
     * Reopen defect
     */
    public void reopen() {
        this.status = DefectStatus.REOPENED.getCode();
        this.resolvedBy = null;
        this.resolvedAt = null;
    }

    /**
     * 延期处理缺陷
     * Defer defect
     */
    public void defer() {
        this.status = DefectStatus.DEFERRED.getCode();
    }

    /**
     * 判断是否为活跃缺陷
     * Check if it is an active defect
     *
     * @return true if active
     */
    public boolean isActive() {
        return DefectStatus.isActive(this.status);
    }

    /**
     * 判断是否为高优先级缺陷
     * Check if it is a high priority defect
     *
     * @return true if high priority
     */
    public boolean isHighPriority() {
        return DefectSeverity.isHighPriority(this.severity);
    }

}
