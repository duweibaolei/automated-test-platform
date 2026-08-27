package com.dwl.model.domain.test_management.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 用例标签聚合根
 * <p>
 * Case Tag Aggregate Root
 * <p>
 * 测试管理域的聚合根, 定义测试用例的分类标签
 * <p>
 * Aggregate root of the Test Management domain, defining classification tags for test cases.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("case_tag")
@Schema(description = """
        用例标签聚合根
        Case Tag Aggregate Root
        """)
public class CaseTag extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            Tag ID
            """)
    private Long id;

    @Schema(description = """
            Tag name
            """, example = "冒烟测试")
    private String tagName;

    @Schema(description = """
            Tag color
            """, example = "#FF5722")
    private String tagColor;

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
     * 工厂方法: 创建用例标签
     * <p>
     * Factory Method: Create case tag
     *
     * @param tagName  Tag name
     * @param tagColor Tag color
     * @return New case tag
     */
    public static CaseTag create(String tagName, String tagColor) {
        return CaseTag.builder().tagName(tagName).tagColor(tagColor).build();
    }

}
