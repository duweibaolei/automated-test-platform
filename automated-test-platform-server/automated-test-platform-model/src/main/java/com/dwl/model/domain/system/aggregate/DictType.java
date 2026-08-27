package com.dwl.model.domain.system.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.EnableStatus;
import com.dwl.model.domain.system.entity.DictData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 字典类型聚合根
 * <p>
 * Dictionary Type Aggregate Root
 * <p>
 * 系统管理域的聚合根, 封装字典类型的业务规则
 * 一个字典类型包含多个字典数据项 (DictData), 字典数据是聚合内实体
 * <p>
 * Aggregate root of the System Management domain, encapsulating dictionary type
 * business rules
 * <p>
 * A dictionary type contains multiple dictionary data items (DictData),
 * which are entities within the aggregate
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 19:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict_type")
@Schema(description = """
        字典类型聚合根
        Dictionary Type Aggregate Root
        """)
public class DictType extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            字典类型 ID
            Dictionary type ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            字典类型编码
            Dictionary type code
            """, example = "trigger_source")
    private String dictType;

    @Schema(description = """
            字典类型名称
            Dictionary type name
            """, example = "触发来源")
    private String dictName;

    @Schema(description = """
            Description
            """, example = "测试任务触发来源字典")
    private String description;

    @Schema(description = """
            Status
            """, example = "1",
            implementation = EnableStatus.class)
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
     * 字典数据项列表
     * <p>
     * Dictionary data item list
     */
    @Builder.Default
    private transient List<DictData> dictDataList = new ArrayList<>();

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建字典类型
     * <p>
     * Factory Method: Create dictionary type
     *
     * @param dictType    字典类型编码
     *                    Dictionary type code
     * @param dictName    字典类型名称
     *                    Dictionary type name
     * @param description 描述
     *                    Description
     * @param createdBy   创建人 ID
     *                    Creator user ID
     * @return 新字典类型
     * New DictType aggregate root
     */
    public static DictType create(String dictType, String dictName, String description,
                                  Long createdBy) {
        return DictType.builder()
                .dictType(dictType)
                .dictName(dictName)
                .description(description)
                .createdBy(createdBy)
                .status(EnableStatus.ENABLED.getValue())
                .dictDataList(new ArrayList<>())
                .build();
    }

    /**
     * 添加字典数据项
     * Add dictionary data item
     *
     * @param dictLabel 字典标签
     *                  Dictionary label (display value)
     * @param dictValue 字典值
     *                  Dictionary value (actual value)
     * @param sortOrder 排序
     *                  Sort order
     * @param cssClass  CSS class
     */
    public void addDictData(String dictLabel, String dictValue, Integer sortOrder,
                            String cssClass) {
        this.dictDataList.add(DictData.create(this.dictType, dictLabel, dictValue,
                sortOrder, cssClass, this.createdBy));
    }

    /**
     * 移除字典数据项
     * <p>
     * Remove dictionary data item
     *
     * @param dictValue 字典值
     *                  Dictionary value to remove
     */
    public void removeDictData(String dictValue) {
        this.dictDataList.removeIf(data -> dictValue.equals(data.getDictValue()));
    }

    /**
     * 启用字典类型
     * <p>
     * Enable dictionary type
     */
    public void enable() {
        if (Objects.nonNull(this.status) && this.status == EnableStatus.ENABLED.getValue()) {
            return;
        }
        this.status = EnableStatus.ENABLED.getValue();
    }

    /**
     * 禁用字典类型
     * <p>
     * Disable dictionary type
     */
    public void disable() {
        if (Objects.nonNull(this.status) && this.status == EnableStatus.DISABLED.getValue()) {
            return;
        }
        this.status = EnableStatus.DISABLED.getValue();
    }

    /**
     * 删除字典类型 (逻辑删除)
     * <p>
     * Delete dictionary type (logical delete)
     */
    public void delete() {
        this.isDelete = DeletedStatus.DELETED.getValue();
    }

    /**
     * 获取不可变的字典数据项列表
     * <p>
     * Get unmodifiable dictionary data item list
     *
     * @return 不可变字典数据列表
     * Unmodifiable dictionary data list
     */
    public List<DictData> getDictDataList() {
        return Collections.unmodifiableList(this.dictDataList);
    }

}
