package com.dwl.model.domain.test_management.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.dwl.common.constant.CommonConstant;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.EnableStatus;
import com.dwl.common.enums.SourceType;
import com.dwl.model.domain.test_management.entity.LinkNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 业务链路聚合根
 * <p>
 * Business Link Aggregate Root
 * <p>
 * 测试管理域的聚合根, 表示一条端到端的业务测试链路, 包含多个链路节点
 * <p>
 * Aggregate root of the Test Management domain, representing an end-to-end
 * business test link containing multiple link nodes.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("business_link")
@Schema(description = """
        业务链路聚合根
        Business Link Aggregate Root
        """)
public class BusinessLink extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            链路 ID
            Link ID
            """)
    private Long id;

    @Schema(description = """
            链路编号
            Link number
            """, example = "BL-0001")
    private String linkNo;

    @Schema(description = """
            链路名称
            Link name
            """, example = "用户注册到首次购买全流程")
    private String linkName;

    @Schema(description = """
            链路描述
            Link description
            """)
    private String description;

    @Schema(description = """
            Source
            """, example = "manual",
            implementation = SourceType.class)
    private String source;

    @Schema(description = """
            Status
            """, example = "1",
            implementation = EnableStatus.class)
    private Integer status;

    @Version
    @Schema(description = """
            版本号
            Version number (optimistic lock)
            """, example = "1")
    private Integer version = CommonConstant.DEFAULT_VERSION;

    @Schema(description = """
            创建人 ID
            Creator user ID
            """)
    private Long createdBy;

    @Schema(description = """
            最后修改人 ID
            Last modifier user ID
            """)
    private Long lastModifiedBy;

    @Schema(description = """
            Logical delete flag
            """, example = "0",
            implementation = DeletedStatus.class)
    private Integer isDelete;

    @Schema(description = """
            创建时间
            Create time
            """)
    private LocalDateTime createdAt;

    @Schema(description = """
            更新时间
            Update time
            """)
    private LocalDateTime updatedAt;

    @Builder.Default
    private transient List<LinkNode> nodes = new ArrayList<>();

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建业务链路
     * Factory Method: Create business link
     *
     * @param linkNo      链路编号
     *                    Link number
     * @param linkName    链路名称
     *                    Link name
     * @param description 链路描述
     *                    description
     * @param source      来源  Source
     * @param createdBy   Creator user ID
     * @return 新业务链路
     * New business link
     */
    public static BusinessLink create(String linkNo, String linkName, String description,
                                      String source, Long createdBy) {
        return BusinessLink.builder()
                .linkNo(linkNo).linkName(linkName).description(description)
                .source(source).createdBy(createdBy).lastModifiedBy(createdBy)
                .status(EnableStatus.ENABLED.getValue()).nodes(new ArrayList<>())
                .build();
    }

    /**
     * 添加节点
     * Add node
     *
     * @param nodeOrder      节点顺序  Node order
     * @param nodeType       节点类型  Node type
     * @param nodeName       节点名称  Node name
     * @param nodeIdentifier 节点标识  Node identifier
     * @param assertRule     断言规则  Assert rule
     */
    public void addNode(Integer nodeOrder, String nodeType, String nodeName,
                        String nodeIdentifier, String assertRule) {
        this.nodes.add(LinkNode.create(this.id, nodeOrder, nodeType, nodeName, nodeIdentifier, assertRule));
    }

    /**
     * 启用链路
     * Enable link
     */
    public void enable() {
        this.status = EnableStatus.ENABLED.getValue();
    }

    /**
     * 禁用链路
     * Disable link
     */
    public void disable() {
        this.status = EnableStatus.DISABLED.getValue();
    }

    /**
     * 获取节点列表
     * Get node list
     *
     * @return 不可变节点列表  Unmodifiable node list
     */
    public List<LinkNode> getNodes() {
        return Collections.unmodifiableList(this.nodes);
    }

}
