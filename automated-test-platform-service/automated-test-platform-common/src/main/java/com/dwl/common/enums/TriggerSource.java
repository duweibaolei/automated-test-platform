package com.dwl.common.enums;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 触发来源枚举
 * Trigger Source Enumeration
 * <p>
 * 定义测试任务的触发方式,包括Webhook自动触发 / 手动触发和定时触发
 * Defines the trigger method of test tasks, including Webhook auto-trigger,
 * manual trigger, and scheduled trigger
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-10 01:28
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        触发来源枚举
        Trigger Source Enumeration
        """)
public enum TriggerSource {

    AUTO("auto", "Webhook自动触发"),
    MANUAL("manual", "手动触发"),
    SCHEDULED("scheduled", "定时触发");


    @Schema(description = """
            触发来源编码
            Trigger source code
            """, example = "auto")
    private final String code;

    @Schema(description = """
            触发来源描述
            Trigger source description
            """, example = "Webhook自动触发")
    private final String description;
}
