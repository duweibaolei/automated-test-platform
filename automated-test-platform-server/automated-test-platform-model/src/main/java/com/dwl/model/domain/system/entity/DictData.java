package com.dwl.model.domain.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.EnableStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 字典数据实体
 * <p>
 * Dictionary Data Entity
 * <p>
 * DictType 聚合根内的实体, 表示字典类型下的具体键值对数据项
 * 外部不能直接操作此实体, 必须通过 DictType 聚合根的方法
 * <p>
 * Entity within the DictType aggregate root, representing specific key-value data
 * items under a dictionary type
 * <p>
 * External objects cannot operate on this entity
 * directly; must go through DictType aggregate root methods.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict_data")
@Schema(description = """
        字典数据实体
        Dictionary Data Entity
        """)
public class DictData extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            字典数据 ID
            Dictionary data ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            所属字典类型编码
            Dictionary type code
            """, example = "trigger_source")
    private String dictType;

    @Schema(description = """
            字典标签
            Dictionary label (display value)
            """, example = "手动触发")
    private String dictLabel;

    @Schema(description = """
            字典值
            Dictionary value (actual value)
            """, example = "manual")
    private String dictValue;

    @Schema(description = """
            排序
            Sort order (ascending)
            """, example = "1")
    private Integer sortOrder;

    @Schema(description = """
            样式属性
            CSS class
            """, example = "tag-blue")
    private String cssClass;

    @Schema(description = """
            描述
            Description
            """, example = "手动触发测试任务")
    private String description;

    @Schema(description = """
            状态
            Status: 1-enabled, 0-disabled
            """, example = "1", implementation = EnableStatus.class)
    private Integer status;

    @Schema(description = """
            创建人 ID
            Creator user ID
            """, example = "1")
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

    /**
     * 工厂方法: 创建字典数据项
     * <p>
     * Factory Method: Create dictionary data item
     *
     * @param dictType  字典类型编码
     *                  Dictionary type code
     * @param dictLabel 字典标签
     *                  Dictionary label
     * @param dictValue 字典值
     *                  Dictionary value
     * @param sortOrder Sort order
     * @param cssClass  CSS class
     * @param createdBy Creator user ID
     * @return 新字典数据项
     * New DictData entity
     */
    public static DictData create(String dictType, String dictLabel, String dictValue,
                                  Integer sortOrder, String cssClass, Long createdBy) {
        return DictData.builder()
                .dictType(dictType)
                .dictLabel(dictLabel)
                .dictValue(dictValue)
                .sortOrder(sortOrder)
                .cssClass(cssClass)
                .createdBy(createdBy)
                .status(EnableStatus.ENABLED.getValue())
                .build();
    }

}
