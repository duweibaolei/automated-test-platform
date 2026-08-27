package com.dwl.model.domain.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 用户角色关联实体
 * <p>
 * User-Role Association Entity
 * <p>
 * User 聚合根内的实体, 表示用户与角色的多对多关联
 * 外部不能直接操作此实体, 必须通过 User 聚合根的方法
 * <p>
 * Entity within the User aggregate root, representing the many-to-many
 * association between users and roles
 * <p>
 * External objects cannot operate on
 * this entity directly; must go through User aggregate root methods.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_role")
@Schema(description = """
        用户角色关联实体
        User-Role Association Entity
        """)
public class UserRole extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            关联 ID
            Association ID
            """, example = "1")
    private Long id;

    @Schema(description = """
            User ID
            """, example = "1")
    private Long userId;

    @Schema(description = """
            Role ID
            """, example = "1")
    private Long roleId;

    @Schema(description = """
            Logical delete flag
            """, example = "0",
            implementation = DeletedStatus.class)
    private Integer isDelete;

    @Schema(description = """
            Create time
            """)
    private LocalDateTime createdAt;

    @Schema(description = """
            Update time
            """)
    private LocalDateTime updatedAt;

    /**
     * 工厂方法: 创建用户角色关联
     * <p>
     * Factory Method: Create user-role association
     *
     * @param userId User ID
     * @param roleId Role ID
     * @return 用户角色关联
     * UserRole entity
     */
    public static UserRole create(Long userId, Long roleId) {
        return UserRole.builder()
                .userId(userId)
                .roleId(roleId)
                .build();
    }

}
