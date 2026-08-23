package com.dwl.dao.mapper.code;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.code.GitCommit;
import org.apache.ibatis.annotations.Mapper;

/**
 * Git提交记录
 * Git Commit Record Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:24
 */
@Mapper
public interface GitCommitMapper extends BaseMapper<GitCommit> {
}
