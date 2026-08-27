package com.dwl.model.domain.test_management.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 用例版本历史实体
 * <p>
 * Case Version History Entity
 * <p>
 * TestCase 聚合根内的实体, 保存测试用例每次修改的快照版本不可变
 * <p>
 * Entity within TestCase aggregate root, saving snapshots of test case modifications
 * <p>
 * Version is immutable
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("case_version")
@Schema(description = """
        用例版本历史实体
        Case Version History Entity
        """)
public class CaseVersion extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            版本 ID
            Version ID
            """)
    private Long id;

    @Schema(description = """
            用例 ID
            Case ID
            """, example = "1")
    private Long caseId;

    @Schema(description = """
            版本号
            Version number
            """, example = "1")
    private Integer version;

    @Schema(description = """
            用例快照
            Case snapshot (JSON, including complete step data)
            """)
    private String snapshotJson;

    @Schema(description = """
            变更摘要
            Change summary
            """)
    private String changeSummary;

    @Schema(description = """
            修改人 ID
            Modifier user ID
            """)
    private Long modifiedBy;

    @Schema(description = """
            Logical delete flag
            """, example = "0",
            implementation = DeletedStatus.class)
    private Integer isDelete;

    @Schema(description = """
            Create time
            """)
    private LocalDateTime createdAt;

    @Schema(description = """
            Update time
            """)
    private LocalDateTime updatedAt;

    /**
     * 工厂方法: 创建用例版本
     * Factory Method: Create case version
     *
     * @param caseId        Case ID
     * @param version       Version number
     * @param snapshotJson  用例快照
     *                      Case snapshot (JSON)
     * @param changeSummary 变更摘要
     *                      Change summary
     * @param modifiedBy    修改人 ID
     *                      Modifier user ID
     * @return 新版本实体
     * New version entity
     */
    public static CaseVersion create(Long caseId, Integer version, String snapshotJson,
                                     String changeSummary, Long modifiedBy) {
        return CaseVersion.builder()
                .caseId(caseId).version(version).snapshotJson(snapshotJson)
                .changeSummary(changeSummary).modifiedBy(modifiedBy).build();
    }

}
