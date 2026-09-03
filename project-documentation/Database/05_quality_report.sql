-- 自动化测试平台-质量报表域

-- 5.1 测试报告
CREATE TABLE test_report
(
    id                BIGINT        NOT NULL COMMENT '报告ID',
    report_no         VARCHAR(32)   NOT NULL COMMENT '报告编号: RPT-YYYYMMDD-NNNN',
    task_id           BIGINT        NOT NULL COMMENT '关联任务ID',
    report_type       VARCHAR(32)   NOT NULL DEFAULT 'task' COMMENT '报告类型: task-任务报告 link-链路分析报告 change-变更质量报告',
    trigger_source    VARCHAR(16)   NOT NULL DEFAULT 'auto' COMMENT '触发来源: auto/manual',
    total_count       INT           NOT NULL DEFAULT 0 COMMENT '总用例数',
    pass_count        INT           NOT NULL DEFAULT 0 COMMENT '通过数',
    fail_count        INT           NOT NULL DEFAULT 0 COMMENT '失败数',
    skip_count        INT           NOT NULL DEFAULT 0 COMMENT '跳过数',
    pass_rate         DECIMAL(5, 2) NOT NULL DEFAULT 0.00 COMMENT '通过率(%)',
    duration_ms       BIGINT                 DEFAULT NULL COMMENT '总执行耗时(毫秒)',
    ai_summary        TEXT                   DEFAULT NULL COMMENT 'AI分析摘要',
    ai_suggestion     TEXT                   DEFAULT NULL COMMENT 'AI修复建议',
    ai_analyzed       TINYINT       NOT NULL DEFAULT 0 COMMENT 'AI是否完成分析: 1-是 0-否',
    manual_conclusion TEXT                   DEFAULT NULL COMMENT '手动调整的报告结论',
    manual_remark     TEXT                   DEFAULT NULL COMMENT '手动补充测试备注',
    status            VARCHAR(16)   NOT NULL DEFAULT 'draft' COMMENT '状态: draft-草稿 published-已发布',
    is_deleted        TINYINT       NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at        DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at        DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_report_no (report_no),
    KEY               idx_task_id (task_id),
    KEY               idx_report_type (report_type),
    KEY               idx_created_at (created_at),
    KEY               idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试报告';

-- 5.2 执行结果与报告关联
CREATE TABLE report_execution_relation
(
    id           BIGINT   NOT NULL COMMENT '报告与执行结果关联ID',
    report_id    BIGINT   NOT NULL COMMENT '报告ID',
    execution_id BIGINT   NOT NULL COMMENT '执行记录ID',
    is_deleted   TINYINT  NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_report_execution (report_id, execution_id),
    KEY          idx_execution_id (execution_id),
    KEY          idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='报告与执行结果关联';

-- 5.3 AI根因分析
CREATE TABLE ai_root_cause
(
    id             BIGINT   NOT NULL COMMENT 'AI根因分析ID',
    execution_id   BIGINT   NOT NULL COMMENT '执行记录ID',
    report_id      BIGINT            DEFAULT NULL COMMENT '报告ID',
    possible_cause TEXT     NOT NULL COMMENT 'AI分析可能原因',
    confidence     INT      NOT NULL DEFAULT 0 COMMENT '置信度(0-100)',
    fix_suggestion TEXT              DEFAULT NULL COMMENT '修复建议',
    model_version  VARCHAR(32)       DEFAULT NULL COMMENT 'AI模型版本',
    analyzed_at    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '分析时间',
    is_deleted     TINYINT  NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY            idx_execution_id (execution_id),
    KEY            idx_report_id (report_id),
    KEY            idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI根因分析';

-- 5.4 手动失败原因标记
CREATE TABLE manual_failure_mark
(
    id             BIGINT      NOT NULL COMMENT '手动失败标记ID',
    execution_id   BIGINT      NOT NULL COMMENT '执行记录ID',
    failure_reason VARCHAR(32) NOT NULL COMMENT '失败原因: bug-业务缺陷 flaky-用例失效 env-环境问题',
    description    TEXT                 DEFAULT NULL COMMENT '补充说明',
    marked_by      BIGINT      NOT NULL COMMENT '标记人ID',
    marked_at      DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '标记时间',
    is_deleted     TINYINT     NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_execution (execution_id),
    KEY            idx_failure_reason (failure_reason),
    KEY            idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='手动失败原因标记';

-- 5.5 缺陷记录
CREATE TABLE defect_record
(
    id           BIGINT       NOT NULL COMMENT '缺陷ID',
    defect_no    VARCHAR(32)  NOT NULL COMMENT '缺陷编号: BUG-NNNN',
    defect_title VARCHAR(256) NOT NULL COMMENT '缺陷标题',
    severity     VARCHAR(16)  NOT NULL DEFAULT 'major' COMMENT '严重程度: critical/major/minor',
    description  TEXT                  DEFAULT NULL COMMENT '缺陷描述',
    execution_id BIGINT                DEFAULT NULL COMMENT '关联执行记录ID',
    case_id      BIGINT                DEFAULT NULL COMMENT '关联用例ID',
    commit_id    BIGINT                DEFAULT NULL COMMENT '关联提交ID',
    report_id    BIGINT                DEFAULT NULL COMMENT '关联报告ID',
    status       VARCHAR(16)  NOT NULL DEFAULT 'open' COMMENT '状态: open/resolved/closed',
    created_by   BIGINT       NOT NULL COMMENT '录入人ID',
    resolved_by  BIGINT                DEFAULT NULL COMMENT '解决人ID',
    resolved_at  DATETIME              DEFAULT NULL COMMENT '解决时间',
    is_deleted   TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_defect_no (defect_no),
    KEY          idx_severity (severity),
    KEY          idx_case_id (case_id),
    KEY          idx_execution_id (execution_id),
    KEY          idx_status (status),
    KEY          idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='缺陷记录';

-- 5.6 质量趋势统计(日聚合)
CREATE TABLE quality_daily_stats
(
    id               BIGINT        NOT NULL COMMENT '质量日统计ID',
    stat_date        DATE          NOT NULL COMMENT '统计日期',
    total_cases      INT           NOT NULL DEFAULT 0 COMMENT '总用例数',
    total_executed   INT           NOT NULL DEFAULT 0 COMMENT '执行用例数',
    total_passed     INT           NOT NULL DEFAULT 0 COMMENT '通过数',
    total_failed     INT           NOT NULL DEFAULT 0 COMMENT '失败数',
    pass_rate        DECIMAL(5, 2) NOT NULL DEFAULT 0.00 COMMENT '通过率(%)',
    new_defects      INT           NOT NULL DEFAULT 0 COMMENT '新增缺陷数',
    resolved_defects INT           NOT NULL DEFAULT 0 COMMENT '解决缺陷数',
    auto_cases       INT           NOT NULL DEFAULT 0 COMMENT '自动来源用例数',
    manual_cases     INT           NOT NULL DEFAULT 0 COMMENT '手动来源用例数',
    hybrid_cases     INT           NOT NULL DEFAULT 0 COMMENT '混合来源用例数',
    is_deleted       TINYINT       NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_stat_date (stat_date),
    KEY              idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='质量趋势日统计';