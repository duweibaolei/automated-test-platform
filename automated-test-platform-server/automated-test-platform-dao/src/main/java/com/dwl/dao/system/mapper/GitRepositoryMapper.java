package com.dwl.dao.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.system.aggregate.GitRepository;
import org.apache.ibatis.annotations.Mapper;

/**
 * Git 仓库配置 Mapper 接口
 * Git Repository Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:21
 */
@Mapper
public interface GitRepositoryMapper extends BaseMapper<GitRepository> {
}
