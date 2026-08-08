-- 自动化测试平台-系统管理域

-- 1.1 系统用户
CREATE TABLE sys_user
(
    id              BIGINT       NOT NULL COMMENT '用户ID',
    username        VARCHAR(64)  NOT NULL COMMENT '用户名',
    password        VARCHAR(128) NOT NULL COMMENT '密码(加密)',
    real_name       VARCHAR(64)           DEFAULT NULL COMMENT '真实姓名',
    email           VARCHAR(128)          DEFAULT NULL COMMENT '邮箱',
    avatar          VARCHAR(256)          DEFAULT NULL COMMENT '头像URL',
    status          TINYINT      NOT NULL DEFAULT 1 COMMENT '状态: 1-启用 0-禁用',
    last_login_time DATETIME              DEFAULT NULL COMMENT '最后登录时间',
    is_deleted      TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username),
    KEY             idx_email (email),
    KEY             idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';

-- 1.2 系统角色
CREATE TABLE sys_role
(
    id          BIGINT      NOT NULL COMMENT '角色ID',
    role_code   VARCHAR(64) NOT NULL COMMENT '角色编码',
    role_name   VARCHAR(64) NOT NULL COMMENT '角色名称',
    description VARCHAR(256)         DEFAULT NULL COMMENT '描述',
    status      TINYINT     NOT NULL DEFAULT 1 COMMENT '状态: 1-启用 0-禁用',
    is_deleted  TINYINT     NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_role_code (role_code),
    KEY         idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色';

-- 1.3 系统权限
CREATE TABLE sys_permission
(
    id              BIGINT       NOT NULL COMMENT '权限ID',
    permission_code VARCHAR(128) NOT NULL COMMENT '权限编码',
    permission_name VARCHAR(64)  NOT NULL COMMENT '权限名称',
    resource_type   VARCHAR(32)  NOT NULL COMMENT '资源类型: menu-菜单 button-按钮 api-接口',
    parent_id       BIGINT                DEFAULT NULL COMMENT '父权限ID',
    sort_order      INT          NOT NULL DEFAULT 0 COMMENT '排序',
    status          TINYINT      NOT NULL DEFAULT 1 COMMENT '状态: 1-启用 0-禁用',
    is_deleted      TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_permission_code (permission_code),
    KEY             idx_parent_id (parent_id),
    KEY             idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统权限';

-- 1.4 用户角色关联
CREATE TABLE sys_user_role
(
    id         BIGINT   NOT NULL COMMENT '用户角色关联ID',
    user_id    BIGINT   NOT NULL COMMENT '用户ID',
    role_id    BIGINT   NOT NULL COMMENT '角色ID',
    is_deleted TINYINT  NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_role (user_id, role_id),
    KEY        idx_role_id (role_id),
    KEY        idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联';

-- 1.5 角色权限关联
CREATE TABLE sys_role_permission
(
    id            BIGINT   NOT NULL COMMENT '角色权限关联ID',
    role_id       BIGINT   NOT NULL COMMENT '角色ID',
    permission_id BIGINT   NOT NULL COMMENT '权限ID',
    is_deleted    TINYINT  NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_role_permission (role_id, permission_id),
    KEY           idx_permission_id (permission_id),
    KEY           idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联';

-- 1.6 环境配置
CREATE TABLE sys_environment
(
    id          BIGINT       NOT NULL COMMENT '环境ID',
    env_code    VARCHAR(64)  NOT NULL COMMENT '环境编码: test/staging/production',
    env_name    VARCHAR(64)  NOT NULL COMMENT '环境名称',
    base_url    VARCHAR(256) NOT NULL COMMENT '基础URL',
    description VARCHAR(256)          DEFAULT NULL COMMENT '描述',
    status      TINYINT      NOT NULL DEFAULT 1 COMMENT '状态: 1-启用 0-禁用',
    is_deleted  TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_env_code (env_code),
    KEY         idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='环境配置';

-- 1.7 Git仓库配置
CREATE TABLE sys_repository
(
    id              BIGINT       NOT NULL COMMENT '仓库ID',
    repo_name       VARCHAR(128) NOT NULL COMMENT '仓库名称',
    repo_url        VARCHAR(512) NOT NULL COMMENT '仓库地址',
    branch_default  VARCHAR(128) NOT NULL DEFAULT 'main' COMMENT '默认分支',
    credential_type VARCHAR(32)  NOT NULL DEFAULT 'ssh' COMMENT '认证方式: ssh/token/password',
    credential_key  VARCHAR(512) NOT NULL COMMENT '认证密钥(加密存储)',
    webhook_url     VARCHAR(512)          DEFAULT NULL COMMENT 'Webhook回调地址',
    webhook_secret  VARCHAR(128)          DEFAULT NULL COMMENT 'Webhook签名密钥',
    status          TINYINT      NOT NULL DEFAULT 1 COMMENT '状态: 1-启用 0-禁用',
    is_deleted      TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY             idx_repo_url (repo_url(255)),
    KEY             idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Git仓库配置';

-- 1.8 字典类型
CREATE TABLE sys_dict_type
(
    id          BIGINT       NOT NULL COMMENT '字典类型ID',
    dict_type   VARCHAR(64)  NOT NULL COMMENT '字典类型编码(唯一标识)',
    dict_name   VARCHAR(128) NOT NULL COMMENT '字典类型名称',
    description VARCHAR(256)          DEFAULT NULL COMMENT '描述',
    status      TINYINT      NOT NULL DEFAULT 1 COMMENT '状态: 1-启用 0-禁用',
    is_deleted  TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_by  BIGINT                DEFAULT NULL COMMENT '创建人ID',
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_dict_type (dict_type),
    KEY         idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典类型';

-- 1.9 字典数据
CREATE TABLE sys_dict_data
(
    id          BIGINT       NOT NULL COMMENT '字典数据ID',
    dict_type   VARCHAR(64)  NOT NULL COMMENT '所属字典类型编码',
    dict_label  VARCHAR(128) NOT NULL COMMENT '字典标签(显示值)',
    dict_value  VARCHAR(128) NOT NULL COMMENT '字典值(实际值)',
    sort_order  INT          NOT NULL DEFAULT 0 COMMENT '排序(升序)',
    css_class   VARCHAR(64)           DEFAULT NULL COMMENT '样式属性(如标签颜色)',
    description VARCHAR(256)          DEFAULT NULL COMMENT '描述',
    status      TINYINT      NOT NULL DEFAULT 1 COMMENT '状态: 1-启用 0-禁用',
    is_deleted  TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    created_by  BIGINT                DEFAULT NULL COMMENT '创建人ID',
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_type_value (dict_type, dict_value),
    KEY         idx_dict_type (dict_type),
    KEY         idx_is_deleted (is_deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典数据';
