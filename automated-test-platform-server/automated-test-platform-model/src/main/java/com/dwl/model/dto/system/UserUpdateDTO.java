package com.dwl.model.dto.system;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Update User DTO
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 23:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = """
        Update User Request
        """)
public class UserUpdateDTO extends UserSuperDTO implements Serializable {
    @Schema(description = """
            头像URL
            Avatar URL
            """)
    private String avatar;

    @Schema(description = """
            状态(0-正常 1-禁用)
            Status (0-normal, 1-disabled)
            """, example = "0")
    private Integer status;

}
