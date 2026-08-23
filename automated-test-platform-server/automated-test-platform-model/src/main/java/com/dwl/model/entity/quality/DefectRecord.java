package com.dwl.model.entity.quality;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 缺陷记录实体
 * Defect Record Entity
 * 对应表 defect_record,管理测试过程中发现的缺陷生命周期
 * Maps to table defect_record, managing the lifecycle of defects found during testing
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 15:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("defect_record")
@Schema(description = """
        缺陷记录
        Defect Record
        """)
public class DefectRecord extends BaseEntity {

    @Schema(description = """
            缺陷编号: BUG-XXX
            Defect Number: BUG-XXX
            """, example = "BUG-XXX")
    private String defectNo;

    @Schema(description = """
            缺陷标题
            Defect title
            """, example = "登录页面提交按钮无响应")
    private String defectTitle;

    @Schema(description = """
            严重状态: critical major minor
            Severity: critical major minor
            """, example = "major")
    private String severity;

    @Schema(description = """
            缺陷描述
            Defect description
            """)
    private Long description;

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
            Status: open resolved closed
            """, example = "open")
    private String status;

    @Schema(description = """
            录入人 ID
            Creator user ID
            """, example = "1")
    private Long createdBy;

    @Schema(description = """
            解决人 ID
            Resolver user ID
            """)
    private Long resolvedBy;

    @Schema(description = """
            解决时间
            Resolved timestamp
            """)
    private LocalDateTime resolvedAt;
}
