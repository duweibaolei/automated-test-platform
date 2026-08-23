package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 测试用例实体
 * Test Case Entity
 * 对应表 test_case,存储测试用例的基本信息和元数据
 * Maps to table test_case, storing basic information and metadata for test cases.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 09:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("test_case")
@Schema(description = """
        测试用例
        Test Case
        """)
public class TestCase extends BaseEntity {

    @Schema(description = """
            用例编号
            Case number: TC-NNNN
            """, example = "TC-0001")
    private String caseNo;

    @Schema(description = """
            用例名称
            Case name
            """, example = "用户登录 - 正常流程")
    private String caseName;

    @Schema(description = """
            所属模块
            Module name
            """, example = "用户管理")
    private String moduleName;

    @Schema(description = """
            来源
            Source: auto/manual/hybrid
            """, example = "manual")
    private String source;

    @Schema(description = """
            状态
            Status: active/unstable/disabled/draft
            """, example = "active")
    private String status;

    @Schema(description = """
            健康度评分
            Health score (0-100)
            """, example = "100")
    private Integer healthScore;

    @Schema(description = """
            优先级
            Priority: P0/P1/P2
            """, example = "P2")
    private String priority;

    @Schema(description = """
            版本号
            Version number (optimistic lock)
            """, example = "1")
    @Version
    private Integer version;

    @Schema(description = """
            用例描述
            Case description
            """)
    private String description;

    @Schema(description = """
            前置条件
            Pre-condition
            """)
    private String preCondition;

    @Schema(description = """
            执行环境 ID
            Execution environment ID
            """)
    private Long envId;

    @Schema(description = """
            创建人 ID
            Creator user ID
            """)
    private Long createdBy;

    @Schema(description = """
            最后修改人 ID
            Last modifier user ID
            """)
    private Long lastModifiedBy;

}
