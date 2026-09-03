package com.dwl.service.system.application.vo;

import com.dwl.common.enums.EnableStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 用户视图对象
 * User View Object
 * <p>
 * 应用层出参，不暴露领域对象包含用户基本信息和角色列表
 * Application layer output parameter, does not expose domain objects, contains user basic information and role list
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
@Data
@Builder
@Schema(description = """
        用户视图对象
        User View Object
        """)
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            用户 ID
            User ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            用户名
            Username
            """, example = "admin")
    private String username;

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
            头像 URL
            Avatar URL
            """)
    private String avatar;

    @Schema(description = """
            状态：1-启用 0-禁用
            Status: 1-enabled, 0-disabled
            """, example = "1", implementation = EnableStatus.class)
    private Integer status;

    @Schema(description = """
            最后登录时间
            Last login time
            """)
    private LocalDateTime lastLoginTime;

    @Schema(description = """
            创建时间
            Created time
            """)
    private LocalDateTime createdAt;

    @Schema(description = """
            角色 ID 列表
            Role ID list
            """)
    private List<Long> roleIds;

    @Schema(description = """
            角色名称列表
            Role name list
            """)
    private List<String> roleNames;
}
