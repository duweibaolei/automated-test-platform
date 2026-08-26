package com.dwl.dao.code_analysis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.code_analysis.aggregate.GitCommit;
import org.apache.ibatis.annotations.Mapper;

/**
 * Git 提交记录 Mapper 接口
 * <p>
 * Git Commit Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:06
 */
@Mapper
public interface GitCommitMapper extends BaseMapper<GitCommit> {
}
