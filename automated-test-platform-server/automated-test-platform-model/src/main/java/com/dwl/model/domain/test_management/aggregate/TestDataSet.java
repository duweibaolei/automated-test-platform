package com.dwl.model.domain.test_management.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 测试数据集聚合根
 * Test Data Set Aggregate Root
 * <p>
 * 测试管理域的聚合根, 管理参数化测试数据集
 * <p>
 * Aggregate root of the Test Management domain, managing parameterized test data sets.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("test_data_set")
@Schema(description = """
        测试数据集聚合根
        Test Data Set Aggregate Root
        """)
public class TestDataSet extends AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Schema(description = """
            数据集 ID
            Data set ID
            """)
    private Long id;

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
            Data content (JSON format)
            """)
    private String dataJson;

    @Schema(description = """
            来源
            Source: auto-automatic, manual-manual entry
            """, example = "manual")
    private String source;

    @Schema(description = """
            创建人 ID
            Creator user ID
            """)
    private Long createdBy;

    @Schema(description = """
            逻辑删除标识
            Logical delete flag: 0-not deleted, 1-deleted
            """)
    private Integer isDelete;

    @Schema(description = """
            创建时间
            Create time
            """)
    private LocalDateTime createdAt;

    @Schema(description = """
            更新时间
            Update time
            """)
    private LocalDateTime updatedAt;

    /* ================================================================
     * 业务方法 / Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建测试数据集
     * Factory Method: Create test data set
     *
     * @param setName     数据集名称 / Data set name
     * @param description 描述 / Description
     * @param dataJson    数据内容 / Data content
     * @param source      来源 / Source
     * @param createdBy   创建人 ID / Creator user ID
     * @return 新测试数据集 / New test data set
     */
    public static TestDataSet create(String setName, String description, String dataJson, 
                                     String source, Long createdBy) {
        return TestDataSet.builder()
                .setName(setName).description(description).dataJson(dataJson)
                .source(source).createdBy(createdBy).build();
    }

    /**
     * 更新数据内容
     * Update data content
     *
     * @param dataJson 数据内容 / Data content
     */
    public void updateData(String dataJson) {
        this.dataJson = dataJson;
    }

}
