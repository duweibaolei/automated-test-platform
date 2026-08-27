package com.dwl.model.domain.test_management.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.EnableStatus;
import com.dwl.common.enums.testmanagement.ElementSource;
import com.dwl.common.enums.testmanagement.LocatorType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 页面元素对象库聚合根
 * <p>
 * Page Element Object Repository Aggregate Root
 * <p>
 * 测试管理域的聚合根，管理被测系统的页面元素定位信息
 * <p>
 * Aggregate root of the Test Management domain, managing page element
 * locator information for the system under test.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("page_element")
@Schema(description = """
        页面元素对象库聚合根
        Page Element Object Repository Aggregate Root
        """)
public class PageElement extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            Element ID
            """)
    private Long id;

    @Schema(description = """
            Element code (alias)
            """, example = "btn_login")
    private String elementCode;

    @Schema(description = """
            Element name
            """, example = "登录按钮")
    private String elementName;

    @Schema(description = """
            Page name
            """, example = "登录页")
    private String pageName;

    @Schema(description = """
            主定位类型
            Primary locator type: css-CSS 选择器，xpath-XPath 表达式，id-ID 属性，data-testid-data-testid 属性，name-Name 属性，className-Class Name 属性，tagName-Tag Name 属性，linkText-链接文本，partialLinkText-部分链接文本
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
            Source: auto-自动识别，manual-手动录入，hybrid-混合模式
            """, example = "manual")
    private String source;

    @Schema(description = """
            状态
            Status: 1-enabled, 0-disabled
            """, example = "1", implementation = EnableStatus.class)
    private Integer status;

    @Schema(description = """
            Logical delete flag
            """)
    private Integer isDelete;

    @Schema(description = """
            Create time
            """)
    private LocalDateTime createdAt;

    @Schema(description = """
            Update time
            """)
    private LocalDateTime updatedAt;

    /* ================================================================
     * 业务方法 / Business Methods
     * ================================================================ */

    /**
     * 工厂方法：创建页面元素
     * <p>
     * Factory Method: Create page element
     *
     * @param elementCode  元素编码 Element code
     * @param elementName  元素名称 Element name
     * @param pageName     所属页面 Page name
     * @param locatorType  主定位类型 Primary locator type
     * @param locatorValue 主定位值 Primary locator value
     * @param source       来源 Source
     * @return 新页面元素 New page element
     * @throws IllegalArgumentException 当定位类型或来源不合法时 When locator type or source is invalid
     */
    public static PageElement create(String elementCode, String elementName, String pageName,
                                     String locatorType, String locatorValue, String source) {
        // 验证定位类型是否合法
        // Validate locator type
        if (!LocatorType.exists(locatorType)) {
            throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }

        // 验证来源是否合法
        // Validate source
        if (!ElementSource.exists(source)) {
            throw new IllegalArgumentException("Invalid source: " + source);
        }

        return PageElement.builder()
                .elementCode(elementCode).elementName(elementName).pageName(pageName)
                .locatorType(locatorType).locatorValue(locatorValue).source(source)
                .status(EnableStatus.ENABLED.getValue()).build();
    }

    /**
     * 更新定位方式
     * <p>
     * Update locator
     *
     * @param locatorType  定位类型 Locator type
     * @param locatorValue 定位值 Locator value
     * @throws IllegalArgumentException 当定位类型不合法时 When locator type is invalid
     */
    public void updateLocator(String locatorType, String locatorValue) {
        if (!LocatorType.exists(locatorType)) {
            throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    /**
     * 启用元素
     * Enable element
     */
    public void enable() {
        this.status = EnableStatus.ENABLED.getValue();
    }

    /**
     * 禁用元素
     * Disable element
     */
    public void disable() {
        this.status = EnableStatus.DISABLED.getValue();
    }

}
