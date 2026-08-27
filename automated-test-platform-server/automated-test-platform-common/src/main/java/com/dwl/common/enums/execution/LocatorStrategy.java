package com.dwl.common.enums.execution;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 定位策略枚举
 * <p>
 * Locator Strategy Enumeration
 * <p>
 * 定义元素定位的备用策略优先级
 * <p>
 * Defines the priority strategy for element locator fallback
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-27 10:00
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        定位策略枚举
        Locator Strategy Enumeration
        """)
public enum LocatorStrategy {

    /**
     * 优先使用主定位器, 失败时切换至备用定位器
     * Primary first, fallback to backup on failure
     */
    PRIMARY_BACKUP("primary-backup", "优先主定位器", "Primary first, then backup"),

    /**
     * 优先使用备用定位器, 失败时切换至主定位器
     * Backup first, fallback to primary on failure
     */
    BACKUP_PRIMARY("backup-primary", "优先备用定位器", "Backup first, then primary"),

    /**
     * 仅使用主定位器
     * Use primary locator only
     */
    PRIMARY_ONLY("primary-only", "仅主定位器", "Primary locator only"),

    /**
     * 仅使用备用定位器
     * Use backup locator only
     */
    BACKUP_ONLY("backup-only", "仅备用定位器", "Backup locator only"),

    /**
     * 智能选择最优定位器
     * Automatically select optimal locator
     */
    SMART("smart", "智能选择", "Auto-select optimal");


    @Schema(description = """
            定位策略编码
            Locator strategy code
            """, example = "primary-backup")
    private final String code;

    @Schema(description = """
            定位策略中文描述
            Locator strategy Chinese description
            """, example = "优先主定位器")
    private final String description;

    @Schema(description = """
            定位策略英文描述
            Locator strategy English description
            """, example = "Primary first, then backup")
    private final String englishDescription;
}
