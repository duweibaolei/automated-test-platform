package com.dwl.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 13:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_repository")
@Schema(description = """
        Git仓库配置
        Git Repository Configuration
        """)
public class SysRepository extends BaseEntity {

    @Schema(description = """
            仓库名称
            Repository name
            """, example = "automated-test-platform-service")
    private String repoName;

    @Schema(description = """
            仓库地址
            Repository URL
            """, example = "git@github.com:example/automated-test-platform-service.git")
    private String repoUrl;

    @Schema(description = """
            默认分支
            Default branch
            """, example = "main")
    private String branchDefault;

    @Schema(description = """
            认证方式: ssh/token/password
            Credential type
            """, example = "ssh")
    private String credentialType;

    @Schema(description = """
            认证密钥(加密存储)
            Credential key (encrypted)
            """)
    private String credentialKey;

    @Schema(description = """
            Webhook回调地址
            Webhook callback URL
            """)
    private String webhookUrl;

    @Schema(description = """
            Webhook签名密钥
            Webhook secret key
            """)
    private String webhookSecret;

    @Schema(description = """
            状态: 1-启用 0-禁用
            Status: 1-enabled, 0-disabled
            """, example = "1")
    private Integer status;

}
