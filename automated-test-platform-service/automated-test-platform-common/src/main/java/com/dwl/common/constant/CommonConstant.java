package com.dwl.common.constant;


/**
 * 通用常量
 * Common Constants
 * <p>
 * 定义系统中通用的常量值
 * Defines common constant values in the system
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-05 19:05
 */
public final class CommonConstant {

    /**
     * 私有构造器,防止实例化
     * private constructor to prevent instantiation
     */
    private CommonConstant() {
        throw new UnsupportedOperationException("""
                常量类不允许实例化
                Constants class cannot be instantiated
                """);
    }

    /* ==================== 是否标识 ====================
     * ==================== Yes-No flags ====================
     * */

    /**
     * YES (1)
     */
    public static final int YES = 1;
    /**
     * NO (0)
     */
    public static final int NO = 0;

    /* ==================== 分页默认值 ====================
     * ==================== Pagination Defaults ====================
     * */

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

    /* ==================== 逻辑删除标识 ====================
     * ==================== Logical Delete Flags ====================
     * */

    /**
     * Deleted
     */
    public static final int LOGICAL_DELETE_DELETED = 1;

    /**
     * 未删除（正常）
     * Not deleted (normal)
     */
    public static final int LOGICAL_DELETE_NORMAL = 0;

}
