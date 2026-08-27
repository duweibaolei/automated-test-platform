package com.dwl.model.domain.system.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.EnableStatus;
import com.dwl.common.enums.system.CredentialType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Git 仓库配置聚合根
 * <p>
 * Git Repository Configuration Aggregate Root
 * <p>
 * 系统管理域的聚合根, 封装 Git 仓库配置的业务规则
 * 管理代码仓库的连接信息、认证方式和 Webhook 配置
 * <p>
 * Note: 类名使用 GitRepository 而非 Repository, 避免与 DDD 的 Repository 接口冲突
 * <p>
 * Note: Class name uses GitRepository instead of Repository to avoid conflict
 * with the DDD Repository interface.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 19:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_repository")
@Schema(description = """
        Git 仓库配置聚合根
        Git Repository Configuration Aggregate Root
        """)
public class GitRepository extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            仓库 ID
            Repository ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            仓库名称
            Repository name
            """, example = "automated-test-platform-service")
    private String repoName;

    @Schema(description = """
            仓库地址
            Repository URL
            """, example = "git@github.com:example/automated-test-platform.git")
    private String repoUrl;

    @Schema(description = """
            默认分支
            Default branch
            """, example = "main")
    private String branchDefault;

    @Schema(description = """
            认证方式
            Credential type
            """, example = "ssh",
            implementation = CredentialType.class)
    private String credentialType;

    @Schema(description = """
            认证密钥 (加密存储)
            Credential key (encrypted)
            """)
    private String credentialKey;

    @Schema(description = """
            Webhook 回调地址
            Webhook callback URL
            """)
    private String webhookUrl;

    @Schema(description = """
            Webhook 签名密钥
            Webhook secret key
            """)
    private String webhookSecret;

    @Schema(description = """
            状态
            Status: 1-enabled, 0-disabled
            """, example = "1", implementation = EnableStatus.class)
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
     * 工厂方法: 创建 Git 仓库配置
     * <p>
     * Factory Method: Create Git repository configuration
     *
     * @param repoName       仓库名称
     *                       Repository name
     * @param repoUrl        仓库地址
     *                       Repository URL
     * @param branchDefault  默认分支
     *                       Default branch
     * @param credentialType 认证方式
     *                       Credential type (ssh/token/password)
     * @param credentialKey  认证密钥
     *                       Credential key (encrypted)
     * @return 新 Git 仓库配置
     * New GitRepository aggregate root
     */
    public static GitRepository create(String repoName, String repoUrl, String branchDefault,
                                       String credentialType, String credentialKey) {
        /* 验证认证方式是否合法
         * Validate credential type */
        if (!CredentialType.exists(credentialType)) {
            throw new IllegalArgumentException("Invalid credential type: " + credentialType);
        }

        return GitRepository.builder()
                .repoName(repoName)
                .repoUrl(repoUrl)
                .branchDefault(branchDefault)
                .credentialType(credentialType)
                .credentialKey(credentialKey)
                .status(EnableStatus.ENABLED.getValue())
                .build();
    }

    /**
     * 更新仓库配置
     * <p>
     * Update repository configuration
     *
     * @param repoName      仓库名称
     *                      Repository name
     * @param repoUrl       仓库地址
     *                      Repository URL
     * @param branchDefault 默认分支
     *                      Default branch
     */
    public void update(String repoName, String repoUrl, String branchDefault) {
        this.repoName = repoName;
        this.repoUrl = repoUrl;
        this.branchDefault = branchDefault;
    }

    /**
     * 更新认证信息
     * <p>
     * Update credential information
     *
     * @param credentialType 认证方式
     *                       Credential type
     * @param credentialKey  认证密钥
     *                       Credential key (encrypted)
     */
    public void updateCredential(String credentialType, String credentialKey) {
        this.credentialType = credentialType;
        this.credentialKey = credentialKey;
    }

    /**
     * 配置 Webhook
     * <p>
     * Configure webhook
     *
     * @param webhookUrl    Webhook 回调地址
     *                      Webhook callback URL
     * @param webhookSecret Webhook 签名密钥
     *                      Webhook secret key
     */
    public void configureWebhook(String webhookUrl, String webhookSecret) {
        this.webhookUrl = webhookUrl;
        this.webhookSecret = webhookSecret;
    }

    /**
     * 启用仓库
     * <p>
     * Enable repository
     */
    public void enable() {
        if (Objects.nonNull(this.status) && this.status == EnableStatus.ENABLED.getValue()) {
            return;
        }
        this.status = EnableStatus.ENABLED.getValue();
    }

    /**
     * 禁用仓库
     * Disable repository
     */
    public void disable() {
        if (Objects.nonNull(this.status) && this.status == EnableStatus.DISABLED.getValue()) {
            return;
        }
        this.status = EnableStatus.DISABLED.getValue();
    }

    /**
     * 删除仓库 (逻辑删除)
     * Delete repository (logical delete)
     */
    public void delete() {
        this.isDelete = 1;
    }

}
