package com.dwl.dao.execution.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.execution.aggregate.ExecNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * 执行节点 Mapper 接口
 * Exec Node Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:07
 */
@Mapper
public interface ExecNodeMapper extends BaseMapper<ExecNode> {
}
