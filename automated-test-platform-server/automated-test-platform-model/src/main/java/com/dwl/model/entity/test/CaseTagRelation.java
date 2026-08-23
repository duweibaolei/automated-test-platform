package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 用例标签关联实体
 * Case-Tag Association Entity
 * 对应表 case_tag_relation，用例与标签的多对多关联。
 * Maps to table case_tag_relation, many-to-many association between cases and tags.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 17:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("case_tag_relation")
@Schema(description = """
        用例标签关联
        Case-Tag Association
        """)
public class CaseTagRelation extends BaseEntity {

    @Schema(description = """
            用例 ID
            Case ID
            """, example = "1")
    private Long caseId;

    @Schema(description = """
            标签 ID
            Tag ID
            """, example = "1")
    private Long tagId;

}
