package com.dwl.dao.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.quality.aggregate.DefectRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 缺陷记录 Mapper 接口
 * Defect Record Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:13
 */
@Mapper
public interface DefectRecordMapper extends BaseMapper<DefectRecord> {
}
