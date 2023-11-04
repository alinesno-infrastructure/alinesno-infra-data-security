package com.alinesno.infra.data.security.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 负载均衡实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("balanced")
@Data
public class BalancedEntity extends InfraBaseEntity {

    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("name")
    private String name;

    @TableField("group_code")
	@ColumnType(length=255)
	@ColumnComment("groupCode")
    private String groupCode;

    @TableField("load_uri")
	@ColumnType(length=255)
	@ColumnComment("loadUri")
    private String loadUri;

    /**
     * 备注
     */
    @TableField("remarks")
	@ColumnType(length=255)
	@ColumnComment("备注")
    private String remarks;
}
