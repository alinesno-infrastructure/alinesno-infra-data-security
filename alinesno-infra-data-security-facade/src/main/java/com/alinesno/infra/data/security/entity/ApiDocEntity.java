package com.alinesno.infra.data.security.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * API文档实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("api_doc")
@Data
public class ApiDocEntity extends InfraBaseEntity {

    /**
     * 内容
     */
    @TableField("content")
	@ColumnType(length=255)
	@ColumnComment("内容")
    private String content;
}
