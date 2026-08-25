package com.dwl.service.system.query.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwl.dao.mapper.system.SysUserRoleMapper;
import com.dwl.model.entity.system.SysUserRole;
import com.dwl.service.system.query.QuerySysUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 查询用户角色关联 服务实现类
 * Query User Role Association Service Implementation
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 01:59
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class QuerySysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements QuerySysUserRoleService {
}
