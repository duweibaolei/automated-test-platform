package com.dwl.dao.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.quality.entity.ManualFailureMark;
import org.apache.ibatis.annotations.Mapper;

/**
 * 手动失败标记 Mapper 接口
 * <p>
 * Manual Failure Mark Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:14
 */
@Mapper
public interface ManualFailureMarkMapper extends BaseMapper<ManualFailureMark> {
}
