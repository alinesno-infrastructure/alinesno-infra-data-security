package com.alinesno.infra.data.security.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 路由实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("route")
@Data
public class RouteEntity extends InfraBaseEntity {

    /**
     * 路由ID
     */
    @TableField("route_id")
	@ColumnType(length=255)
	@ColumnComment("路由ID")
    private String routeId;

    /**
     * 用户标识
     */
    @TableField("account_token")
	@ColumnType(length=255)
	@ColumnComment("用户标识")
    private String accountToken;

    /**
     * 名称
     */
    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("名称")
    private String name;

    /**
     * 系统编码
     */
    @TableField("system_code")
	@ColumnType(length=255)
	@ColumnComment("系统编码")
    private String systemCode;

    /**
     * 分组编码
     */
    @TableField("group_code")
	@ColumnType(length=255)
	@ColumnComment("分组编码")
    private String groupCode;

    /**
     * URI
     */
    @TableField("uri")
	@ColumnType(length=255)
	@ColumnComment("URI")
    private String uri;

    /**
     * 路径
     */
    @TableField("path")
	@ColumnType(length=255)
	@ColumnComment("路径")
    private String path;

    /**
     * 请求类型：POST，GET，DELETE，PUT
     */
    @TableField("method")
	@ColumnType(length=255)
	@ColumnComment("请求类型：POST，GET，DELETE，PUT")
    private String method;

    /**
     * 断言Hosts
     */
    @TableField("host")
	@ColumnType(length=255)
	@ColumnComment("断言Hosts")
    private String host;

    /**
     * 断言RemoteAddrs
     */
    @TableField("remote_addr")
	@ColumnType(length=255)
	@ColumnComment("断言RemoteAddrs")
    private String remoteAddr;

    /**
     * 断言Headers
     */
    @TableField("header")
	@ColumnType(length=255)
	@ColumnComment("断言Headers")
    private String header;

    /**
     * 鉴权过滤器类型：ip,token,id
     */
    @TableField("filter_gateway_name")
	@ColumnType(length=255)
	@ColumnComment("鉴权过滤器类型：ip,token,id")
    private String filterGatewaName;

    /**
     * 熔断器名称：hystrix,custom
     */
    @TableField("filter_hystrix_name")
	@ColumnType(length=255)
	@ColumnComment("熔断器名称：hystrix,custom")
    private String filterHystrixName;

    /**
     * 限流器类型：ip,uri,requestId
     */
    @TableField("filter_rate_limiter_name")
	@ColumnType(length=255)
	@ColumnComment("限流器类型：ip,uri,requestId")
    private String filterRateLimiterName;

    /**
     * 过滤器类型：header,ip,param,time,cookie
     */
    @TableField("filter_authorize_name")
	@ColumnType(length=255)
	@ColumnComment("过滤器类型：header,ip,param,time,cookie")
    private String filterAuthorizeName;

    /**
     * 回滚消息
     */
    @TableField("fallback_msg")
	@ColumnType(length=255)
	@ColumnComment("回滚消息")
    private String fallbackMsg;

    /**
     * 回滚超时时长
     */
    @TableField("fallback_timeout")
	@ColumnType(length=255)
	@ColumnComment("回滚超时时长")
    private Long fallbackTimeout;

    /**
     * 每1秒限制请求数(令牌数)
     */
    @TableField("replenish_rate")
	@ColumnType(length=255)
	@ColumnComment("每1秒限制请求数(令牌数)")
    private Integer replenishRate;

    /**
     * 令牌桶的容量
     */
    @TableField("burst_capacity")
	@ColumnType(length=255)
	@ColumnComment("令牌桶的容量")
    private Integer burstCapacity;

    /**
     * 权重名称
     */
    @TableField("weight_name")
	@ColumnType(length=255)
	@ColumnComment("权重名称")
    private String weightName;

    /**
     * 权重
     */
    @TableField("weight")
	@ColumnType(length=255)
	@ColumnComment("权重")
    private Integer weight;

    /**
     * 状态，0是启用，1是禁用
     */
    @TableField("status")
	@ColumnType(length=255)
	@ColumnComment("状态，0是启用，1是禁用")
    private String status;

    /**
     * 断言前缀截取层数
     */
    @TableField("strip_prefix")
	@ColumnType(length=255)
	@ColumnComment("断言前缀截取层数")
    private Integer stripPrefix;

    /**
     * 请求参数
     */
    @TableField("request_parameter")
	@ColumnType(length=255)
	@ColumnComment("请求参数")
    private String requestParameter;

    /**
     * 重写Path路径
     */
    @TableField("rewrite_path")
	@ColumnType(length=255)
	@ColumnComment("重写Path路径")
    private String rewritePath;

    /**
     * 鉴权：Header
     */
    @TableField("access_header")
	@ColumnType(length=255)
	@ColumnComment("鉴权：Header")
    private String accessHeader;

    /**
     * 鉴权：IP
     */
    @TableField("access_ip")
	@ColumnType(length=255)
	@ColumnComment("鉴权：IP")
    private String accessIp;

    /**
     * 鉴权：Parameter
     */
    @TableField("access_parameter")
	@ColumnType(length=255)
	@ColumnComment("鉴权：Parameter")
    private String accessParameter;

    /**
     * 鉴权：Time
     */
    @TableField("access_time")
	@ColumnType(length=255)
	@ColumnComment("鉴权：Time")
    private String accessTime;

    /**
     * 鉴权：Cookie
     */
    @TableField("access_cookie")
	@ColumnType(length=255)
	@ColumnComment("鉴权：Cookie")
    private String accessCookie;
}
