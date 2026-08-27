package com.dwl.model.domain.test_management.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.SourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 测试数据集聚合根
 * <p>
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

    @Serial
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
            Description
            """)
    private String description;

    @Schema(description = """
            Data content (JSON format)
            """)
    private String dataJson;

    @Schema(description = """
            Source: auto-automatic, manual-manual entry
            """, example = "manual",
            implementation = SourceType.class)
    private String source;

    @Schema(description = """
            Creator user ID
            """)
    private Long createdBy;

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

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建测试数据集
     * <p>
     * Factory Method: Create test data set
     *
     * @param setName     Data set name
     * @param description Description
     * @param dataJson    Data content
     * @param source      Source
     * @param createdBy   Creator user ID
     * @return 新测试数据集
     * New test data set
     */
    public static TestDataSet create(String setName, String description, String dataJson,
                                     String source, Long createdBy) {
        return TestDataSet.builder()
                .setName(setName).description(description).dataJson(dataJson)
                .source(source).createdBy(createdBy).build();
    }

    /**
     * 更新数据内容
     * <p>
     * Update data content
     *
     * @param dataJson Data content
     */
    public void updateData(String dataJson) {
        this.dataJson = dataJson;
    }

}
