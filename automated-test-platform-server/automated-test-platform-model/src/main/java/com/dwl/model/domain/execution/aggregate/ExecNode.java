package com.dwl.model.domain.execution.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.execution.ExecNodeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 执行节点聚合根
 * <p>
 * Execution Node Aggregate Root
 * <p>
 * 执行管控域的聚合根, 管理 Playwright 执行节点的注册和心跳状态
 * <p>
 * Aggregate root of the Execution Control domain, managing Playwright execution
 * node registration and heartbeat status.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("exec_node")
@Schema(description = """
        执行节点聚合根
        Execution Node Aggregate Root
        """)
public class ExecNode extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            Node ID
            """)
    private Long id;

    @Schema(description = """
            Node name
            """, example = "node-01")
    private String nodeName;

    @Schema(description = """
            Node address
            """, example = "192.168.1.100")
    private String nodeHost;

    @Schema(description = """
            支持浏览器类型
            Supported browser types (comma-separated)
            """, example = "chromium, firefox")
    private String browserTypes;

    @Schema(description = """
            最大并发数
            Maximum concurrency
            """, example = "2")
    private Integer maxConcurrent;

    @Schema(description = """
            Status
            """, example = "healthy", implementation = ExecNodeStatus.class)
    private String status;

    @Schema(description = """
            最后心跳时间
            Last heartbeat time
            """)
    private LocalDateTime localHeartbeat;

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
     * 工厂方法：注册执行节点
     * <p>
     * Factory Method: Register execution node
     *
     * @param nodeName      Node name
     * @param nodeHost      Node address
     * @param browserTypes  支持浏览器类型
     *                      Supported browser types
     * @param maxConcurrent 最大并发数
     *                      Maximum concurrency
     * @return 新执行节点
     * New execution node
     */
    public static ExecNode register(String nodeName, String nodeHost, String browserTypes, Integer maxConcurrent) {
        return ExecNode.builder()
                .nodeName(nodeName).nodeHost(nodeHost).browserTypes(browserTypes)
                .maxConcurrent(maxConcurrent).status(ExecNodeStatus.HEALTHY.getCode())
                .localHeartbeat(LocalDateTime.now()).build();
    }

    /**
     * 发送心跳
     * Send heartbeat
     */
    public void heartbeat() {
        this.localHeartbeat = LocalDateTime.now();
        this.status = ExecNodeStatus.HEALTHY.getCode();
    }

    /**
     * 标记为忙碌
     * Mark as busy
     */
    public void markBusy() {
        this.status = ExecNodeStatus.BUSY.getCode();
    }

    /**
     * 标记为离线
     * Mark as offline
     */
    public void markOffline() {
        this.status = ExecNodeStatus.OFFLINE.getCode();
    }

}
