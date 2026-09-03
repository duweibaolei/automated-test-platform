package com.dwl.service.system.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 创建用户请求 DTO
 * Create User Request DTO
 * <p>
 * 应用层入参，Controller 接收后转换为 CreateUserCommand 传给 ApplicationService
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 11:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = """
        创建用户请求
        Create User Request DTO
        """)
public class UserCreateDTO extends UserSuperDTO {

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
            密码
            Password
            """, example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
