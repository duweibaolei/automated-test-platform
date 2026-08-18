package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 测试数据集实体
 * Test Data Set Entity
 * 对应表 test_data_set,管理参数化测试数据集
 * Maps to table test_data_set, managing parameterized test data sets
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 16:55
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("test_data_set")
@Schema(description = """
        测试数据集
        Test Data Set
        """)
public class TestDataSet extends BaseEntity {

    @Schema(description = """
            数据集名称
            Data set name
            """, example = "用户登录测试数据")
    private String setName;

    @Schema(description = """
            描述
            Description
            """)
    private String description;

    @Schema(description = """
            数据内容
            Data content (key-value JSON), stored as String for JSON content
            """)
    private String dataJson;

    @Schema(description = """
            来源
            Source: auto/manual
            """, example = "manual")
    private String source;

    @Schema(description = """
            创建人 ID
            Creator user ID
            """)
    private Long createdBy;

}
