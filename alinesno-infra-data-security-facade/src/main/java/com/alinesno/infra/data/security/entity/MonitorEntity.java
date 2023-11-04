package com.alinesno.infra.data.security.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

/**
 * 告警监控实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("monitor")
@Data
public class MonitorEntity extends InfraBaseEntity {

    /**
     * 通知接收邮箱
     */
    @TableField("emails")
	@ColumnType(length=255)
	@ColumnComment("通知接收邮箱")
    private String emails;

    /**
     * 告警邮件主题附带内容
     */
    @TableField("topic")
	@ColumnType(length=255)
	@ColumnComment("告警邮件主题附带内容")
    private String topic;

    /**
     * 告警频率：30m,1h,5h,12h,24h
     */
    @TableField("frequency")
	@ColumnType(length=255)
	@ColumnComment("告警频率：30m,1h,5h,12h,24h")
    private String frequency;

    /**
     * 告警重试，0开启，1禁用
     */
    @TableField("recover")
	@ColumnType(length=255)
	@ColumnComment("告警重试，0开启，1禁用")
    private String recover;

    /**
     * 告警时间
     */
    @TableField("alarm_time")
	@ColumnType(length=255)
	@ColumnComment("告警时间")
    private Date alarmTime;

    /**
     * 发送告警时间
     */
    @TableField("send_time")
	@ColumnType(length=255)
	@ColumnComment("发送告警时间")
    private Date sendTime;
}
