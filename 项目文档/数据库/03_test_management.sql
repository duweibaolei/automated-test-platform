-- 自动化测试平台-测试管理域

-- 3.1 页面元素对象库
CREATE TABLE page_element
(
    id                   BIGINT       NOT NULL COMMENT '元素ID',
    element_code         VARCHAR(128) NOT NULL COMMENT '元素编码(别名)',
    element_name         VARCHAR(128) NOT NULL COMMENT '元素名称',
    page_name            VARCHAR(128)          DEFAULT NULL COMMENT '所属页面',
    locator_type         VARCHAR(32)  NOT NULL COMMENT '主定位类型: css/xpath/id/data-testid/name',
    locator_value        VARCHAR(512) NOT NULL COMMENT '主定位值',
    backup_locator_type  VARCHAR(32)           DEFAULT NULL COMMENT '备份定位类型',
    backup_locator_value VARCHAR(512)          DEFAULT NULL COMMENT '备份定位值',
    description          VARCHAR(256)          DEFAULT NULL COMMENT '描述',
    source               VARCHAR(16)  NOT NULL DEFAULT 'manual' COMMENT '来源: auto/manual/hybrid',
    status               TINYINT      NOT NULL DEFAULT 1 COMMENT '状态: 1-启用 0-禁用',
    is_deleted           TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at           DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at           DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_element_code (element_code),
    KEY                  idx_page_name (page_name),
    KEY                  idx_source (source),
    KEY                  idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='页面元素对象库';

-- 3.2 测试用例
CREATE TABLE test_case
(
    id               BIGINT       NOT NULL COMMENT '用例ID',
    case_no          VARCHAR(32)  NOT NULL COMMENT '用例编号: TC-NNNN',
    case_name        VARCHAR(256) NOT NULL COMMENT '用例名称',
    module_name      VARCHAR(64)           DEFAULT NULL COMMENT '所属模块',
    source           VARCHAR(16)  NOT NULL DEFAULT 'manual' COMMENT '来源: auto/manual/hybrid',
    status           VARCHAR(16)  NOT NULL DEFAULT 'active' COMMENT '状态: active-正常 unstable-不稳定 disabled-失效 draft-草稿',
    health_score     INT          NOT NULL DEFAULT 100 COMMENT '健康度评分(0-100)',
    priority         VARCHAR(8)   NOT NULL DEFAULT 'P2' COMMENT '优先级: P0/P1/P2',
    version          INT          NOT NULL DEFAULT 1 COMMENT '版本号(乐观锁)',
    description      TEXT                  DEFAULT NULL COMMENT '用例描述',
    pre_condition    TEXT                  DEFAULT NULL COMMENT '前置条件',
    env_id           BIGINT                DEFAULT NULL COMMENT '执行环境ID',
    created_by       BIGINT                DEFAULT NULL COMMENT '创建人ID',
    last_modified_by BIGINT                DEFAULT NULL COMMENT '最后修改人ID',
    is_deleted       TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_case_no (case_no),
    KEY              idx_module (module_name),
    KEY              idx_source (source),
    KEY              idx_status (status),
    KEY              idx_health (health_score),
    KEY              idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试用例';

-- 3.3 用例步骤
CREATE TABLE case_step
(
    id           BIGINT      NOT NULL COMMENT '步骤ID',
    case_id      BIGINT      NOT NULL COMMENT '用例ID',
    step_order   INT         NOT NULL COMMENT '步骤顺序(从1开始)',
    element_id   BIGINT               DEFAULT NULL COMMENT '操作元素ID',
    action_type  VARCHAR(32) NOT NULL COMMENT '动作类型: click/fill/select/waitFor/hover/scroll/navigate/assert',
    action_value VARCHAR(512)         DEFAULT NULL COMMENT '输入值(动作参数)',
    assert_type  VARCHAR(32)          DEFAULT NULL COMMENT '断言类型: url_contains/visible/text_match/value_contains/attribute',
    assert_value VARCHAR(512)         DEFAULT NULL COMMENT '断言期望值',
    wait_timeout INT                  DEFAULT NULL COMMENT '等待超时(毫秒)',
    description  VARCHAR(256)         DEFAULT NULL COMMENT '步骤描述',
    is_deleted   TINYINT     NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY          idx_case_id_order (case_id, step_order),
    KEY          idx_element_id (element_id),
    KEY          idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用例步骤';

-- 3.4 用例版本历史
CREATE TABLE case_version
(
    id             BIGINT   NOT NULL COMMENT '版本ID',
    case_id        BIGINT   NOT NULL COMMENT '用例ID',
    version        INT      NOT NULL COMMENT '版本号',
    snapshot_json  JSON     NOT NULL COMMENT '用例快照(含步骤完整数据)',
    change_summary VARCHAR(256)      DEFAULT NULL COMMENT '变更摘要',
    modified_by    BIGINT            DEFAULT NULL COMMENT '修改人ID',
    is_deleted     TINYINT  NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_case_version (case_id, version),
    KEY            idx_case_id (case_id),
    KEY            idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用例版本历史';

-- 3.5 用例标签
CREATE TABLE case_tag
(
    id         BIGINT      NOT NULL COMMENT '标签ID',
    tag_name   VARCHAR(64) NOT NULL COMMENT '标签名称',
    tag_color  VARCHAR(16)          DEFAULT NULL COMMENT '标签颜色',
    is_deleted TINYINT     NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_tag_name (tag_name),
    KEY        idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用例标签';

-- 3.6 用例标签关联
CREATE TABLE case_tag_relation
(
    id         BIGINT   NOT NULL COMMENT '用例标签关联ID',
    case_id    BIGINT   NOT NULL COMMENT '用例ID',
    tag_id     BIGINT   NOT NULL COMMENT '标签ID',
    is_deleted TINYINT  NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_case_tag (case_id, tag_id),
    KEY        idx_tag_id (tag_id),
    KEY        idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用例标签关联';

-- 3.7 业务链路
CREATE TABLE business_link
(
    id               BIGINT       NOT NULL COMMENT '链路ID',
    link_no          VARCHAR(32)  NOT NULL COMMENT '链路编号: BL-NNNN',
    link_name        VARCHAR(256) NOT NULL COMMENT '链路名称',
    description      TEXT                  DEFAULT NULL COMMENT '链路描述',
    source           VARCHAR(16)  NOT NULL DEFAULT 'manual' COMMENT '来源: auto/manual/hybrid',
    status           TINYINT      NOT NULL DEFAULT 1 COMMENT '状态: 1-启用 0-禁用',
    version          INT          NOT NULL DEFAULT 1 COMMENT '版本号(乐观锁)',
    created_by       BIGINT                DEFAULT NULL COMMENT '创建人ID',
    last_modified_by BIGINT                DEFAULT NULL COMMENT '最后修改人ID',
    is_deleted       TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_link_no (link_no),
    KEY              idx_source (source),
    KEY              idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务链路';

-- 3.8 链路节点
CREATE TABLE link_node
(
    id              BIGINT       NOT NULL COMMENT '节点ID',
    link_id         BIGINT       NOT NULL COMMENT '所属链路ID',
    node_order      INT          NOT NULL COMMENT '节点顺序',
    node_type       VARCHAR(32)  NOT NULL COMMENT '节点类型: frontend_page/backend_api/backend_service/database_table',
    node_name       VARCHAR(256) NOT NULL COMMENT '节点名称',
    node_identifier VARCHAR(512)          DEFAULT NULL COMMENT '节点标识(如接口路径、表名)',
    assert_rule     VARCHAR(512)          DEFAULT NULL COMMENT '断言规则',
    is_deleted      TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY             idx_link_id_order (link_id, node_order),
    KEY             idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='链路节点';

-- 3.9 用例与链路关联
CREATE TABLE case_link_relation
(
    id         BIGINT   NOT NULL COMMENT '用例与链路关联ID',
    case_id    BIGINT   NOT NULL COMMENT '用例ID',
    link_id    BIGINT   NOT NULL COMMENT '链路ID',
    is_deleted TINYINT  NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_case_link (case_id, link_id),
    KEY        idx_link_id (link_id),
    KEY        idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用例与链路关联';

-- 3.10 用例与变更分析关联(受影响标记)
CREATE TABLE case_analysis_relation
(
    id            BIGINT      NOT NULL COMMENT '用例与变更分析关联ID',
    case_id       BIGINT      NOT NULL COMMENT '用例ID',
    analysis_id   BIGINT      NOT NULL COMMENT '分析ID',
    affected_type VARCHAR(32) NOT NULL DEFAULT 'impacted' COMMENT '影响类型: impacted-受影响 need_update-需更新断言 need_new-需新增',
    resolved      TINYINT     NOT NULL DEFAULT 0 COMMENT '是否已处理: 1-是 0-否',
    is_deleted    TINYINT     NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_case_analysis (case_id, analysis_id),
    KEY           idx_analysis_id (analysis_id),
    KEY           idx_resolved (resolved),
    KEY           idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用例与变更分析关联';

-- 3.11 测试数据集
CREATE TABLE test_data_set
(
    id          BIGINT       NOT NULL COMMENT '数据集ID',
    set_name    VARCHAR(128) NOT NULL COMMENT '数据集名称',
    description VARCHAR(256)          DEFAULT NULL COMMENT '描述',
    data_json   JSON         NOT NULL COMMENT '数据内容(键值对JSON)',
    source      VARCHAR(16)  NOT NULL DEFAULT 'manual' COMMENT '来源: auto/manual',
    created_by  BIGINT                DEFAULT NULL COMMENT '创建人ID',
    is_deleted  TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY         idx_source (source),
    KEY         idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试数据集';

-- 3.12 环境变量
CREATE TABLE env_variable
(
    id          BIGINT       NOT NULL COMMENT '变量ID',
    env_id      BIGINT                DEFAULT NULL COMMENT '环境ID(NULL表示全局)',
    var_key     VARCHAR(128) NOT NULL COMMENT '变量键',
    var_value   VARCHAR(512) NOT NULL COMMENT '变量值',
    description VARCHAR(256)          DEFAULT NULL COMMENT '描述',
    is_deleted  TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_env_key (env_id, var_key),
    KEY         idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='环境变量';
