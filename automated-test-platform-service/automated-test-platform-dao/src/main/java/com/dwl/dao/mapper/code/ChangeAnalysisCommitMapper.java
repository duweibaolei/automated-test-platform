package com.dwl.dao.mapper.code;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.code.ChangeAnalysisCommit;
import org.apache.ibatis.annotations.Mapper;

/**
 * 变更分析-提交关联
 * Change Analysis-Commit Association Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:23
 */
@Mapper
public interface ChangeAnalysisCommitMapper extends BaseMapper<ChangeAnalysisCommit> {
}
