package com.dwl.model.entity.code;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Git 提交记录实体
 * Git Commit Record Entity
 * 对应表 git_commit,记录 Git 仓库的提交变更信息
 * Maps to table git_commit, recording Git repository commit change information
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 18:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("git_commit")
@Schema(description = """
        Git 提交记录
        Git Commit Record
        """)
public class GitCommit extends BaseEntity {

    @Schema(description = """
            所属仓库 ID
            Repository ID
            """, example = "1")
    private Long repoId;

    @Schema(description = """
            Commit Hash(SHA-1)
            """, example = "a1b2c3d4e5f6")
    private String commitHash;

    @Schema(description = """
            分支名
            Branch name
            """, example = "main")
    private String branch;

    @Schema(description = """
            提交者姓名
            Committer name
            """, example = "张三")
    private String authorName;

    @Schema(description = """
            提交者邮箱
            Committer email
            """, example = "zhangsan@example.com")
    private String authorEmail;

    @Schema(description = """
            提交信息
            Commit message
            """, example = "feat: 新增用户管理模块")
    private String commitMessage;

    @Schema(description = """
            提交时间
            Commit timestamp
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
            Trigger source: auto-Webhook automatic, manual-manual trigger
            """, example = "auto")
    private String triggerSource;

}
