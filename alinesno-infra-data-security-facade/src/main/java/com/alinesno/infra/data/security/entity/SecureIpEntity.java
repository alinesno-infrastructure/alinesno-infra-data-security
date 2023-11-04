package com.alinesno.infra.data.security.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 安全IP实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("secure_ip")
@Data
public class SecureIpEntity extends InfraBaseEntity {

    /**
     * IP地址
     */
    @TableField("ip")
	@ColumnType(length=255)
	@ColumnComment("IP地址")
    private String ip;

    /**
     * 用户标识
     */
    @TableField("account_token")
	@ColumnType(length=255)
	@ColumnComment("用户标识")
    private String accountToken;

    /**
     * 状态
     */
    @TableField("status")
	@ColumnType(length=255)
	@ColumnComment("状态")
    private String status;

    /**
     * 备注
     */
    @TableField("remarks")
	@ColumnType(length=255)
	@ColumnComment("备注")
    private String remarks;
}
