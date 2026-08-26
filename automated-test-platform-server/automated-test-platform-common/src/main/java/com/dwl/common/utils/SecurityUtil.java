package com.dwl.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全工具类
 * Security Utility Class
 * <p>
 * 提供从 Spring Security 上下文中获取当前登录用户信息的方法
 * Provides methods to retrieve current login user information
 * from the Spring Security context
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-10 02:04
 */
public final class SecurityUtil {

    /**
     * 私有构造函数,防止实例化
     * Private constructor to prevent instantiation
     */
    private SecurityUtil() {
        throw new UnsupportedOperationException("""
                工具类不允许实例化
                Utility class cannot be instantiated
                """);
    }

    /**
     * 获取当前登录用户 ID
     * Get current login user ID from SecurityContext
     * <p>
     * 从 Spring Security 上下文中提取已认证用户的 ID
     * Extracts the authenticated user's ID from the Spring Security context
     * <p>
     * 如果用户未认证或上下文中无信息,返回 null
     * Returns null if the user is not authenticated or context is empty
     *
     * @return 当前登录用户 ID,未认证时返回null
     * Current login user ID, or null if not authenticated
     */
    public static Long getCurrentUserId() {
        Authentication authentication = getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Long userId) {
            return userId;
        }
        return null;
    }

    /**
     * 获取当前登录用户 ID (字符串形式)
     * Get current login user ID as String from SecurityContext
     * <p>
     * 从 Spring Security 上下文中提取已认证用户的 ID 字符串
     * Extracts the authenticated user's ID as a string from the Spring Security context
     *
     * @return 当前登录用户 ID 字符串,未认证时返回 null
     * Current login user ID string, or null if not authenticated
     */
    public static String getCurrentUserIdAsString() {
        Long userId = getCurrentUserId();
        return userId != null ? userId.toString() : null;
    }

    /**
     * 获取当前登录用户名
     * Get current login username from SecurityContext
     * <p>
     * 从 Spring Security 上下文中提取已认证用户的用户名
     * Extracts the authenticated user's username from the Spring Security context
     * <p>
     * 如果用户未认证或上下文中无信息,返回 null
     * Returns null if the user is not authenticated or context is empty
     *
     * @return 当前登录用户名,未认证时返回 null
     * Current login username, or null if not authenticated
     */
    public static String getCurrentUsername() {
        Authentication authentication = getAuthentication();
        if (authentication != null && authentication.getName() != null) {
            return authentication.getName();
        }
        return null;
    }

    /**
     * 判断当前用户是否已认证
     * Check if the current user is authenticated
     * <p>
     * 检查 Spring Security 上下文中是否存在已认证的 Authentication 对象
     * Checks whether an authenticated Authentication object exists
     * in the Spring Security context
     *
     * @return 是否已认证
     * Whether authenticated
     */
    public static boolean isAuthenticated() {
        Authentication authentication = getAuthentication();
        return authentication != null && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getPrincipal());
    }

    /**
     * 获取当前 Authentication 对象
     * Get current Authentication object from SecurityContext
     *
     * @return Authentication 对象,可能为 null
     * Authentication object, may be null
     */
    private static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context != null ? context.getAuthentication() : null;
    }


}
