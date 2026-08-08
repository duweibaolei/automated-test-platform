-- 自动化测试平台-初始化数据

-- ---- 字典类型 ----
INSERT INTO sys_dict_type (id, dict_type, dict_name, description)
VALUES (1, 'trigger_source', '触发来源', '数据来源方式: 自动触发/手动触发/定时触发'),
       (2, 'source_type', '资产来源', '用例/元素/链路的来源: auto/manual/hybrid'),
       (3, 'risk_level', '风险等级', 'AI或手动评定的变更风险等级'),
       (4, 'analysis_status', '分析状态', '变更分析的执行状态'),
       (5, 'case_status', '用例状态', '测试用例的当前状态'),
       (6, 'case_priority', '用例优先级', '测试用例的优先级'),
       (7, 'action_type', '步骤动作类型', '用例步骤中的操作动作'),
       (8, 'assert_type', '断言类型', '用例步骤中的断言方式'),
       (9, 'locator_type', '元素定位类型', '页面元素的定位策略'),
       (10, 'scope_type', '影响范围类型', '变更影响范围的分类'),
       (11, 'affected_type', '受影响类型', '用例受变更影响的程度'),
       (12, 'task_status', '任务状态', '测试任务的执行状态'),
       (13, 'execution_status', '执行状态', '单条用例的执行结果'),
       (14, 'browser_type', '浏览器类型', '执行环境使用的浏览器'),
       (15, 'report_type', '报告类型', '测试报告的分类'),
       (16, 'report_status', '报告状态', '测试报告的发布状态'),
       (17, 'failure_reason', '失败原因', '手动标记的用例失败原因'),
       (18, 'defect_severity', '缺陷严重程度', '缺陷的严重程度分级'),
       (19, 'defect_status', '缺陷状态', '缺陷的处理状态'),
       (20, 'node_type', '链路节点类型', '业务链路节点的分类'),
       (21, 'node_status', '执行节点状态', 'Playwright执行节点的状态'),
       (22, 'credential_type', '仓库认证方式', 'Git仓库的认证方式'),
       (23, 'resource_type', '权限资源类型', '系统权限的资源分类'),
       (24, 'env_status', '环境状态', '测试环境的启用状态');

