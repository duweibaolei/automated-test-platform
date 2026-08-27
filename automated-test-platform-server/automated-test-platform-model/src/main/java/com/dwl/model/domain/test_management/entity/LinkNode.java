package com.dwl.model.domain.test_management.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.code_analysis.CodeAnalysisScopeType;
import com.dwl.common.enums.ScopePathType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 链路节点实体
 * <p>
 * Link Node Entity
 * <p>
 * BusinessLink 聚合根内的实体, 定义业务链路中的各个节点
 * <p>
 * Entity within BusinessLink aggregate root, defining individual nodes within a business link
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("link_node")
@Schema(description = """
        链路节点实体
        Link Node Entity
        """)
public class LinkNode extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            Node ID
            """)
    private Long id;

    @Schema(description = """
            所属链路 ID
            Link ID
            """, example = "1")
    private Long linkId;

    @Schema(description = """
            Node order
            """, example = "1")
    private Integer nodeOrder;

    @Schema(description = """
            Node type
            """, example = "frontend_page",
            implementation = CodeAnalysisScopeType.class)
    private String nodeType;

    @Schema(description = """
            Node name
            """, example = "登录页面")
    private String nodeName;

    @Schema(description = """
            节点标识
            Node identifier (e.g., API path, table name)
            """, example = "/api/auth/login",
            implementation = ScopePathType.class)
    private String nodeIdentifier;

    @Schema(description = """
            断言规则
            Assert rule
            """)
    private String assertRule;


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

    /**
     * 工厂方法: 创建链路节点
     * <p>
     * Factory Method: Create link node
     *
     * @param linkId         所属链路 ID
     *                       Link ID
     * @param nodeOrder      Node order
     * @param nodeType       Node type
     * @param nodeName       Node name
     * @param nodeIdentifier Node identifier
     * @param assertRule     断言规则
     *                       Assert rule
     * @return 新节点实体
     * New node entity
     */
    public static LinkNode create(Long linkId, Integer nodeOrder, String nodeType,
                                  String nodeName, String nodeIdentifier, String assertRule) {
        return LinkNode.builder()
                .linkId(linkId).nodeOrder(nodeOrder).nodeType(nodeType)
                .nodeName(nodeName).nodeIdentifier(nodeIdentifier).assertRule(assertRule)
                .build();
    }

}
