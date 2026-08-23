package com.dwl.model.entity.execute;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 执行节点实体
 * Execution Node Entity
 * 对应表 exec_node,管理 Playwright 执行节点的注册和心跳状态
 * aps to table exec_node, managing Playwright execution node registration and heartbeat status
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 13:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("exec_node")
@Schema(description = "执行节点 / Execution Node")
public class ExecNode extends BaseEntity {

    @Schema(description = """
            节点名称
            Node name
            """, example = "node-01")
    private String nodeName;

    @Schema(description = """
            节点地址
            Node host address
            """, example = "192.168.1.100")
    private String nodeHost;

    @Schema(description = """
            支持浏览器类型(逗号分割)
            Supported browser types(comma-separated)
            """, example = "chromium,firefox")
    private String browserTypes;

    @Schema(description = """
            最大并发数
            maximum concurrent executions
            """, example = "2")
    private Integer maxConcurrent;

    @Schema(description = """
            Status: healthy offline busy
            """, example = "healthy")
    private String status;

    @Schema(description = """
            最后心跳时间
            Last heartbeat timestamp
            """)
    private LocalDateTime localHeartbeat;
}
