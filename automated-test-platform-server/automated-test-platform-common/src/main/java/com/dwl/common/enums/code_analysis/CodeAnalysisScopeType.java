package com.dwl.common.enums.code_analysis;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 影响范围类型枚举
 * <p>
 * Affected Scope Type Enumeration
 * <p>
 * 用于表示代码变更影响到的系统范围类型
 * <p>
 * Used to represent the system scope type affected by code changes.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 23:30
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        影响范围类型
        Affected Scope Type
        """)
public enum CodeAnalysisScopeType {

    /**
     * 前端页面 - 表示前端页面受到影响
     * Frontend page - indicates frontend page is affected
     */
    FRONTEND_PAGE("frontend_page", "前端页面"),

    /**
     * 前端组件 - 表示前端组件受到影响
     * Frontend component - indicates frontend component is affected
     */
    FRONTEND_COMPONENT("frontend_component", "前端组件"),

    /**
     * 后端 API - 表示后端 API 接口受到影响
     * Backend API - indicates backend API interface is affected
     */
    BACKEND_API("backend_api", "后端 API"),

    /**
     * 后端服务 - 表示后端服务逻辑受到影响
     * Backend service - indicates backend service logic is affected
     */
    BACKEND_SERVICE("backend_service", "后端服务"),

    /**
     * 数据库表 - 表示数据库表结构受到影响
     * Database table - indicates database table structure is affected
     */
    DATABASE_TABLE("database_table", "数据库表");

    /**
     * Type code
     */
    @Schema(description = """
            Type code
            """, example = "backend_api")
    private final String code;

    /**
     * Chinese description
     */
    @Schema(description = """
            Chinese description
            """, example = "后端 API")
    private final String description;

    /**
     * Get enum by code
     *
     * @param code Type code
     * @return ScopeType enum, or null if not found
     */
    public static CodeAnalysisScopeType of(String code) {
        for (CodeAnalysisScopeType st : values()) {
            if (st.code.equals(code)) {
                return st;
            }
        }
        return null;
    }

    /**
     * 判断是否为前端类型
     * <p>
     * Check if it is a frontend type
     *
     * @param code Type code
     * @return true if frontend type
     */
    public static boolean isFrontend(String code) {
        return FRONTEND_PAGE.code.equals(code) || FRONTEND_COMPONENT.code.equals(code);
    }

    /**
     * 判断是否为后端类型
     * <p>
     * Check if it is a backend type
     *
     * @param code Type code
     * @return true if backend type
     */
    public static boolean isBackend(String code) {
        return BACKEND_API.code.equals(code) || BACKEND_SERVICE.code.equals(code);
    }

}
