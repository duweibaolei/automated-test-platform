package com.dwl.model.domain.system.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.EnableStatus;
import com.dwl.common.enums.system.EnvCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Objects;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 环境配置聚合根
 * <p>
 * Environment Configuration Aggregate Root
 * <p>
 * 系统管理域的聚合根，封装测试环境的业务规则
 * 管理测试环境的基础配置信息，如环境编码、名称、基础 URL 等
 * <p>
 * Aggregate root of the System Management domain, encapsulating test environment
 * business rules
 * <p>
 * Manages basic configuration information for test environments,
 * such as environment code, name, and base URL.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 19:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_environment")
@Schema(description = """
        环境配置聚合根
        Environment Configuration Aggregate Root
        """)
public class Environment extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            环境 ID
            Environment ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            环境编码
            Environment code
            """, example = "staging",
            implementation = EnvCode.class)
    private String envCode;

    @Schema(description = """
            环境名称
            Environment name
            """, example = "预发布环境")
    private String envName;

    @Schema(description = """
            基础 URL
            Base URL
            """, example = "https://staging.example.com")
    private String baseUrl;

    @Schema(description = """
            Description
            """, example = "预发布测试环境")
    private String description;

    @Schema(description = """
            Status
            """, example = "1",
            implementation = EnableStatus.class)
    private Integer status;

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
     * 工厂方法：创建环境配置
     * <p>
     * Factory Method: Create environment configuration
     *
     * @param envCode     环境编码
     *                    Environment code
     * @param envName     环境名称
     *                    Environment name
     * @param baseUrl     基础
     *                    URL Base URL
     * @param description 描述
     *                    Description
     * @return 新环境配置
     * New Environment aggregate root
     * @throws IllegalArgumentException 当环境编码不合法时
     *                                  When env code is invalid
     */
    public static Environment create(String envCode, String envName, String baseUrl,
                                     String description) {
        /* 验证环境编码是否合法
         * Validate env code */
        if (!EnvCode.exists(envCode)) {
            throw new IllegalArgumentException("Invalid env code: " + envCode);
        }

        return Environment.builder()
                .envCode(envCode)
                .envName(envName)
                .baseUrl(baseUrl)
                .description(description)
                .status(EnableStatus.ENABLED.getValue())
                .build();
    }

    /**
     * 更新环境配置
     * <p>
     * Update environment configuration
     *
     * @param envName     环境名称
     *                    Environment name
     * @param baseUrl     基础
     *                    URL Base URL
     * @param description  Description
     */
    public void update(String envName, String baseUrl, String description) {
        this.envName = envName;
        this.baseUrl = baseUrl;
        this.description = description;
    }

    /**
     * 更新环境编码
     * <p>
     * Update environment code
     *
     * @param newEnvCode 新环境编码
     *                   New env code
     * @throws IllegalArgumentException 当环境编码不合法时
     * When env code is invalid
     */
    public void updateEnvCode(String newEnvCode) {
        if (!EnvCode.exists(newEnvCode)) {
            throw new IllegalArgumentException("Invalid env code: " + newEnvCode);
        }
        this.envCode = newEnvCode;
    }

    /**
     * 启用环境
     * <p>
     * Enable environment
     */
    public void enable() {
        if (Objects.nonNull(this.status) && this.status == EnableStatus.ENABLED.getValue()) {
            return;
        }
        this.status = EnableStatus.ENABLED.getValue();
    }

    /**
     * 禁用环境
     * <p>
     * Disable environment
     */
    public void disable() {
        if (Objects.nonNull(this.status) && this.status == EnableStatus.DISABLED.getValue()) {
            return;
        }
        this.status = EnableStatus.DISABLED.getValue();
    }

    /**
     * 删除环境 (逻辑删除)
     * <p>
     * Delete environment (logical delete)
     */
    public void delete() {
        this.isDelete = DeletedStatus.DELETED.getValue();
    }

}
