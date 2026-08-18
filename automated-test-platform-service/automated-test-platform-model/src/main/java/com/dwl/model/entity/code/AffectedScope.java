package com.dwl.model.entity.code;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 影响范围实体
 * Affected Scope Entity
 * 对应表 affected_scope,记录变更分析识别出的影响范围
 * Maps to table affected_scope, recording affected scopes identified by change analysis
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 18:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("affected_scope")
@Schema(description = """
        影响范围
        Affected Scope
        """)
public class AffectedScope extends BaseEntity {

    @Schema(description = """
            所属分析 ID
            Analysis ID
            """, example = "1")
    private Long analysisId;

    @Schema(description = """
            范围类型
            Scope type: frontend_page/frontend_component/backend_api/backend_service/database_table
            """, example = "backend_api")
    private String scopeType;

    @Schema(description = """
            范围名称
            Scope name
            """, example = "用户登录接口")
    private String scopeName;

    @Schema(description = """
            范围路径/标识
            Scope path or identifier
            """, example = "/api/auth/login")
    private String scopePath;

    @Schema(description = """
            是否选入回归范围
            Selected for regression: 1-yes, 0-no
            """, example = "1")
    private Integer selectedForRegression;

}
