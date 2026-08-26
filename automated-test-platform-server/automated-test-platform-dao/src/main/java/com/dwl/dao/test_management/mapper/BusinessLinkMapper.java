package com.dwl.dao.test_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwl.model.domain.test_management.aggregate.BusinessLink;
import org.apache.ibatis.annotations.Mapper;

/**
 * 业务链路 Mapper 接口
 * <p>
 * Business Link Mapper Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 21:27
 */
@Mapper
public interface BusinessLinkMapper extends BaseMapper<BusinessLink> {
}
