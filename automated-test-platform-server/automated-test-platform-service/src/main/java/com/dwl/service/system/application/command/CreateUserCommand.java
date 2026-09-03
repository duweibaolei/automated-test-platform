package com.dwl.service.system.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 创建用户命令
 * Create User Command
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-30 17:52
 */
@Data
@Builder
@Schema(description = """
        创建用户命令
        Create User Command
        """)
public class CreateUserCommand implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = """
            用户名不能为空
            Username cannot be empty
            """)
    @Schema(description = """
            用户名
            Username
            """, example = "admin", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = """
            密码不能为空
            Password cannot be empty
            """)
    @Schema(description = """
            密码 (明文，Handler 内加密)
            Password (plain text, encrypted within Handler)
            """, example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = """
            真实姓名
            Real name
            """, example = "管理员")
    private String realName;

    @Schema(description = """
            邮箱
            Email
            """, example = "admin@example.com")
    private String email;

    @Schema(description = """
            初始角色 ID 列表
            Initial role ID list
            """)
    private List<Long> roleIds;
}
