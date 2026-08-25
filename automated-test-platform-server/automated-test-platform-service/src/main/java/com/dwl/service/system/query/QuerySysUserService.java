package com.dwl.service.system.query;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dwl.common.result.PageResult;
import com.dwl.model.dto.system.UserCreateDTO;
import com.dwl.model.entity.system.SysUser;
import com.dwl.model.vo.system.UserVO;

/**
 * 查询系统用户 服务接口
 * Query System User Service Interface
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-24 00:46
 */
public interface QuerySysUserService extends IService<SysUser> {

    /**
     * Get user by username
     *
     * @param username Username
     * @return System user entity
     */
    SysUser getByUsername(String username);

    /**
     * Get user detail
     *
     * @param id User ID
     * @return User view object
     */
    UserVO getUserDetail(Long id);

    /**
     * Page query users
     *
     * @param username 用户名(模糊查询)
     *                 Username (fuzzy)
     * @param status   Status
     * @param pageNum  Page number
     * @param pageSize Page size
     * @return 分页结果
     * Paginated Result
     */
    PageResult<UserVO> pageUsers(String username, Integer status, int pageNum, int pageSize);

}
