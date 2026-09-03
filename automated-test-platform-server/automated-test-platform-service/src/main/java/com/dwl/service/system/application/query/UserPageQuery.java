package com.dwl.service.system.application.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户分页查询
 * User Page Query
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25
 */
@Data
@Builder
@Schema(description = """
        用户分页查询
        User Page Query
        """)
public class UserPageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            用户名 (模糊查询)
            Username (fuzzy query)
            """, example = "adm")
    private String username;

    @Schema(description = """
            状态筛选：1-启用 0-禁用 null-全部
            Status filter: 1-enabled, 0-disabled, null-all
            """, example = "1")
    private Integer status;

    @Schema(description = """
            页码 (从 1 开始)
            Page number (starts from 1)
            """, example = "1")
    private int pageNum;

    @Schema(description = """
            每页条数
            Page size
            """, example = "10")
    private int pageSize;
}
