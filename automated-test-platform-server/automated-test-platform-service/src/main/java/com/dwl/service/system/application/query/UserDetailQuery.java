package com.dwl.service.system.application.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户详情查询
 * User Detail Query
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
@Data
@Builder
@Schema(description = """
        用户详情查询
        User Detail Query
        """)
public class UserDetailQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户 ID 不能为空")
    @Schema(description = """
            用户 ID
            User ID
            """, example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;
}
