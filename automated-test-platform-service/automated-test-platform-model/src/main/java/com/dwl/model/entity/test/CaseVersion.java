package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 用例版本历史实体
 * Case Version History Entity
 * 对应表 case_version，保存测试用例每次修改的快照。
 * Maps to table case_version, saving snapshots of test case modifications.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 11:29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("case_version")
@Schema(description = """
        用例版本历史
        Case Version History
        """)
public class CaseVersion extends BaseEntity {

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
            Case snapshot (including complete step data), stored as String for JSON content
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

}
