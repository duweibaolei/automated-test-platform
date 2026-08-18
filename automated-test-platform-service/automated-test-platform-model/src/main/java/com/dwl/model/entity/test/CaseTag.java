package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 用例标签实体
 * Case Tag Entity
 * 对应表 case_tag，定义测试用例的分类标签。
 * Maps to table case_tag, defining classification tags for test cases.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 13:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("case_tag")
@Schema(description = """
        用例标签
        Case Tag
        """)
public class CaseTag extends BaseEntity {

    @Schema(description = """
            标签名称
            Tag name
            """, example = "冒烟测试")
    private String tagName;

    @Schema(description = """
            标签颜色
            Tag color
            """, example = "#FF5722")
    private String tagColor;

}
