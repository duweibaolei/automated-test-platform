-- 自动化测试平台-执行调度域

-- 4.1 执行节点
CREATE TABLE exec_node
(
    id             BIGINT       NOT NULL COMMENT '节点ID',
    node_name      VARCHAR(64)  NOT NULL COMMENT '节点名称',
    node_host      VARCHAR(256) NOT NULL COMMENT '节点地址',
    node_port      INT          NOT NULL COMMENT '节点端口',
    browser_types  VARCHAR(256) NOT NULL DEFAULT 'chromium' COMMENT '支持浏览器类型(逗号分隔)',
    max_concurrent INT          NOT NULL DEFAULT 2 COMMENT '最大并发数',
    status         VARCHAR(16)  NOT NULL DEFAULT 'healthy' COMMENT '状态: healthy/offline/busy',
    last_heartbeat DATETIME              DEFAULT NULL COMMENT '最后心跳时间',
    is_deleted     TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY            idx_status (status),
    KEY            idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='执行节点';

-- 4.2 测试任务
CREATE TABLE test_task
(
    id              BIGINT       NOT NULL COMMENT '任务ID',
    task_no         VARCHAR(32)  NOT NULL COMMENT '任务编号: TK-NNNN',
    task_name       VARCHAR(256) NOT NULL COMMENT '任务名称',
    trigger_source  VARCHAR(16)  NOT NULL DEFAULT 'auto' COMMENT '触发来源: auto-变更自动 manual-手动 scheduled-定时',
    trigger_user_id BIGINT                DEFAULT NULL COMMENT '手动触发用户ID',
    analysis_id     BIGINT                DEFAULT NULL COMMENT '关联变更分析ID(自动触发时)',
    env_id          BIGINT                DEFAULT NULL COMMENT '执行环境ID',
    browser_type    VARCHAR(32)  NOT NULL DEFAULT 'chromium' COMMENT '浏览器类型',
    concurrency     INT          NOT NULL DEFAULT 1 COMMENT '并发数',
    retry_count     INT          NOT NULL DEFAULT 0 COMMENT '失败重试次数',
    schedule_cron   VARCHAR(64)           DEFAULT NULL COMMENT '定时CRON表达式(定时任务)',
    status          VARCHAR(16)  NOT NULL DEFAULT 'pending' COMMENT '状态: pending/running/paused/completed/failed/cancelled',
    progress        INT          NOT NULL DEFAULT 0 COMMENT '执行进度(百分比)',
    pass_count      INT          NOT NULL DEFAULT 0 COMMENT '通过数',
    fail_count      INT          NOT NULL DEFAULT 0 COMMENT '失败数',
    skip_count      INT          NOT NULL DEFAULT 0 COMMENT '跳过数',
    total_count     INT          NOT NULL DEFAULT 0 COMMENT '总用例数',
    start_time      DATETIME              DEFAULT NULL COMMENT '开始时间',
    end_time        DATETIME              DEFAULT NULL COMMENT '结束时间',
    duration_ms     BIGINT                DEFAULT NULL COMMENT '执行耗时(毫秒)',
    is_deleted      TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_task_no (task_no),
    KEY             idx_trigger_source (trigger_source),
    KEY             idx_status (status),
    KEY             idx_analysis_id (analysis_id),
    KEY             idx_created_at (created_at),
    KEY             idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试任务';

-- 4.3 任务与用例关联
CREATE TABLE task_case_relation
(
    id         BIGINT   NOT NULL COMMENT '任务与用例关联ID',
    task_id    BIGINT   NOT NULL COMMENT '任务ID',
    case_id    BIGINT   NOT NULL COMMENT '用例ID',
    is_deleted TINYINT  NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_task_case (task_id, case_id),
    KEY        idx_case_id (case_id),
    KEY        idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务与用例关联';

-- 4.4 任务执行记录(单条用例的执行实例)
CREATE TABLE task_execution
(
    id             BIGINT      NOT NULL COMMENT '执行ID',
    task_id        BIGINT      NOT NULL COMMENT '任务ID',
    case_id        BIGINT      NOT NULL COMMENT '用例ID',
    node_id        BIGINT               DEFAULT NULL COMMENT '执行节点ID',
    retry_index    INT         NOT NULL DEFAULT 0 COMMENT '重试序号(0为首次)',
    status         VARCHAR(16) NOT NULL DEFAULT 'pending' COMMENT '状态: pending/running/passed/failed/skipped/error',
    start_time     DATETIME             DEFAULT NULL COMMENT '开始时间',
    end_time       DATETIME             DEFAULT NULL COMMENT '结束时间',
    duration_ms    BIGINT               DEFAULT NULL COMMENT '执行耗时(毫秒)',
    failed_step    INT                  DEFAULT NULL COMMENT '失败步骤序号',
    error_message  TEXT                 DEFAULT NULL COMMENT '错误信息',
    screenshot_url VARCHAR(512)         DEFAULT NULL COMMENT '失败截图URL',
    video_url      VARCHAR(512)         DEFAULT NULL COMMENT '执行录像URL',
    is_deleted     TINYINT     NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY            idx_task_id (task_id),
    KEY            idx_case_id (case_id),
    KEY            idx_status (status),
    KEY            idx_task_case (task_id, case_id),
    KEY            idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务执行记录';

-- 4.5 执行步骤结果
CREATE TABLE execution_step_result
(
    id            BIGINT      NOT NULL COMMENT '执行步骤结果ID',
    execution_id  BIGINT      NOT NULL COMMENT '执行记录ID',
    step_order    INT         NOT NULL COMMENT '步骤序号',
    action_type   VARCHAR(32) NOT NULL COMMENT '动作类型',
    assert_type   VARCHAR(32)          DEFAULT NULL COMMENT '断言类型',
    assert_passed TINYINT              DEFAULT NULL COMMENT '断言结果: 1-通过 0-失败 NULL-无断言',
    actual_value  VARCHAR(512)         DEFAULT NULL COMMENT '实际值',
    duration_ms   BIGINT               DEFAULT NULL COMMENT '步骤耗时(毫秒)',
    error_message VARCHAR(512)         DEFAULT NULL COMMENT '错误信息',
    locator_used  VARCHAR(32)          DEFAULT NULL COMMENT '使用的定位策略: primary/backup',
    is_deleted    TINYINT     NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY           idx_execution_id (execution_id),
    KEY           idx_execution_step (execution_id, step_order),
    KEY           idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='执行步骤结果';