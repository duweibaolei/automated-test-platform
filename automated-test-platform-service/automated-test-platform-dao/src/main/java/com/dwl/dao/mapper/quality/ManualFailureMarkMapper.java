package com.dwl.dao.mapper.quality;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.entity.quality.ManualFailureMark;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人工失败标记
 * Manual Failure Mark Mapper 接口
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 17:44
 */
@Mapper
public interface ManualFailureMarkMapper extends BaseMapper<ManualFailureMark> {
}
