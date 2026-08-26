package com.dwl.dao.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.quality.aggregate.QualityDailyStats;
import org.apache.ibatis.annotations.Mapper;

/**
 * 质量日统计 Mapper 接口
 * Quality Daily Stats Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:15
 */
@Mapper
public interface QualityDailyStatsMapper extends BaseMapper<QualityDailyStats> {
}
