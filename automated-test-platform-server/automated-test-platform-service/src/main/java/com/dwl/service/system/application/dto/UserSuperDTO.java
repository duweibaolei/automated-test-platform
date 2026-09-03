package com.dwl.service.system.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 用户公共字段 DTO(创建/更新用户的公共父类)
 * User Common Fields DTO (Common parent for create/update user)
 * <p>
 * 应用层入参，Controller 接收后转换为 Command 传给 ApplicationService
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 17:20
 */
@Data
@Schema(description = """
        用户公共字段 DTO
        User Common Fields DTO
        """)
public class UserSuperDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
            角色 ID 列表
            Role ID list
            """, example = "[1, 2]")
    private List<Long> roles;
}
