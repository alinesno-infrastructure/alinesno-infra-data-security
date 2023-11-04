package com.alinesno.infra.data.security.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 客户端实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("client")
@Data
public class ClientEntity extends InfraBaseEntity {

    @TableField("system_code")
	@ColumnType(length=255)
	@ColumnComment("system_code")
    private String systemCode;

    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("name")
    private String name;

    @TableField("group_code")
	@ColumnType(length=255)
	@ColumnComment("groupCode")
    private String groupCode;

    @TableField("ip")
	@ColumnType(length=255)
	@ColumnComment("ip")
    private String ip;

    /**
     * 备注
     */
    @TableField("remarks")
	@ColumnType(length=255)
	@ColumnComment("备注")
    private String remarks;
}
