package com.alinesno.infra.data.security.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 统计数据实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("statistical_data")
@Data
public class StatisticalEntity extends InfraBaseEntity {

    /**
     * 账户ID
     */
	@ColumnType(length=255)
	@ColumnComment("账户ID")
	@TableField("account_id")
    private String accountId;

    /**
     * 统计时间键
     */
	@ColumnType(length=255)
	@ColumnComment("统计时间键")
	@TableField("count_time_key")
    private String countTimeKey;

    /**
     * 总计数
     */
	@ColumnType(length=255)
	@ColumnComment("总计数")
	@TableField("total_count")
    private Long totalCount;

    /**
     * 成功计数
     */
	@ColumnType(length=255)
	@ColumnComment("成功计数")
	@TableField("success_count")
    private Long successCount;

    /**
     * 失败计数
     */
	@ColumnType(length=255)
	@ColumnComment("失败计数")
	@TableField("fail_count")
    private Long failCount;
}
