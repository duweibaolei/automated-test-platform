package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 用例步骤实体
 * Case Step Entity
 * 对应表 case_step，定义测试用例的每一步操作和断言。
 * Maps to table case_step, defining each operation and assertion step of a test case.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 16:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("case_step")
@Schema(description = """
        用例步骤
        Case Step
        """)
public class CaseStep extends BaseEntity {

    @Schema(description = """
            用例 ID
            Case ID
            """, example = "1")
    private Long caseId;

    @Schema(description = """
            步骤顺序
            Step order (starts from 1)
            """, example = "1")
    private Integer stepOrder;

    @Schema(description = """
            操作元素 ID
            Operation element ID
            """)
    private Long elementId;

    @Schema(description = """
            动作类型
            Action type: click/fill/select/waitFor/hover/scroll/navigate/assert
            """, example = "click")
    private String actionType;

    @Schema(description = """
            输入值
            Action value (action parameter)
            """)
    private String actionValue;

    @Schema(description = """
            断言类型
            Assert type: url_contains/visible/text_match/value_contains/attribute
            """, example = "visible")
    private String assertType;

    @Schema(description = """
            断言期望值
            Assert expected value
            """)
    private String assertValue;

    @Schema(description = """
            等待超时
            Wait timeout (milliseconds)
            """, example = "5000")
    private Integer waitTimeout;

    @Schema(description = """
            步骤描述
            Step description
            """)
    private String description;

}
