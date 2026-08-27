package com.dwl.model.domain.code_analysis.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.SourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * Git 提交聚合根
 * <p>
 * Git Commit Aggregate Root
 * <p>
 * 代码分析域的聚合根, 表示一次 Git 提交记录
 * <p>
 * 包含: 仓库 ID、提交哈希、分支、作者、提交信息、变更统计
 * <p>
 * Aggregate root of the Code Analysis domain, representing a Git commit record
 * <p>
 * Contains: repository ID, commit hash, branch, author, commit message, change statistics
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("git_commit")
@Schema(description = """
        Git 提交聚合根
        Git Commit Aggregate Root
        """)
public class GitCommit extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            提交 ID
            Commit ID
            """)
    private Long id;

    @Schema(description = """
            所属仓库 ID
            Repository ID
            """, example = "1")
    private Long repoId;

    @Schema(description = """
            Commit hash (SHA-1)
            """, example = "a1b2c3d4e5f6")
    private String commitHash;

    @Schema(description = """
            分支名
            Branch name
            """, example = "main")
    private String branch;

    @Schema(description = """
            提交者姓名
            Author name
            """, example = "张三")
    private String authorName;

    @Schema(description = """
            提交者邮箱
            Author email
            """, example = "zhangsan@example.com")
    private String authorEmail;

    @Schema(description = """
            提交信息
            Commit message
            """, example = "feat: 新增用户管理模块")
    private String commitMessage;

    @Schema(description = """
            提交时间
            Commit time
            """)
    private LocalDateTime commitTime;

    @Schema(description = """
            新增行数
            Lines added
            """, example = "50")
    private Integer additions;

    @Schema(description = """
            删除行数
            Lines deleted
            """, example = "10")
    private Integer deletions;

    @Schema(description = """
            变更文件数
            Changed files count
            """, example = "5")
    private Integer changedFiles;

    @Schema(description = """
            触发来源
            Trigger source
            """, example = "auto",
            implementation = SourceType.class)
    private String triggerSource;

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
     * 工厂方法: 创建 Git 提交记录
     * <p>
     * Factory Method: Create Git commit record
     *
     * @param repoId        所属仓库 ID
     *                      Repository ID
     * @param commitHash    Commit Hash
     * @param branch        Branch name
     * @param authorName    提交者姓名
     *                      Author name
     * @param authorEmail   提交者邮箱
     *                      Author email
     * @param commitMessage Commit message
     * @param commitTime    Commit time
     * @param additions     新增行数
     *                      Lines added
     * @param deletions     删除行数
     *                      Lines deleted
     * @param changedFiles  变更文件数
     *                      Changed files count
     * @param triggerSource 触发来源
     *                      Trigger source
     * @return 新 Git 提交记录
     * New Git commit record
     */
    public static GitCommit create(Long repoId, String commitHash, String branch,
                                   String authorName, String authorEmail, String commitMessage,
                                   LocalDateTime commitTime, Integer additions, Integer deletions,
                                   Integer changedFiles, String triggerSource) {
        return GitCommit.builder()
                .repoId(repoId)
                .commitHash(commitHash)
                .branch(branch)
                .authorName(authorName)
                .authorEmail(authorEmail)
                .commitMessage(commitMessage)
                .commitTime(commitTime)
                .additions(additions)
                .deletions(deletions)
                .changedFiles(changedFiles)
                .triggerSource(triggerSource)
                .build();
    }

}
