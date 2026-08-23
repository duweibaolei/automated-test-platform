package com.dwl.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 *
 * 字典数据实体
 * Dictionary Data Entity
 * 对应表 sys_dict_data,存储字典类型下的具体键值对数据项
 * Maps to table sys_dict_data, storing specific key-value
 * data items under dictionary types
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 17:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict_data")
@Schema(description = """
        字典数据
        Dictionary Data
        """)
public class SysDictData extends BaseEntity {


    @Schema(description = """
            所属字典类型编码
            Dictionary type code
            """, example = "trigger_source")
    private String dictType;


    @Schema(description = """
            字典标签(显示值)
            Dictionary label (display value)
            """, example = "手动触发")
    private String dictLabel;

    @Schema(description = """
            字典值(实际值)
            Dictionary value (actual value)
            """, example = "manual")
    private String dictValue;

    /**
     * 排序(升序)
     * Sort order (ascending)
     */
    @Schema(description = """
            排序(升序)
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
