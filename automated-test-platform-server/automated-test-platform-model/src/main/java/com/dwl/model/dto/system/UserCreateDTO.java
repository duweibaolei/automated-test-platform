package com.dwl.model.dto.system;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * Create User DTO
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 23:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = """
        创建用户请求
        Create User Request
        """)
public class UserCreateDTO extends UserSuperDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            User Name
            """,
            example = "admin",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = """
            用户名不能为空
            User name cannot be empty
            """)
    private String username;

    @Schema(description = """
            Pass Word
            """,
            example = "123456",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = """
            密码不能为空
            Pass Word cannot be empty
            """)
    private String password;


}