-- ---- 字典数据 ----
INSERT INTO sys_dict_data (id, dict_type, dict_label, dict_value, sort_order, css_class)
VALUES
-- 触发来源 (1-3)
(1, 'trigger_source', 'Webhook自动触发', 'auto', 1, 'tag-blue'),
(2, 'trigger_source', '手动触发', 'manual', 2, 'tag-green'),
(3, 'trigger_source', '定时触发', 'scheduled', 3, 'tag-orange'),
-- 资产来源 (4-6)
(4, 'source_type', '自动生成', 'auto', 1, 'tag-blue'),
(5, 'source_type', '手动录入', 'manual', 2, 'tag-green'),
(6, 'source_type', '混合修改', 'hybrid', 3, 'tag-orange'),
-- 风险等级 (7-9)
(7, 'risk_level', '高风险', 'high', 1, 'tag-red'),
(8, 'risk_level', '中风险', 'medium', 2, 'tag-orange'),
(9, 'risk_level', '低风险', 'low', 3, 'tag-green'),
-- 分析状态 (10-12)
(10, 'analysis_status', '分析中', 'running', 1, 'tag-blue'),
(11, 'analysis_status', '已完成', 'completed', 2, 'tag-green'),
(12, 'analysis_status', '失败', 'failed', 3, 'tag-red'),
-- 用例状态 (13-16)
(13, 'case_status', '正常', 'active', 1, 'tag-green'),
(14, 'case_status', '不稳定', 'unstable', 2, 'tag-orange'),
(15, 'case_status', '失效', 'disabled', 3, 'tag-red'),
(16, 'case_status', '草稿', 'draft', 4, 'tag-gray'),
-- 用例优先级 (17-19)
(17, 'case_priority', 'P0-最高', 'P0', 1, 'tag-red'),
(18, 'case_priority', 'P1-较高', 'P1', 2, 'tag-orange'),
(19, 'case_priority', 'P2-普通', 'P2', 3, 'tag-gray'),
-- 步骤动作类型 (20-27)
(20, 'action_type', '点击', 'click', 1, NULL),
(21, 'action_type', '输入', 'fill', 2, NULL),
(22, 'action_type', '选择', 'select', 3, NULL),
(23, 'action_type', '等待', 'waitFor', 4, NULL),
(24, 'action_type', '悬停', 'hover', 5, NULL),
(25, 'action_type', '滚动', 'scroll', 6, NULL),
(26, 'action_type', '导航', 'navigate', 7, NULL),
(27, 'action_type', '断言', 'assert', 8, NULL),
-- 断言类型 (28-32)
(28, 'assert_type', 'URL包含', 'url_contains', 1, NULL),
(29, 'assert_type', '元素可见', 'visible', 2, NULL),
(30, 'assert_type', '文本匹配', 'text_match', 3, NULL),
(31, 'assert_type', '值包含', 'value_contains', 4, NULL),
(32, 'assert_type', '属性校验', 'attribute', 5, NULL),
-- 元素定位类型 (33-37)
(33, 'locator_type', 'CSS选择器', 'css', 1, NULL),
(34, 'locator_type', 'XPath', 'xpath', 2, NULL),
(35, 'locator_type', 'ID', 'id', 3, NULL),
(36, 'locator_type', 'data-testid', 'data-testid', 4, NULL),
(37, 'locator_type', 'name属性', 'name', 5, NULL),
-- 影响范围类型 (38-42)
(38, 'scope_type', '前端页面', 'frontend_page', 1, NULL),
(39, 'scope_type', '前端组件', 'frontend_component', 2, NULL),
(40, 'scope_type', '后端接口', 'backend_api', 3, NULL),
(41, 'scope_type', '后端服务', 'backend_service', 4, NULL),
(42, 'scope_type', '数据库表', 'database_table', 5, NULL),
-- 受影响类型 (43-45)
(43, 'affected_type', '受影响', 'impacted', 1, 'tag-orange'),
(44, 'affected_type', '需更新断言', 'need_update', 2, 'tag-red'),
(45, 'affected_type', '需新增', 'need_new', 3, 'tag-blue'),
-- 任务状态 (46-51)
(46, 'task_status', '待执行', 'pending', 1, 'tag-gray'),
(47, 'task_status', '执行中', 'running', 2, 'tag-blue'),
(48, 'task_status', '已暂停', 'paused', 3, 'tag-orange'),
(49, 'task_status', '已完成', 'completed', 4, 'tag-green'),
(50, 'task_status', '失败', 'failed', 5, 'tag-red'),
(51, 'task_status', '已取消', 'cancelled', 6, 'tag-gray'),
-- 执行状态 (52-57)
(52, 'execution_status', '待执行', 'pending', 1, 'tag-gray'),
(53, 'execution_status', '执行中', 'running', 2, 'tag-blue'),
(54, 'execution_status', '通过', 'passed', 3, 'tag-green'),
(55, 'execution_status', '失败', 'failed', 4, 'tag-red'),
(56, 'execution_status', '跳过', 'skipped', 5, 'tag-gray'),
(57, 'execution_status', '错误', 'error', 6, 'tag-red'),
-- 浏览器类型 (58-62)
(58, 'browser_type', 'Chromium', 'chromium', 1, NULL),
(59, 'browser_type', 'Chrome', 'chrome', 2, NULL),
(60, 'browser_type', 'Firefox', 'firefox', 3, NULL),
(61, 'browser_type', 'WebKit', 'webkit', 4, NULL),
(62, 'browser_type', 'Edge', 'edge', 5, NULL),
-- 报告类型 (63-65)
(63, 'report_type', '任务报告', 'task', 1, NULL),
(64, 'report_type', '链路分析报告', 'link', 2, NULL),
(65, 'report_type', '变更质量报告', 'change', 3, NULL),
-- 报告状态 (66-67)
(66, 'report_status', '草稿', 'draft', 1, 'tag-gray'),
(67, 'report_status', '已发布', 'published', 2, 'tag-green'),
-- 失败原因 (68-70)
(68, 'failure_reason', '业务缺陷', 'bug', 1, 'tag-red'),
(69, 'failure_reason', '用例失效', 'flaky', 2, 'tag-orange'),
(70, 'failure_reason', '环境问题', 'env', 3, 'tag-gray'),
-- 缺陷严重程度 (71-73)
(71, 'defect_severity', '严重', 'critical', 1, 'tag-red'),
(72, 'defect_severity', '主要', 'major', 2, 'tag-orange'),
(73, 'defect_severity', '次要', 'minor', 3, 'tag-blue'),
-- 缺陷状态 (74-76)
(74, 'defect_status', '待处理', 'open', 1, 'tag-red'),
(75, 'defect_status', '已解决', 'resolved', 2, 'tag-orange'),
(76, 'defect_status', '已关闭', 'closed', 3, 'tag-green'),
-- 链路节点类型 (77-80)
(77, 'node_type', '前端页面', 'frontend_page', 1, NULL),
(78, 'node_type', '后端接口', 'backend_api', 2, NULL),
(79, 'node_type', '后端服务', 'backend_service', 3, NULL),
(80, 'node_type', '数据库表', 'database_table', 4, NULL),
-- 执行节点状态 (81-83)
(81, 'node_status', '健康', 'healthy', 1, 'tag-green'),
(82, 'node_status', '离线', 'offline', 2, 'tag-red'),
(83, 'node_status', '繁忙', 'busy', 3, 'tag-orange'),
-- 仓库认证方式 (84-86)
(84, 'credential_type', 'SSH密钥', 'ssh', 1, NULL),
(85, 'credential_type', 'Access Token', 'token', 2, NULL),
(86, 'credential_type', '账号密码', 'password', 3, NULL),
-- 权限资源类型 (87-89)
(87, 'resource_type', '菜单', 'menu', 1, NULL),
(88, 'resource_type', '按钮', 'button', 2, NULL),
(89, 'resource_type', '接口', 'api', 3, NULL);