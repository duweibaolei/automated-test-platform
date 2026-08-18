package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 业务链路实体
 * Business Link Entity
 * 对应表 business_link,定义端到端的业务链路及其元信息
 * Maps to table business_link, defining end-to-end business links and their metadata.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 14:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("business_link")
@Schema(description = """
        业务链路
        Business Link
        """)
public class BusinessLink extends BaseEntity {

    @Schema(description = """
            链路编号
            Link number: BL-NNNN
            """, example = "BL-0001")
    private String linkNo;

    @Schema(description = """
            链路名称
            Link name
            """, example = "用户注册到首次购买全流程")
    private String linkName;

    @Schema(description = """
            链路描述
            Link description
            """)
    private String description;

    @Schema(description = """
            来源
            Source: auto/manual/hybrid
            """, example = "manual")
    private String source;

    @Schema(description = """
            状态
            Status: 1-enabled, 0-disabled
            """, example = "1")
    private Integer status;

    @Schema(description = """
            版本号
            Version number (optimistic lock)
            """, example = "1")
    @Version
    private Integer version;

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
