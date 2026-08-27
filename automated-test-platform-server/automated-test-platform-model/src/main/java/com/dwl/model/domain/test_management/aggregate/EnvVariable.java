package com.dwl.model.domain.test_management.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.util.Objects;

import java.time.LocalDateTime;

/**
 * 环境变量聚合根
 * <p>
 * Environment Variable Aggregate Root
 * <p>
 * 测试管理域的聚合根, 管理各环境下的变量键值对配置
 * envId 为 NULL 表示全局变量, 通过 envId 跨域引用系统管理域的 Environment 聚合根
 * <p>
 * Aggregate root of the Test Management domain, managing variable key-value
 * configurations for each environment
 * <p>
 * Null envId indicates global variables.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("env_variable")
@Schema(description = """
        环境变量聚合根
        Environment Variable Aggregate Root
        """)
public class EnvVariable extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            变量 ID
            Variable ID
            """)
    private Long id;

    @Schema(description = """
            环境 ID
            Environment ID (null for global)
            """, example = "1")
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
     * 工厂方法: 创建环境变量
     * <p>
     * Factory Method: Create environment variable
     *
     * @param envId       环境 ID
     *                    Environment ID
     * @param varKey      变量键
     *                    Variable key
     * @param varValue    变量值
     *                    Variable value
     * @param description Description
     * @return 新环境变量
     * New environment variable
     */
    public static EnvVariable create(Long envId, String varKey, String varValue, String description) {
        return EnvVariable.builder()
                .envId(envId).varKey(varKey).varValue(varValue).description(description)
                .build();
    }

    /**
     * 更新变量值
     * <p>
     * Update variable value
     *
     * @param varValue 变量值  Variable value
     */
    public void updateValue(String varValue) {
        this.varValue = varValue;
    }

    /**
     * 判断是否为全局变量
     * <p>
     * Check if this is a global variable
     *
     * @return 是否为全局变量  true if global
     */
    public boolean isGlobal() {
        return Objects.isNull(this.envId);
    }

}
