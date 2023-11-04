package com.alinesno.infra.data.security.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

/**
 * 客户端注册网关路由服务实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("reg_server")
@Data
public class RegServerEntity extends InfraBaseEntity {

    /**
     * 客户端ID
     */
    @TableField("client_id")
	@ColumnType(length=255)
	@ColumnComment("客户端ID")
    private String clientId;

    /**
     * 路由ID
     */
    @TableField("route_id")
	@ColumnType(length=255)
	@ColumnComment("路由ID")
    private String routeId;

    /**
     * token加密内容
     */
    @TableField("token")
	@ColumnType(length=255)
	@ColumnComment("token加密内容")
    private String token;

    /**
     * token加密密钥
     */
    @TableField("secret_key")
	@ColumnType(length=255)
	@ColumnComment("token加密密钥")
    private String secretKey;

    /**
     * token有效期截止时间
     */
    @TableField("token_effective_time")
	@ColumnType(length=255)
	@ColumnComment("token有效期截止时间")
    private Date tokenEffectiveTime;
}
