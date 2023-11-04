package com.alinesno.infra.data.security.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * Groovy脚本实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("groovy_script")
@Data
public class GroovyScriptEntity extends InfraBaseEntity {

    /**
     * 网关ID
     */
    @TableField("route_id")
	@ColumnType(length=255)
	@ColumnComment("网关ID")
    private Long routeId;

    /**
     * 脚本名称
     */
    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("脚本名称")
    private String name;

    /**
     * 脚本内容
     */
    @TableField("content")
	@ColumnType(length=255)
	@ColumnComment("脚本内容")
    private String content;

    /**
     * 扩展内容，参数json
     */
    @TableField("extend_info")
	@ColumnType(length=255)
	@ColumnComment("扩展内容，参数json")
    private String extendInfo;

    /**
     * 执行事件，request|response
     */
    @TableField("event")
	@ColumnType(length=255)
	@ColumnComment("执行事件，request|response")
    private String event;

    /**
     * 顺序
     */
    @TableField("order_num")
	@ColumnType(length=255)
	@ColumnComment("顺序")
    private Integer orderNum;

    /**
     * 备注
     */
    @TableField("remarks")
	@ColumnType(length=255)
	@ColumnComment("备注")
    private String remarks;
}
