package com.dwl.service.system.application.dto;

import com.dwl.common.enums.EnableStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 更新用户请求 DTO
 * Update User Request DTO
 * <p>
 * 应用层入参，Controller 接收后转换为 Command 传给 ApplicationService
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 14:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = """
        更新用户请求
        Update User Request DTO
        """)
public class UserUpdateDTO extends UserSuperDTO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            头像 URL
            Avatar URL
            """)
    private String avatar;

    @Schema(description = """
            状态：1-启用 0-禁用
            Status: 1-enabled, 0-disabled
            """, example = "1",
            implementation = EnableStatus.class)
    private Integer status;
}
