package com.dwl.model.domain.test_management.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 用例标签关联实体
 * <p>
 * Case-Tag Association Entity
 * <p>
 * TestCase 聚合根内的实体, 用例与标签的多对多关联
 * <p>
 * Entity within TestCase aggregate root, many-to-many association between cases and tags
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("case_tag_relation")
@Schema(description = """
        用例标签关联实体
        Case-Tag Association Entity
        """)
public class CaseTagRelation extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            关联 ID
            Association ID
            """)
    private Long id;

    @Schema(description = """
            Case ID
            """, example = "1")
    private Long caseId;

    @Schema(description = """
            Tag ID
            """, example = "1")
    private Long tagId;

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
     * 工厂方法: 创建用例标签关联
     * <p>
     * Factory Method: Create case-tag relation
     *
     * @param caseId Case ID
     * @param tagId  Tag ID
     * @return 新关联实体
     * New association entity
     */
    public static CaseTagRelation create(Long caseId, Long tagId) {
        return CaseTagRelation.builder().caseId(caseId).tagId(tagId).build();
    }

}
