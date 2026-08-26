package com.dwl.common.exception.code_analysis;

import com.dwl.common.enums.ErrorCode;
import com.dwl.common.exception.BusinessException;

/**
 * 代码分析域业务异常
 * Code Analysis Domain Business Exception
 * <p>
 * 代码分析域特有的业务异常类,继承自 BusinessException
 * 用于在变更分析、Git 提交处理、影响范围分析等业务逻辑中抛出具体错误
 * <p>
 * Business exception class specific to the Code Analysis domain, extending BusinessException.
 * Used to throw specific errors in business logic such as change analysis, Git commit processing,
 * and affected scope analysis.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 22:00
 */
public class CodeAnalysisException extends BusinessException {

    /**
     * 使用 ErrorCode 构造异常
     * Construct exception with ErrorCode
     *
     * @param errorCode Error code enum
     */
    public CodeAnalysisException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * 使用 ErrorCode 和自定义消息构造异常
     * Construct exception with ErrorCode and custom message
     *
     * @param errorCode Error code enum
     * @param message   Custom message
     */
    public CodeAnalysisException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * 使用 ErrorCode 和原因异常构造异常
     * Construct exception with ErrorCode and cause
     *
     * @param errorCode Error code enum
     * @param cause     Cause exception
     *                  原因异常
     */
    public CodeAnalysisException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * 使用 ErrorCode、自定义消息和原因异常构造异常
     * Construct exception with ErrorCode, custom message, and cause
     *
     * @param errorCode Error code enum
     * @param message   Custom message
     * @param cause     Cause exception
     *                  原因异常
     */
    public CodeAnalysisException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    /* ==================== 工厂方法 - 常用异常场景 ====================
     * ==================== Factory Methods - Common Exception Scenarios ====================
     */

    /**
     * 变更分析不存在异常
     * Change analysis not found exception
     *
     * @param analysisNo 分析编号
     *                   Analysis number
     * @return CodeAnalysisException
     */
    public static CodeAnalysisException analysisNotFound(String analysisNo) {
        return new CodeAnalysisException(ErrorCode.CHANGE_ANALYSIS_NOT_FOUND,
                "变更分析不存在：" + analysisNo);
    }

    /**
     * 变更分析状态异常
     * Change analysis status error exception
     *
     * @param currentStatus  当前状态
     *                       Current status
     * @param requiredStatus 所需状态
     *                       Required status
     * @return CodeAnalysisException
     */
    public static CodeAnalysisException analysisStatusError(String currentStatus, String requiredStatus) {
        return new CodeAnalysisException(ErrorCode.CHANGE_ANALYSIS_STATUS_ERROR,
                "变更分析状态异常：当前=" + currentStatus + ", 需要=" + requiredStatus);
    }

    /**
     * Git 仓库配置不存在异常
     * Git repository not found exception
     *
     * @param repoId 仓库 ID
     *               Repository ID
     * @return CodeAnalysisException
     */
    public static CodeAnalysisException repositoryNotFound(Long repoId) {
        return new CodeAnalysisException(ErrorCode.GIT_REPOSITORY_NOT_FOUND,
                "Git 仓库配置不存在：" + repoId);
    }

    /**
     * Git 连接失败异常
     * Git connection failed exception
     *
     * @param repoUrl 仓库地址
     *                Repository URL
     * @param reason  失败原因
     *                Failure reason
     * @return CodeAnalysisException
     */
    public static CodeAnalysisException gitConnectionFailed(String repoUrl, String reason) {
        return new CodeAnalysisException(ErrorCode.GIT_CONNECTION_FAILED,
                "Git 仓库连接失败 [" + repoUrl + "]: " + reason);
    }

    /**
     * Git 提交记录不存在异常
     * Git commit not found exception
     *
     * @param commitHash Commit Hash
     * @return CodeAnalysisException
     */
    public static CodeAnalysisException commitNotFound(String commitHash) {
        return new CodeAnalysisException(ErrorCode.GIT_COMMIT_NOT_FOUND,
                "Git 提交记录不存在：" + commitHash);
    }

    /**
     * 影响范围分析失败异常
     * Affected scope analysis failed exception
     *
     * @param reason 失败原因
     *               Failure reason
     * @return CodeAnalysisException
     */
    public static CodeAnalysisException affectedScopeAnalysisFailed(String reason) {
        return new CodeAnalysisException(ErrorCode.AFFECTED_SCOPE_ANALYSIS_FAILED,
                "影响范围分析失败：" + reason);
    }

    /**
     * 变更分析与提交关联不存在异常
     * Change analysis commit relation not found exception
     *
     * @param analysisId 分析 ID
     *                   Analysis ID
     * @param commitId   Commit ID
     * @return CodeAnalysisException
     */
    public static CodeAnalysisException analysisCommitRelationNotFound(Long analysisId, Long commitId) {
        return new CodeAnalysisException(ErrorCode.ANALYSIS_COMMIT_RELATION_NOT_FOUND,
                "变更分析与提交关联不存在：analysisId=" + analysisId + ", commitId=" + commitId);
    }

}
