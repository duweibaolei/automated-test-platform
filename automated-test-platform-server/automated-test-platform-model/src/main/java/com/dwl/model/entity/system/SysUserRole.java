package com.dwl.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 用户角色关联实体
 * User-Role Association Entity
 * 对应表 sys_user_role,用户与角色的多对多关联表
 * Maps to table sys_user_role, many-to-many association between users and roles
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 12:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_role")
@Schema(description = """
        用户角色关联
        User-Role Association
        """)
public class SysUserRole extends BaseEntity {

    @Schema(description = """
            用户ID
            User ID
            """, example = "1")
    private Long userId;

    @Schema(description = """
            角色ID
            Role ID
            """, example = "1")
    private Long roleId;

}
