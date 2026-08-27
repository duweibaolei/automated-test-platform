package com.dwl.common.enums.code_analysis;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 范围路径类型枚举
 * <p>
 * Scope Path Type Enumeration
 * <p>
 * 用于表示影响范围路径的格式类型
 * <p>
 * Used to represent the format type of affected scope path.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 23:35
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        范围路径类型
        Scope Path Type
        """)
public enum ScopePathType {

    /**
     * API 路径 - RESTful API 接口路径
     * API path - RESTful API interface path
     */
    API_PATH("api_path", "API 路径"),

    /**
     * 文件路径 - 源代码文件路径
     * File path - source code file path
     */
    FILE_PATH("file_path", "文件路径"),

    /**
     * 类名 - Java 类全限定名
     * Class name - Java fully qualified class name
     */
    CLASS_NAME("class_name", "类名"),

    /**
     * 方法名 - 方法标识
     * Method name - method identifier
     */
    METHOD_NAME("method_name", "方法名"),

    /**
     * SQL 表名 - 数据库表名称
     * SQL table name - database table name
     */
    TABLE_NAME("table_name", "表名"),

    /**
     * SQL 列名 - 数据库列名称
     * SQL column name - database column name
     */
    COLUMN_NAME("column_name", "列名"),

    /**
     * 组件路径 - 前端组件路径
     * Component path - frontend component path
     */
    COMPONENT_PATH("component_path", "组件路径"),

    /**
     * 路由路径 - 前端路由路径
     * Route path - frontend route path
     */
    ROUTE_PATH("route_path", "路由路径"),

    /**
     * 其他 - 未归类的路径类型
     * Other - unclassified path type
     */
    OTHER("other", "其他");

    /**
     * Type code
     */
    @Schema(description = """
            Type code
            """, example = "api_path")
    private final String code;

    /**
     * Chinese description
     */
    @Schema(description = """
            Chinese description
            """, example = "API 路径")
    private final String description;

    /**
     * Get enum by code
     *
     * @param code Type code
     * @return ScopePathType enum, or null if not found
     */
    public static ScopePathType of(String code) {
        for (ScopePathType spt : values()) {
            if (spt.code.equals(code)) {
                return spt;
            }
        }
        return null;
    }

    /**
     * 判断是否为 API 相关路径
     * Check if it is an API-related path
     *
     * @param code Type code
     * @return true if API-related
     */
    public static boolean isApiRelated(String code) {
        return (API_PATH.code.equals(code) || METHOD_NAME.code.equals(code));
    }

    /**
     * 判断是否为前端相关路径
     * Check if it is a frontend-related path
     *
     * @param code Type code
     * @return true if frontend-related
     */
    public static boolean isFrontendRelated(String code) {
        return (COMPONENT_PATH.code.equals(code) || ROUTE_PATH.code.equals(code));
    }

    /**
     * 判断是否为数据库相关路径
     * Check if it is a database-related path
     *
     * @param code Type code
     * @return true if database-related
     */
    public static boolean isDatabaseRelated(String code) {
        return (TABLE_NAME.code.equals(code) || COLUMN_NAME.code.equals(code));
    }

}
