package com.dwl.common.enums.execution;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 动作类型枚举
 * <p>
 * Action Type Enumeration
 * <p>
 * 定义 Web 自动化测试中可执行的操作类型
 * <p>
 * Defines the operation types executable in web automation testing
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-27 10:00
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        动作类型枚举
        Action Type Enumeration
        """)
public enum ActionType {

    // 导航类动作
    NAVIGATE("navigate", "页面导航", "Navigate to URL"),
    RELOAD("reload", "页面刷新", "Reload current page"),
    GO_BACK("goBack", "浏览器后退", "Go back in browser history"),
    GO_FORWARD("goForward", "浏览器前进", "Go forward in browser history"),

    // 元素交互动作
    CLICK("click", "点击", "Click on element"),
    DOUBLE_CLICK("dblClick", "双击", "Double click on element"),
    RIGHT_CLICK("rightClick", "右键", "Right click on element"),

    // 输入动作
    TYPE("type", "输入文本", "Type text into input field"),
    CLEAR("clear", "清空输入", "Clear input field"),
    SELECT_OPTION("selectOption", "选择选项", "Select option from dropdown"),
    CHECK("check", "勾选", "Check checkbox"),
    UNCHECK("uncheck", "取消勾选", "Uncheck checkbox"),

    // 键盘动作
    PRESS_KEY("pressKey", "按键", "Press keyboard key"),
    HOTKEY("hotkey", "快捷键", "Execute keyboard shortcut"),

    // 拖拽动作
    DRAG_AND_DROP("dragAndDrop", "拖拽", "Drag and drop element"),

    // 窗口/标签页动作
    SWITCH_TAB("switchTab", "切换标签页", "Switch to tab"),
    CLOSE_TAB("closeTab", "关闭标签页", "Close tab"),
    SWITCH_FRAME("switchFrame", "切换框架", "Switch to frame/iframe"),

    // 等待动作
    WAIT_FOR("waitFor", "等待", "Wait for condition"),
    WAIT_FOR_ELEMENT("waitForElement", "等待元素", "Wait for element to appear"),

    // 文件操作
    UPLOAD_FILE("uploadFile", "上传文件", "Upload file"),
    DOWNLOAD_FILE("downloadFile", "下载文件", "Download file"),

    // 其他动作
    HOVER("hover", "悬停", "Hover over element"),
    SCROLL("scroll", "滚动", "Scroll page or element"),
    EXECUTE_SCRIPT("executeScript", "执行脚本", "Execute JavaScript code");


    @Schema(description = """
            动作类型编码
            Action type code
            """, example = "click")
    private final String code;

    @Schema(description = """
            动作类型中文描述
            Action type Chinese description
            """, example = "点击")
    private final String description;

    @Schema(description = """
            动作类型英文描述
            Action type English description
            """, example = "Click on element")
    private final String englishDescription;
}
