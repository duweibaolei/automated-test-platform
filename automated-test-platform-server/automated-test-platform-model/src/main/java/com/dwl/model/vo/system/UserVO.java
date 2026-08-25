package com.dwl.model.vo.system;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User View Object
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 23:59
 */
@Data
@Schema(description = """
        User View Object
        """)
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            User ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            User Name
            """, example = "admin")
    private String username;

    @Schema(description = """
            昵称
            Nickname
            """, example = "管理员")
    private String nickname;

    @Schema(description = """
            Email address
            """, example = "admin@deltatest.com")
    private String email;

    @Schema(description = """
            头像 URL
            Avatar URL
            """)
    private String avatar;

    @Schema(description = """
            Status
            """, example = "0")
    private Integer status;

    @Schema(description = """
            Last login time
            """)
    private LocalDateTime lastLoginTime;

    @Schema(description = """
            Creation time
            """)
    private LocalDateTime createTime;

    @Schema(description = """
            Role list
            """)
    private List<RoleVO> roles;
}
