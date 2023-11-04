package com.alinesno.infra.data.security.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 负载服务实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("load_server")
@Data
public class LoadServerEntity extends InfraBaseEntity {

    @TableField("route_id")
	@ColumnType(length=255)
	@ColumnComment("route_id")
    private Long routeId;

    @TableField("balanced_id")
	@ColumnType(length=255)
	@ColumnComment("balancedId")
    private Long balancedId;

    @TableField("weight")
	@ColumnType(length=255)
	@ColumnComment("weight")
    private Integer weight;

    /**
     * 备注
     */
    @TableField("remarks")
	@ColumnType(length=255)
	@ColumnComment("备注")
    private String remarks;
}
