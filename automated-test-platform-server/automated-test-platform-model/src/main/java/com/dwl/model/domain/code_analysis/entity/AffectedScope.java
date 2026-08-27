package com.dwl.model.domain.code_analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.SelectedStatus;
import com.dwl.common.enums.code_analysis.CodeAnalysisScopeType;
import com.dwl.common.enums.ScopePathType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 影响范围实体
 * Affected Scope Entity
 * <p>
 * ChangeAnalysis 聚合根内的实体, 表示变更分析识别出的影响范围
 * 外部不能直接操作此实体, 必须通过 ChangeAnalysis 聚合根的方法
 * <p>
 * Entity within the ChangeAnalysis aggregate, representing the affected scope
 * identified by change analysis.
 * External objects cannot directly operate this entity, must go through ChangeAnalysis aggregate root methods
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("affected_scope")
@Schema(description = """
        影响范围实体
        Affected Scope Entity
        """)
public class AffectedScope extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            影响范围 ID
            Affected scope ID
            """)
    private Long id;

    @Schema(description = """
            所属分析 ID
            Analysis ID
            """, example = "1")
    private Long analysisId;

    @Schema(description = """
            范围类型
            Scope type
            """, example = "backend_api",
            implementation = CodeAnalysisScopeType.class)
    private String scopeType;

    @Schema(description = """
            范围名称
            Scope name
            """, example = "用户登录接口")
    private String scopeName;

    @Schema(description = """
            范围路径/标识
            Scope path or identifier
            """, example = "/api/auth/login",
            implementation = ScopePathType.class)
    private String scopePath;

    @Schema(description = """
            是否选入回归范围
            Selected for regression
            """, example = "1",
            implementation = SelectedStatus.class)
    private Integer selectedForRegression;

    @Schema(description = """
            Logical delete flag
            """, example = "0",
            implementation = DeletedStatus.class)
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
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法：创建影响范围
     * <p>
     * Factory Method: Create affected scope
     *
     * @param analysisId 所属分析 ID
     *                   Analysis ID
     * @param scopeType  Scope type
     * @param scopeName  Scope name
     * @param scopePath  Scope path
     * @return 新影响范围
     * New affected scope
     */
    public static AffectedScope create(Long analysisId, String scopeType, String scopeName, String scopePath) {
        return AffectedScope.builder()
                .analysisId(analysisId)
                .scopeType(scopeType)
                .scopeName(scopeName)
                .scopePath(scopePath)
                .selectedForRegression(SelectedStatus.SELECTED.getValue())
                .build();
    }

    /**
     * 创建前端页面影响范围
     * Create frontend page affected scope
     *
     * @param analysisId 所属分析 ID
     *                   Analysis ID
     * @param scopeName  Scope name
     * @param scopePath  Scope path
     * @return 前端页面影响范围
     * Frontend page affected scope
     */
    public static AffectedScope createFrontendPage(Long analysisId, String scopeName, String scopePath) {
        return create(analysisId, CodeAnalysisScopeType.FRONTEND_PAGE.getCode(), scopeName, scopePath);
    }

    /**
     * 创建后端 API 影响范围
     * Create backend API affected scope
     *
     * @param analysisId 所属分析 ID
     *                   Analysis ID
     * @param scopeName  Scope name
     * @param scopePath  Scope path
     * @return 后端 API 影响范围
     * Backend API affected scope
     */
    public static AffectedScope createBackendApi(Long analysisId, String scopeName, String scopePath) {
        return create(analysisId, CodeAnalysisScopeType.BACKEND_API.getCode(), scopeName, scopePath);
    }

    /**
     * 创建数据库表影响范围
     * Create database table affected scope
     *
     * @param analysisId 所属分析 ID
     *                   Analysis ID
     * @param scopeName  Scope name
     * @param scopePath  Scope path
     * @return 数据库表影响范围
     * Database table affected scope
     */
    public static AffectedScope createDatabaseTable(Long analysisId, String scopeName, String scopePath) {
        return create(analysisId, CodeAnalysisScopeType.DATABASE_TABLE.getCode(), scopeName, scopePath);
    }

    /**
     * 选入回归范围
     * Select for regression
     */
    public void selectForRegression() {
        this.selectedForRegression = SelectedStatus.SELECTED.getValue();
    }

    /**
     * 取消回归范围选择
     * Deselect from regression
     */
    public void deselectFromRegression() {
        this.selectedForRegression = SelectedStatus.NOT_SELECTED.getValue();
    }

    /**
     * 判断是否已选入回归范围
     * Check if selected for regression
     *
     * @return true if selected
     */
    public boolean isSelectedForRegression() {
        return SelectedStatus.isSelected(this.selectedForRegression);
    }

    /**
     * 判断是否为前端类型
     * Check if it is a frontend type
     *
     * @return true if frontend type
     */
    public boolean isFrontend() {
        return CodeAnalysisScopeType.isFrontend(this.scopeType);
    }

    /**
     * 判断是否为后端类型
     * Check if it is a backend type
     *
     * @return true if backend type
     */
    public boolean isBackend() {
        return CodeAnalysisScopeType.isBackend(this.scopeType);
    }

}
