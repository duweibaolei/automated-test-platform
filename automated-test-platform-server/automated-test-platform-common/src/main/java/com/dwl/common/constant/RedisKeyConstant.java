package com.dwl.common.constant;


/**
 * Redis Key Constants
 * <p>
 * 定义系统中所有 Redis 缓存键的前缀，确保键命名规范统一
 * <p>
 * Defines all redis cache key prefixes in the system,
 * ensuring consistent and standardized key naming conventions
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-05 19:22
 */
public final class RedisKeyConstant {


    /**
     * 私有构造函数
     * <p>
     * Private constructor to prevent instantiation
     */
    private RedisKeyConstant() {
        throw new UnsupportedOperationException("""
                常量类不允许实例化
                Constants class cannot be instantiated
                """);
    }

    /* ============================================================
     *  登陆认证
     *  Login Authentication
     * ============================================================ */

    /**
     * 登陆 Token 缓存键前缀
     * <p>
     * Login token cache key prefix
     * <p>
     * 完整的键格式：login:token:{token 值}
     * <p>
     * Full key format: login:token{token value}
     */
    public static final String LOGIN_TOKEN_KEY = "login:token:";

    /* ============================================================
     *  字典管理
     *  Dictionary Management
     * ============================================================ */

    /**
     * 字典类型缓存键设置
     * <p>
     * Dictionary type cache key prefix
     * <p>
     * Full key format: dict:type:{type code}
     */
    public static final String DICT_TYPE_KEY = "dict:type:";

    /**
     * Dictionary date cache key prefix
     * <p>
     * Full key format: dict:data:{type code}
     */
    public static final String DICT_DATA_KEY = "dict:data:";

    /* ============================================================
     *  任务状态
     *  Task Status
     * ============================================================ */

    /**
     * Task status cache key prefix
     * <p>
     * Full key format: task:status:{taskId}
     */
    public static final String TASK_STATUS_KEY = "task:status:";


}
