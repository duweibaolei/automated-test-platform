package com.dwl.dao.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.system.aggregate.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户配置 Mapper 接口
 * <p>
 * User Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
