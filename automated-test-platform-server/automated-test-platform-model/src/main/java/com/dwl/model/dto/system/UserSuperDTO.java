package com.dwl.model.dto.system;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * User Super Class
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 23:47
 */
@Data
@Schema(description = """
        User Super Class
        """)
public class UserSuperDTO implements Serializable {

    @Schema(description = """
            真实姓名
            realName
            """, example = "管理员")
    private String realName;

    @Schema(description = """
            邮箱
            Email address
            """, example = "admin@deltatest.com")
    private String email;

    @Schema(description = """
            角色 ID 列表
            Role ID list
            """, example = "[1, 2]")
    private List<Long> roles;

}
