-- 自动化测试平台-代码分析域

-- 2.1 Git提交记录
CREATE TABLE git_commit
(
    id             BIGINT       NOT NULL COMMENT '提交记录ID',
    repo_id        BIGINT       NOT NULL COMMENT '所属仓库ID',
    commit_hash    VARCHAR(40)  NOT NULL COMMENT 'Commit Hash(SHA-1)',
    branch         VARCHAR(128) NOT NULL COMMENT '分支名',
    author_name    VARCHAR(64)  NOT NULL COMMENT '提交者姓名',
    author_email   VARCHAR(128)          DEFAULT NULL COMMENT '提交者邮箱',
    commit_message TEXT         NOT NULL COMMENT '提交信息',
    commit_time    DATETIME     NOT NULL COMMENT '提交时间',
    additions      INT          NOT NULL DEFAULT 0 COMMENT '新增行数',
    deletions      INT          NOT NULL DEFAULT 0 COMMENT '删除行数',
    changed_files  INT          NOT NULL DEFAULT 0 COMMENT '变更文件数',
    trigger_source VARCHAR(16)  NOT NULL DEFAULT 'auto' COMMENT '触发来源: auto-Webhook自动 manual-手动触发',
    is_deleted     TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_repo_hash (repo_id, commit_hash),
    KEY            idx_branch (repo_id, branch),
    KEY            idx_commit_time (commit_time),
    KEY            idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Git提交记录';

-- 2.2 变更分析
CREATE TABLE change_analysis
(
    id                 BIGINT       NOT NULL COMMENT '分析ID',
    analysis_no        VARCHAR(32)  NOT NULL COMMENT '分析编号: CA-YYYYMMDD-NNNN',
    repo_id            BIGINT       NOT NULL COMMENT '仓库ID',
    branch             VARCHAR(128) NOT NULL COMMENT '分支名',
    start_commit_hash  VARCHAR(40)           DEFAULT NULL COMMENT '起始Commit(手动分析时指定)',
    end_commit_hash    VARCHAR(40)           DEFAULT NULL COMMENT '截止Commit(手动分析时指定)',
    trigger_source     VARCHAR(16)  NOT NULL DEFAULT 'auto' COMMENT '触发来源: auto-Webhook manual-手动',
    trigger_user_id    BIGINT                DEFAULT NULL COMMENT '手动触发用户ID',
    risk_level         VARCHAR(16)  NOT NULL DEFAULT 'low' COMMENT 'AI风险等级: high/medium/low',
    risk_level_manual  VARCHAR(16)           DEFAULT NULL COMMENT '手动调整风险等级: high/medium/low',
    risk_adjust_reason TEXT                  DEFAULT NULL COMMENT '风险等级调整原因',
    ai_summary         TEXT                  DEFAULT NULL COMMENT 'AI变更摘要',
    ai_test_suggestion TEXT                  DEFAULT NULL COMMENT 'AI测试建议',
    manual_description TEXT                  DEFAULT NULL COMMENT '手动补充变更说明',
    status             VARCHAR(16)  NOT NULL DEFAULT 'running' COMMENT '状态: running/completed/failed',
    is_deleted         TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at         DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at         DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_analysis_no (analysis_no),
    KEY                idx_repo_branch (repo_id, branch),
    KEY                idx_trigger_source (trigger_source),
    KEY                idx_status (status),
    KEY                idx_created_at (created_at),
    KEY                idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='变更分析';

-- 2.3 变更分析与提交关联
CREATE TABLE change_analysis_commit
(
    id          BIGINT   NOT NULL COMMENT '变更分析与提交关联ID',
    analysis_id BIGINT   NOT NULL COMMENT '分析ID',
    commit_id   BIGINT   NOT NULL COMMENT '提交记录ID',
    is_deleted  TINYINT  NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_analysis_commit (analysis_id, commit_id),
    KEY         idx_commit_id (commit_id),
    KEY         idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='变更分析与提交关联';

-- 2.4 影响范围
CREATE TABLE affected_scope
(
    id                      BIGINT       NOT NULL COMMENT '影响范围ID',
    analysis_id             BIGINT       NOT NULL COMMENT '所属分析ID',
    scope_type              VARCHAR(32)  NOT NULL COMMENT '范围类型: frontend_page/frontend_component/backend_api/backend_service/database_table',
    scope_name              VARCHAR(256) NOT NULL COMMENT '范围名称',
    scope_path              VARCHAR(512)          DEFAULT NULL COMMENT '范围路径/标识',
    selected_for_regression TINYINT      NOT NULL DEFAULT 1 COMMENT '是否选入回归范围: 1-是 0-否',
    is_deleted              TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at              DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at              DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY                     idx_analysis_id (analysis_id),
    KEY                     idx_scope_type (scope_type),
    KEY                     idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='影响范围';
