package com.dwl.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * 安全工具类
 * <p>
 * Security Utility Class
 * <p>
 * 提供从 Spring Security 上下文中获取当前登录用户信息的方法
 * <p>
 * Provides methods to retrieve current login user information
 * from the Spring Security context
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-10 02:04
 */
public final class SecurityUtil {

    /**
     * 私有构造函数, 防止实例化
     * <p>
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
     * <p>
     * Get current login user ID from SecurityContext
     * <p>
     * 从 Spring Security 上下文中提取已认证用户的 ID
     * <p>
     * Extracts the authenticated user's ID from the Spring Security context
     * <p>
     * 如果用户未认证或上下文中无信息, 返回 null
     * <p>
     * Returns null if the user is not authenticated or context is empty
     *
     * @return 当前登录用户 ID, 未认证时返回 null
     * Current login user ID, or null if not authenticated
     */
    public static Long getCurrentUserId() {

        return Optional.ofNullable(getAuthentication()).map(Authentication::getPrincipal).filter(Long.class::isInstance)
                .map(Long.class::cast)
                .orElse(null);
    }

    /**
     * 获取当前登录用户 ID (字符串形式)
     * <p>
     * Get current login user ID as String from SecurityContext
     * <p>
     * 从 Spring Security 上下文中提取已认证用户的 ID 字符串
     * <p>
     * Extracts the authenticated user's ID as a string from the Spring Security context
     *
     * @return 当前登录用户 ID 字符串, 未认证时返回 null
     * Current login user ID string, or null if not authenticated
     */
    public static String getCurrentUserIdAsString() {
        Long userId = getCurrentUserId();
        return Optional.ofNullable(userId).map(String::valueOf).orElse(null);
    }

    /**
     * 获取当前登录用户名
     * <p>
     * Get current login username from SecurityContext
     * <p>
     * 从 Spring Security 上下文中提取已认证用户的用户名
     * <p>
     * Extracts the authenticated user's username from the Spring Security context
     * <p>
     * 如果用户未认证或上下文中无信息, 返回 null
     * <p>
     * Returns null if the user is not authenticated or context is empty
     *
     * @return 当前登录用户名, 未认证时返回 null
     * Current login username, or null if not authenticated
     */
    public static String getCurrentUsername() {
        return Optional.ofNullable(getAuthentication())
                .map(Authentication::getName)
                .orElse(null);

    }

    /**
     * 判断当前用户是否已认证
     * <p>
     * Check if the current user is authenticated
     * <p>
     * 检查 Spring Security 上下文中是否存在已认证的 Authentication 对象
     * <p>
     * Checks whether an authenticated Authentication object exists
     * in the Spring Security context
     *
     * @return 是否已认证
     * Whether authenticated
     */
    public static boolean isAuthenticated() {
        return Optional.ofNullable(getAuthentication())
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .filter(principal -> !"anonymousUser".equals(principal))
                .isPresent();
    }

    /**
     * 获取当前 Authentication 对象
     * <p>
     * Get current Authentication object from SecurityContext
     *
     * @return Authentication 对象, 可能为 null
     * Authentication object, may be null
     */
    private static Authentication getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .orElse(null);
    }


}
