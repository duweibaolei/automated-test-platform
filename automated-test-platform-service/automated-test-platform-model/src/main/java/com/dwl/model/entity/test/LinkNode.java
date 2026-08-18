package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 链路节点实体
 * Link Node Entity
 * 对应表 link_node,定义业务链路中的各个节点
 * Maps to table link_node, defining individual nodes within a business link.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 12:08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("link_node")
@Schema(description = """
        链路节点
        Link Node
        """)
public class LinkNode extends BaseEntity {

    @Schema(description = """
            所属链路 ID
            Link ID
            """, example = "1")
    private Long linkId;

    @Schema(description = """
            节点顺序
            Node order
            """, example = "1")
    private Integer nodeOrder;

    @Schema(description = """
            节点类型
            Node type: frontend_page/backend_api/backend_service/database_table
            """, example = "frontend_page")
    private String nodeType;

    @Schema(description = """
            节点名称
            Node name
            """, example = "登录页面")
    private String nodeName;

    @Schema(description = """
            节点标识
            Node identifier (e.g., API path, table name)
            """, example = "/api/auth/login")
    private String nodeIdentifier;

    @Schema(description = """
            断言规则
            Assert rule
            """)
    private String assertRule;

}
