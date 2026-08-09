package com.dwl.model.entity.system;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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

}
