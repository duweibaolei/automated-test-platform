package com.dwl.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 环境配置实体
 * Environment Configuration Entity
 * 对应表 sys_environment,管理测试环境的基础配置信息
 * Maps to table sys_environment, managing basic configuration
 * for test environments
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 17:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_environment")
@Schema(description = """
        环境配置
        Environment Configuration
        """)
public class SysEnvironment extends BaseEntity {


    @Schema(description = """
            环境编码: test/staging/production
            Environment code
            """, example = "staging")
    private String envCode;

    @Schema(description = """
            环境名称
            Environment name
            """, example = "预发布环境")
    private String envName;

    @Schema(description = """
            基础URL
            Base URL
            """, example = "https://staging.example.com")
    private String baseUrl;

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

}
