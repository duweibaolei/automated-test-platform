package com.dwl.common.ddd;


import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * DDD 值对象标记接口
 * DDD Value Object Marker Interface
 * <p>
 * 值对象没有唯一标识,通过属性值区分。值对象是不可变的(Immutable),
 * 创建后不能修改,需要修改时创建新的值对象替换。
 * 典型值对象: 地址(Address)、金额(Money)、坐标(Coordinate)。
 * <p>
 * Value Objects have no unique identity and are distinguished by attribute values.
 * Value Objects are immutable; once created, they cannot be modified. When modification
 * is needed, create a new Value Object to replace. Typical VOs: Address, Money, Coordinate.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 18:54
 */
@Schema(description = """
        DDD 值对象标记接口
        DDD Value Object Marker Interface
        """)
public interface ValueObject extends Serializable {

    /**
     * 值对象通过所有属性值判断相等
     * Value Objects are equal if all attribute values are equal
     *
     * @param o Other object
     * @return true if all attributes are equal
     */
    @Override
    boolean equals(Object o);

    /**
     * 值对象的哈希码基于所有属性
     * Value Object hash code is based on all attributes
     *
     * @return Hash code
     */
    @Override
    int hashCode();

}
