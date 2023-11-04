package com.alinesno.infra.data.security.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 账户实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("account")
@Data
public class AccountEntity extends InfraBaseEntity {

    /**
     * 账户ID
     */
    @TableField("account_id")
	@ColumnType(length=255)
	@ColumnComment("账户ID")
    private Long accountId;

    /**
     * 授权令牌
     */
    @TableField("auth_token")
	@ColumnType(length=255)
	@ColumnComment("授权令牌")
    private String authToken;
}
