package com.dwl.service.system.application.command;

import com.dwl.common.enums.EnableStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 更新用户状态命令
 * Update User Status Command
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-30 18:01
 */
@Data
@Builder
@Schema(description = """
        更新用户状态命令
        Update User Status Command
        """)
public class UpdateUserStatusCommand implements Serializable {

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

    @NotNull(message = """
            状态不能为空
            Status cannot be empty
            """)
    @Schema(description = """
            状态：1-启用 0-禁用
            Status: 1-enabled, 0-disabled
            """, example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED,
            implementation = EnableStatus.class)
    private Integer status;
}
