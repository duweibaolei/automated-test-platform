package com.dwl.common.result;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 分页结果包装类
 * <p>
 * Paginated Result Wrapper
 * <p>
 * 封装分页查询的结过数据, 包含数据列表和分页元数据
 * <p>
 * Wraps paginated query result data, including the data list and pagination metadata
 *
 * @param <T> List element generic type
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-05 17:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = """
        分页结果
        Paginated Result
        """)
public class PageResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            Data list
            """)
    private List<T> data;

    @Schema(description = """
            Total record count
            """, example = "100")
    private long total;

    @Schema(description = """
            Current page number (starts from 1)
            """, example = "1")
    private long pageNum;

    @Schema(description = """
            Page size
            """, example = "10")
    private long pageSize;

    @Schema(description = """
            Total page count
            """, example = "10")
    private long pages;

    /**
     * 根据分页参数构造分页结果
     * <p>
     * Construct paginated result from pagination parameters
     *
     * @param data     Data list
     * @param total    Total record count
     * @param pageNum  Current page number
     * @param pageSize Page size
     */
    public PageResult(List<T> data, long total, long pageNum, long pageSize) {
        this.data = data;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = pageSize > 0 ? (total + pageSize - 1) / pageSize : 0;
    }
}
