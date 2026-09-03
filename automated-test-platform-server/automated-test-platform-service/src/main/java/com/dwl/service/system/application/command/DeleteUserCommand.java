package com.dwl.service.system.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 删除用户命令
 * Delete User Command
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-30 17:30
 */
@Data
@Builder
@Schema(description = """
        删除用户命令
        Delete User Command
        """)
public class DeleteUserCommand implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = """
            用户 ID 不能为空
            User ID cannot be empty
            """)
    @Schema(description = """
            用户 ID
            User ID
            """, example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;
}
