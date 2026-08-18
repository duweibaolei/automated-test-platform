package com.dwl.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 字典类型实体
 * Dictionary Type Entity
 * 对应表 sys_dict_type,管理系统中各类字典的分类定义
 * Maps to table sys_dict_type, managing classification
 * definitions for various system dictionaries
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 17:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict_type")
@Schema(description = """
        字典类型
        Dictionary Type
        """)
public class SysDictType extends BaseEntity {


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
            描述
            Description
            """)
    private String description;

    @Schema(description = """
            状态: 1-启用 0-禁用
            Status: 1-enabled, 0-disabled
            """, example = "1")
    private Integer status;

    @Schema(description = """
            创建人ID
            Creator user ID
            """)
    private Long createdBy;
}
