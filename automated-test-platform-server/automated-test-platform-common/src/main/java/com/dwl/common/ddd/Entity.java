package com.dwl.common.ddd;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * DDD 实体基类
 * <p>
 * DDD Entity Base Class
 * <p>
 * 所有有唯一标识的领域对象的基类, 实体通过 ID 区分, 即时其他属性相同, ID 不同就是不同的实体
 * <p>
 * Base class for all domain objects with a unique identity
 * <p>
 * Entities are distinguished by ID, even if other attributes are the same, different IDs
 * mean different entities
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 16:14
 */
@ToString
@EqualsAndHashCode(callSuper = false)
@Schema(description = """
        DDD Entity Base Class
        """)
public abstract class Entity<ID extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 获取实体唯一标识
     * <p>
     * Get entity unique identity
     *
     * @return Entity ID
     */
    public abstract ID getId();

    /**
     * 设置实体唯一标识
     * <p>
     * Set entity unique identity
     *
     * @param id Entity ID
     */
    public abstract void setId(ID id);

}
