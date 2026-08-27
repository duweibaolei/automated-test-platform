package com.dwl.model.domain.test_management.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.testmanagement.AssertType;
import com.dwl.common.enums.execution.ActionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 用例步骤实体
 * <p>
 * Case Step Entity
 * <p>
 * TestCase 聚合根内的实体，定义测试用例的每一步操作和断言
 * <p>
 * Entity within the TestCase aggregate root, defining each operation and
 * assertion step of a test case.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("case_step")
@Schema(description = """
        用例步骤实体
        Case Step Entity
        """)
public class CaseStep extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            步骤 ID
            Step ID
            """)
    private Long id;

    @Schema(description = """
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
            Action type
            """, example = "click",
            implementation = ActionType.class)
    private String actionType;

    @Schema(description = """
            输入值
            Action value (action parameter)
            """)
    private String actionValue;

    @Schema(description = """
            断言类型
            Assert type
            """, example = "visible",
            implementation = AssertType.class)
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
     * 工厂方法：创建用例步骤
     * <p>
     * Factory Method: Create case step
     *
     * @param caseId      Case ID
     * @param stepOrder   步骤顺序
     *                    Step order
     * @param elementId   操作元素
     *                    ID Operation element ID
     * @param actionType  动作类型
     *                    Action type
     * @param actionValue 输入值
     *                    Action value
     * @param assertType  断言类型
     *                    Assert type
     * @param assertValue 断言期望值
     *                    Assert expected value
     * @param waitTimeout 等待超时
     *                    Wait timeout
     * @return New case step
     * @throws IllegalArgumentException 当断言类型不合法时
     * When assert type is invalid
     */
    public static CaseStep create(Long caseId, Integer stepOrder, Long elementId, String actionType,
                                  String actionValue, String assertType, String assertValue, Integer waitTimeout) {
        if (Objects.nonNull(assertType) && !AssertType.exists(assertType)) {
            throw new IllegalArgumentException("Invalid assert type: " + assertType);
        }

        return CaseStep.builder()
                .caseId(caseId).stepOrder(stepOrder).elementId(elementId)
                .actionType(actionType).actionValue(actionValue)
                .assertType(assertType).assertValue(assertValue).waitTimeout(waitTimeout)
                .build();
    }

}
