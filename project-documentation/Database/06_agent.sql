-- 自动化测试平台-智能体域

-- 6.1 Agent对话记录
CREATE TABLE agent_conversation
(
    id            BIGINT      NOT NULL COMMENT 'Agent对话ID',
    session_id    VARCHAR(64) NOT NULL COMMENT '会话ID',
    role          VARCHAR(16) NOT NULL COMMENT '角色: user/agent/system / Role',
    content       TEXT        NOT NULL COMMENT '消息内容 / Message content',
    agent_type    VARCHAR(32) COMMENT 'Agent类型 / Agent type',
    sub_results   JSON COMMENT '子Agent结果 / Sub-agent results',
    model_version VARCHAR(64) COMMENT 'LLM模型版本 / LLM model version',
    token_usage   JSON COMMENT 'Token使用量 / Token usage',
    is_deleted    TINYINT     NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY           idx_session_id (session_id),
    KEY           idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Agent对话记录';

-- 6.2 Agent长期记忆
CREATE TABLE agent_memory
(
    id            BIGINT      NOT NULL COMMENT 'Agent记忆ID',
    memory_type   VARCHAR(32) NOT NULL COMMENT '记忆类型: pattern/preference/feedback / Memory type',
    source_type   VARCHAR(32) NOT NULL COMMENT '来源类型: risk_analysis/root_cause/manual_mark / Source type',
    source_id     BIGINT COMMENT '来源业务ID / Source business ID',
    pattern_key   VARCHAR(255) COMMENT '模式键(如模块路径) / Pattern key',
    pattern_value JSON COMMENT '模式值 / Pattern value',
    confidence    DECIMAL(5, 2) COMMENT '置信度 / Confidence',
    hit_count     INT         NOT NULL DEFAULT 0 COMMENT '命中次数 / Hit count',
    is_deleted    TINYINT     NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY           idx_pattern_key (pattern_key),
    KEY           idx_source_type_id (source_type, source_id),
    KEY           idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Agent长期记忆';

-- 6.3 Agent工具调用记录
CREATE TABLE agent_tool_call
(
    id            BIGINT      NOT NULL COMMENT '工具调用ID',
    session_id    VARCHAR(64) NOT NULL COMMENT '会话ID',
    agent_type    VARCHAR(32) NOT NULL COMMENT 'Agent类型 / Agent type',
    tool_name     VARCHAR(64) NOT NULL COMMENT '工具名称 / Tool name',
    input_hash    VARCHAR(64) COMMENT '输入哈希 / Input hash',
    output_hash   VARCHAR(64) COMMENT '输出哈希 / Output hash',
    latency_ms    INT COMMENT '耗时(毫秒) / Latency in ms',
    status        VARCHAR(16) NOT NULL COMMENT '状态: success/failed/timeout / Status',
    error_message TEXT COMMENT '错误信息 / Error message',
    is_deleted    TINYINT     NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY           idx_session_id (session_id),
    KEY           idx_agent_tool (agent_type, tool_name),
    KEY           idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Agent工具调用记录';
