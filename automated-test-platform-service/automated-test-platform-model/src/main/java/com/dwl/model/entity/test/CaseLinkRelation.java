package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 用例与链路关联实体
 * Case-Link Association Entity
 * 对应表 case_link_relation,用例与业务链路的多对多关联
 * Maps to table case_link_relation, many-to-many association between cases and business links
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 15:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("case_link_relation")
@Schema(description = """
        用例与链路关联
        Case-Link Association
        """)
public class CaseLinkRelation extends BaseEntity {

    @Schema(description = """
            用例 ID
            Case ID
            """, example = "1")
    private Long caseId;

    @Schema(description = """
            链路 ID
            Link ID
            """, example = "1")
    private Long linkId;

}
