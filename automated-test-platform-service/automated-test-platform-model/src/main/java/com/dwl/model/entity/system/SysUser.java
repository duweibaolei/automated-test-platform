package com.dwl.model.entity.system;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 系统用户实体
 * System User Entity
 * 对应表 sys_user,存储平台用户的基本信息和认证数据
 * Maps to table sys_user, storing basic user information and authentication data
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-10 02:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
@Schema(description = """
        系统用户
        System User
        """)
public class SysUser extends BaseEntity {

    @Schema(description = """
            用户名
            UserName
            """, example = "admin")
    private String username;

    @Schema(description = """
            密码(加密存储)
            PassWord(encrypted)
            """)
    private String password;

    @Schema(description = """
            真实姓名
            Real Name
            """, example = "ADMIN")
    private String realName;

    @Schema(description = """
            邮箱
            Email address
            """, example = "admin@qq.com")
    private String email;

    @Schema(description = """
            头像 URL
            Avatar URL
            """)
    private String avatar;

    @Schema(description = """
            状态: 1-启用 0-禁用
            Status: 1-enabled, 0-disabled
            """, example = "1")
    private Integer status;

    @Schema(description = """
            最后登录时间
            Last login timestamp
            """)
    private LocalDateTime lastLoginTime;
}
