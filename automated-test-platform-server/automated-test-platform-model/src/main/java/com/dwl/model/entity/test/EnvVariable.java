package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 环境变量实体
 * Environment Variable Entity
 * 对应表 env_variable，管理各环境下的变量键值对配置。
 * Maps to table env_variable, managing variable key-value configurations for each environment.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 10:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("env_variable")
@Schema(description = """
        环境变量
        Environment Variable
        """)
public class EnvVariable extends BaseEntity {

    @Schema(description = """
            环境 ID
            Environment ID (NULL for global)
            """)
    private Long envId;

    @Schema(description = """
            变量键
            Variable key
            """, example = "BASE_URL")
    private String varKey;

    @Schema(description = """
            变量值
            Variable value
            """, example = "https://staging.example.com")
    private String varValue;

    @Schema(description = """
            描述
            Description
            """)
    private String description;

}
