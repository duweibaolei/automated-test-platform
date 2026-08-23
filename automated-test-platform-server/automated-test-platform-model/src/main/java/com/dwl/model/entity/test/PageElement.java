package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 页面元素对象库实体
 * Page Element Object Repository Entity
 * 对应表 page_element，管理被测系统的页面元素定位信息
 * Maps to table page_element, managing page element locator information for the system under test
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 19:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("page_element")
@Schema(description = """
        页面元素对象库
        Page Element Object Repository
        """)
public class PageElement extends BaseEntity {

    @Schema(description = """
            元素编码
            Element code (alias)
            """, example = "btn_login")
    private String elementCode;

    @Schema(description = """
            元素名称
            Element name
            """, example = "登录按钮")
    private String elementName;

    @Schema(description = """
            所属页面
            Page name
            """, example = "登录页")
    private String pageName;

    @Schema(description = """
            主定位类型
            Primary locator type: css/xpath/id/data-testid/name
            """, example = "css")
    private String locatorType;

    @Schema(description = """
            主定位值
            Primary locator value
            """, example = "#login-btn")
    private String locatorValue;

    @Schema(description = """
            备份定位类型
            Backup locator type
            """)
    private String backupLocatorType;

    @Schema(description = """
            备份定位值
            Backup locator value
            """)
    private String backupLocatorValue;

    @Schema(description = """
            描述
            Description
            """)
    private String description;

    @Schema(description = """
            来源
            Source: auto/manual/hybrid
            """, example = "manual")
    private String source;

    @Schema(description = """
            状态
            Status: 1-enabled, 0-disabled
            """, example = "1")
    private Integer status;

}
