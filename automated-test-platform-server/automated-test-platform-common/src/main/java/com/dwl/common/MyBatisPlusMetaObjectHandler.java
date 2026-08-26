package com.dwl.common;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Mybatis-Plus 自动填充处理器
 * <p>
 * Mybatis-Plus Auto-Fill Meta Object Handler
 * <p>
 * 自动填充数据库实体字段
 * <p>
 * Automatically fills audit fields of database entities
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-05 18:50
 */
@Slf4j
@Component
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时自动填充
     * <p>
     * Auto-fill on insert
     * <p>
     * When performing an insert operation, automatically sets the createdAt and updateAt fields to the current time
     *
     * @param metaObject Meta Object
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("""
                MyBatis-Plus 自动填充(插入)
                MyBatis-Plus auto-fill insert
                """);
        // fill creation time
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, LocalDateTime.now());
        // fill update time
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * Auto-fill on update
     * <p>
     * when performing an update operation, automatically sets the updateAt field to the current time.
     *
     * @param metaObject Meta Object
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("""
                MyBatis-Plus 自动填充(更新)
                MyBatis-Plus auto-fill update
                """);
        this.strictUpdateFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
    }
}
