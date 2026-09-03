package com.dwl.common.constant;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * Common Constants
 * <p>
 * 定义系统中通用的常量值
 * <p>
 * Defines common constant values in the system
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-05 19:05
 */
@Hidden
public final class CommonConstant {

    /**
     * 私有构造器, 防止实例化
     * Private constructor to prevent instantiation
     */
    private CommonConstant() {
        throw new UnsupportedOperationException("""
                常量类不允许实例化
                Constants class cannot be instantiated
                """);
    }

    /**
     * 默认版本号
     * Default version number (for optimistic locking)
     */
    public static final int DEFAULT_VERSION = 1;

    /* ============================================================
     *  分页默认值
     *  Pagination Defaults
     * ============================================================ */

    /**
     * Default page number (starts from 1)
     */
    public static final int DEFAULT_PAGE_NUM = 1;

    /**
     * Default page size
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * Maximum page size
     */
    public static final int MAX_PAGE_SIZE = 100;

}
