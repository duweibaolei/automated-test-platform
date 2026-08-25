package com.dwl.service.system.query.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwl.dao.mapper.system.SysRoleMapper;
import com.dwl.model.entity.system.SysRole;
import com.dwl.service.system.query.QuerySysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 查询系统角色 服务实现类
 * Query System Role Service Implementation
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 02:05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class QuerySysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements QuerySysRoleService {
}
