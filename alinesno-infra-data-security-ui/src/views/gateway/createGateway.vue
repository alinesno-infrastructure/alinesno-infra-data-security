<template>
	<div class="app-container">
		<el-page-header @back="goBack" content="服务管理"></el-page-header>
		<el-row :gutter="20" style="margin-top: 20px;">
			<el-col>
				<el-card shadow="never" class="box-card">
					<div class="clearfix" style="margin-bottom:10px">
						<span>网关路由 <el-icon><Right /></el-icon> 服务配置</span>

						<el-popover trigger="click" placement="right" :width="700">
							<div style="font-size: 10pt;">
								<span>配置说明：</span><br/>
								<span>1.服务URL，支持http、https、server-id(lb://xxx)模式转发。</span><br/>
								<span>2.断言Path，请根据实际API调用路径设置，不含服务host和port，可添加自定义前缀。</span><br/>
								<span>3.过滤StripPrefix根据值，截取断言Path多节斜杠后内容，拼接到服务URL上。</span><br/>
								<span>4.重定向RewritePath，会将原始请求Path指向新的Path路径，但网关路由地址不变，如:/foo/abc指向/abc。</span><br/>
								<span>5.断言Header，会查找原始请求Header头部信息，获取匹配项。</span><br/>
								<span>6.示例：</span><br/>
								<span>&nbsp;&nbsp;a.服务URL示例：http://server:port、http://server.com、lb://xxx ,支持但不推荐：http://server:port/api.do。</span><br/>
								<span>&nbsp;&nbsp;b.断言Path示例：/route/producer/** 或 /producer/api。</span><br/>
								<span>&nbsp;&nbsp;c.断言Host示例：**.my.com 或 my.com 、127.0.0.1:38771。</span><br/>
								<span>&nbsp;&nbsp;d.断言RemoteAddr示例：192.168.1.1 或 192.168.1.1/100。</span><br/>
								<span>&nbsp;&nbsp;e.重定向RewritePath示例：/foo/(?&lt;segment&gt;.*),/$\{segment}，需满足java正则表达示或参见官方。</span><br/>
								<span>&nbsp;&nbsp;f.断言Header示例：Header=X-Request-Id, \d+，其中\d+表示正则匹配的任意值，需满足java正则表达示或参见官方。</span><br/>
								<span>7.如配置多个断言项，则gateway网关采用断言组合匹配方式（and关系）转发到符合的路由服务中。</span>
							</div>
							<template #reference>
								<el-button style="padding: 3px 0;margin-left:5px " type="text" title="说明"><i class="fa-solid fa-tags"></i></el-button>
							</template>
						</el-popover>
						<div style="float: right; margin-left: 10px;">
						    <el-button icon="Delete"  type="warning" @click="resetForm"> 清 空 </el-button>
						</div>
						<div style="float: right; margin-left: 10px;">
						    <el-button icon="Promotion"  type="success" @click="submit"> 发 布 </el-button>
						</div>
					</div>

					<el-row>
						<el-col :span="24">
							<div style="float: left;">
								<el-input size="large"  placeholder="示例：project-method" v-model="form.routeId" style="width: 343px;" :disabled="idDisabled">
								  <template #prepend>RouteId</template>
								</el-input>
							</div>
							<div style="float: left; margin-left: 10px;">
								<el-input size="large"  placeholder="示例：CRM" v-model="form.systemCode" style="width: 343px;">
								  <template #prepend>系统代号</template>
								</el-input>
							</div>
							<div style="float: left; margin-left: 10px;">
								<el-input size="large"  placeholder="示例：CRM-用户信息获取网关" v-model="form.name" style="width: 343px;">
								  <template #prepend>名称</template>
								</el-input>
							</div>
							<div style="float: left; margin-left: 10px;">
								<el-popover  placement="bottom" width="170" trigger="click">
								  <el-radio v-model="form.status" label="0">启用</el-radio>
								  <el-radio v-model="form.status" label="1">禁用</el-radio>
									<template #reference>
								  	<el-button size="large">服务状态:{{form.status === '0'?'启用':'禁用'}} <el-icon><CaretBottom /></el-icon></el-button>
									</template>
								</el-popover>
							</div>
							<div style="float: left; margin-left: 10px;">
								<el-popover placement="bottom" width="500" trigger="click">
								  <el-radio-group v-model="form.groupCode"  @change="handleSelectedGroup">
									  <el-radio-button v-for="item in groupOptions" :key="item.value" :label="item.value">{{item.label}}</el-radio-button>
								  </el-radio-group>
									<template #reference>
										<el-button size="large">分组:{{groupName}} <el-icon><CaretBottom /></el-icon></el-button>
									</template>
								</el-popover>
							</div>
						</el-col>
					</el-row>

					<el-row style="margin-top: 20px;">

						<el-col :span="24">
							<div style="float: left;">
							  <el-input size="large"  placeholder="请输入网关服务URL,示例:http://server:port、http://server.com、lb://xxx" v-model="form.uri" class="input-with-select" style="width: 696px;">
									<template #prepend>
										<el-select  size="large" v-model="form.method" placeholder="请选择" style="width: 90px;">
											<el-option v-for="item in methodOptions" :key="item.value" :label="item.label" :value="item.value"/>
										</el-select>
									</template>
							  </el-input>
							</div>
							<div style="float: left; margin-left: 10px;">
								<el-popover placement="bottom" width="500" trigger="click">
								  <el-input size="large"  placeholder="示例：Path=/route/producer/** 或 /producer/api" v-model="form.path" style="width: 475px;">
									<template #prepend>Path=</template>
								  </el-input>
									<template #reference>
										<el-button size="large">断言Path={{form.path}} <el-icon><CaretBottom /></el-icon></el-button>
									</template>
								</el-popover>
							</div>
							<div style="float: left; margin-left: 10px;">
								<el-popover placement="bottom" width="500" trigger="click">
								  <el-input size="large"  placeholder="示例：Header=X-Request-Id, \d+" v-model="form.header">
										<template #prepend>Header=</template>
								  </el-input>
									<template #reference>
										<el-button size="large">断言Header={{form.header}} <el-icon><CaretBottom /></el-icon></el-button>
									</template>
								</el-popover>
							</div>
							<div style="float: left; margin-left: 10px;">
								<el-popover placement="bottom" width="350" trigger="click">
								  <el-input size="large"  type="number" placeholder="请输入数字，示例：1" v-model="form.stripPrefix">
									<template #prepend>StripPrefix=</template>
								  </el-input>
									<template #reference>
								  	<el-button size="large">过滤StripPrefix={{form.stripPrefix}} <el-icon><CaretBottom /></el-icon></el-button>
									</template>
								</el-popover>
							</div>
						</el-col>
					</el-row>

					<el-row style="margin-top: 20px;">
						<el-col :span="24">
							<div style="float: left;">
								<el-popover placement="bottom" width="500" trigger="click">
								  <el-input size="large"  placeholder="示例：Host=**.my.com,my.com" v-model="form.host" >
									<template #prepend>Host=</template>
								  </el-input>
									<template #reference>
										<el-button size="large">断言Host={{form.host}} <el-icon><CaretBottom /></el-icon></el-button>
									</template>
								</el-popover>
							</div>
							<div style="float: left; margin-left: 10px;">
								<el-popover placement="bottom" width="500" trigger="click">
								  <el-input size="large"  placeholder="示例：RemoteAddr=192.168.1.1/100" v-model="form.remoteAddr" >
									<template #prepend>RemoteAddr=</template>
								  </el-input>
									<template #reference>
										<el-button size="large">断言RemoteAddr={{form.remoteAddr}} <el-icon><CaretBottom /></el-icon></el-button>
									</template>
								</el-popover>
							</div>
							<div style="float: left; margin-left: 10px;">
								<el-popover placement="bottom" width="550" trigger="click">
								  <el-input size="large"  placeholder="示例：RequestParameter=version,v01" v-model="form.requestParameter" >
									<template #prepend>RequestParameter=</template>
								  </el-input>
									<template #reference>
										<el-button size="large">参数RequestParameter={{form.requestParameter}} <el-icon><CaretBottom /></el-icon></el-button>
									</template>
								</el-popover>
							</div>
							<div style="float: left; margin-left: 10px;">
								<el-popover placement="bottom" width="600" trigger="click">
								  <el-input size="large"  placeholder="示例：RewritePath=/foo/(?<segment>.*), /$\{segment}" v-model="form.rewritePath">
									<template #prepend>RewritePath=</template>
								  </el-input>
									<template #reference>
										<el-button size="large">重定向RewritePath={{form.rewritePath}} <el-icon><CaretBottom /></el-icon></el-button>
									</template>
								</el-popover>
							</div>
						</el-col>
					</el-row>

				</el-card>
			</el-col>
		</el-row>

		<el-row :gutter="20" style="margin-top: 20px;">
			<el-col :span="6">
				<el-card shadow="never" class="box-card">
					<div class="clearfix">
						<span>熔断器</span>
						<el-button style="float: right; padding: 3px 0; " icon="Question" type="text">说明</el-button>
					</div>
					<br/>
					<el-collapse accordion>
					  <el-collapse-item>
					    <template #title>
					      全局方法&nbsp;&nbsp;<i v-show="hystrix.defaultChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
					    </template>
					    <div><el-checkbox v-model="hystrix.defaultChecked" @change="handleHystrixChecked('default')">启用</el-checkbox></div>
					    <div>启用超时检测，当请求到响应返回的时间，超过5000ms后，将触发全局Hystrix熔断方法的（name: fallbackcmd），默认回调路径fallbackUri: forward:/fallback。</div>
					  </el-collapse-item>
					  <el-collapse-item title="自定义方法">
						  <template #title>
						    自定义方法&nbsp;&nbsp;<i v-show="hystrix.customChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
						  </template>
					    <div><el-checkbox v-model="hystrix.customChecked" @change="handleHystrixChecked('custom')">启用</el-checkbox></div>
					    <div>启用超时检测，当请求到响应返回的时间，超过自定义时长后，将触发自定义Hystrix熔断方法（name: customHystrix_no），默认回调路径fallbackUri: forward:/fallback/custom 。(如无特殊要求，<span style="font-weight: bold;">强列建议用默认全局Hystrix方法</span>，过多定制Hystrix否则会增加gateway网关服务性能压力)</div>
						<div style="margin-top: 10px;">
							<el-input size="large"  placeholder="触发fallbackcmd超时时长,如:3000" v-model="form.fallbackTimeout">
							  <template #prepend>超时时长</template>
							</el-input>
						</div>
						<div style="margin-top: 10px;">
							<el-input size="large"  placeholder="触发fallbackcmd提示内容,如:system is error" v-model="form.fallbackMsg">
							  <template #prepend>提示内容</template>
							</el-input>
						</div>
					  </el-collapse-item>
					</el-collapse>
				</el-card>

				<el-card shadow="never" class="box-card" style="margin-top: 20px;">
					<div class="clearfix">
						<span>监控器</span>
						<el-popover trigger="click" placement="bottom">
							<div style="font-size: 10pt;">
								<span>配置说明：</span><br/>
								<span>1.只向网关服务发起http请求，只有服务host和port，不含请求路径和参数。</span><br/>
								<span>2.未超时则认为服务存活，不考虑服务有效性。</span><br/>
								<span>3.心跳检测请求Header中带<span style="font-weight: bold;">Keepalive:flying-fish-gateway</span>，服务可做特殊性响应。</span><br/>
								<span>4.心跳检测服务URL示例：http://server:port、http://server.com、lb://xxx 。</span><br/>
								<span>5.网关服务无客户端请求后，每30秒触发一次心跳检测</span><br/>
								<span>6.告警重试设置为禁用后，心跳检测失败后，将不再继续检测，并且网关将会拒绝所有客户端请求，直到网关服务状态为：启用</span>
							</div>
							<template #reference>
								<el-button style="float: right; padding: 3px 0; " icon="Question" type="text" title="说明">说明</el-button>
							</template>
						</el-popover>
					</div>
					<el-collapse accordion>
					  <el-collapse-item>
					    <template #title>
					      监控告警&nbsp;&nbsp;<i v-show="monitor.checked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
					    </template>
					    <div><el-checkbox v-model="monitor.checked">启用</el-checkbox></div>
					    <div>基于网关服务心跳检测，当网关服务正常运行中，没有客户端请求后，开始每30秒一次心跳检测。</div>
						<div style="margin-top: 10px;">
							<el-popover placement="bottom" width="170" trigger="click">
								<el-radio v-model="form.monitor.recover" label="0">启用</el-radio>
								<el-radio v-model="form.monitor.recover" label="1">禁用</el-radio>
								<template #reference>
									<el-button size="large">告警重试：{{form.monitor.recover === '0'?'启用':'禁用'}} <el-icon><CaretBottom /></el-icon></el-button>
								</template>
							</el-popover>
							<el-popover placement="bottom" width="460" trigger="click">
								<el-radio-group v-model="form.monitor.frequency"  @change="handleSelectedMonitorFrequency">
									<el-radio-button v-for="item in monitorOptions" :key="item.value" :label="item.value">{{item.label}}</el-radio-button>
								</el-radio-group>
								<template #reference>
									<el-button size="large">告警通知频率：{{monitorFrequencyName}} <el-icon><CaretBottom /></el-icon></el-button>
								</template>
							</el-popover>
						</div>
						<div style="margin-top: 10px;">
							<el-input size="large"  placeholder="示例：user1@qq.com,user2@qq.com" v-model="form.monitor.emails" maxlength="200" show-word-limit>
							  <template #prepend>通知邮箱</template>
							</el-input>
						</div>
						<div style="margin-top: 10px;">
							<el-input size="large"  placeholder="示例：XXX网关服务发生告警，请及时处理" v-model="form.monitor.topic" maxlength="200" show-word-limit>
							  <template #prepend>告警提示</template>
							</el-input>
						</div>
					  </el-collapse-item>
					</el-collapse>
				</el-card>
			</el-col>

			<el-col :span="6">
				<el-card shadow="never" class="box-card">
					<div class="clearfix">
						<span>过滤器</span>
						<el-popover trigger="click" placement="bottom">
							<div style="font-size: 10pt;">
								<span>配置说明：</span><br/>
								<span>1.IP过滤需要配置本网关路由的注册客户端，非注册客户端IP不可访问。</span><br/>
								<span>2.IP名单管理中的禁止通行的IP不可访问本网关路由。</span><br/>
								<span>3.TOKEN过滤目前只对请求Header中带<span style="font-weight: bold;">TOKEN</span>做非空验证，暂无其它响应。</span><br/>
								<span>4.ID过滤对请求Header中带<span style="font-weight: bold;">CLIENTID</span>做较验，非注册客户端ID不可访问。</span><br/>
							</div>
							<template #reference>
								<el-button style="float: right; padding: 3px 0; " icon="Question" type="text" title="说明">说明</el-button>
							</template>
						</el-popover>
					</div>
					<br/>
					<el-collapse accordion>
					  <el-collapse-item>
					    <template #title>
					      IP过滤&nbsp;&nbsp;<i v-show="filter.ipChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
					    </template>
					    <div><el-checkbox v-model="filter.ipChecked">启用</el-checkbox></div>
					    <div>基于IP进行拦截，只有客户端管理中添加对本网关服务连接权限的指定IP才能访问本路由地址。</div>
					  </el-collapse-item>
					  <el-collapse-item>
						  <template #title>
						    TOKEN过滤&nbsp;&nbsp;<i v-show="filter.tokenChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
						  </template>
					    <div><el-checkbox v-model="filter.tokenChecked">启用</el-checkbox></div>
					    <div>基于TOKEN进行拦截，只有符合指定TOKEN才能访问本路由地址。</div>
					  </el-collapse-item>
					  <el-collapse-item>
						  <template #title>
						    ID过滤&nbsp;&nbsp;<i v-show="filter.idChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
						  </template>
					    <div><el-checkbox v-model="filter.idChecked">启用</el-checkbox></div>
					    <div>基于ID进行拦截，只有客户端管理中添加对本网关服务连接权限的指定ID才能访问本路由地址。</div>
					  </el-collapse-item>
					</el-collapse>
				</el-card>
			</el-col>

			<el-col :span="6">
				<el-card shadow="never" class="box-card">
					<div class="clearfix">
						<span>限流器</span>
						<el-button style="float: right; padding: 3px 0; " icon="Question" type="text">说明</el-button>
					</div>
					<div style="margin-top: 10px;">
						<el-row :gutter="24">
							<el-col :span="6">
								<span class="text item" style="line-height: 38px;">令牌总量</span>
							</el-col>
							<el-col :span="18">
								<el-input size="large" type="number" v-model="form.burstCapacity" :step="20" :min="0" :max="10000" style="width: 60%;"/>
							</el-col>
						</el-row>
					</div>
					<div style="margin-bottom: 10px; border: 0px solid red;">
						<el-row :gutter="24">
							<el-col :span="6">
								<span class="text item" style="line-height: 38px;">每秒流量</span>
							</el-col>
							<el-col :span="18">
								<el-slider v-model="form.replenishRate" :step="1" :min="0" :max="form.burstCapacity" show-stops />
							</el-col>
						</el-row>
					</div>

					<el-collapse accordion>
					  <el-collapse-item>
					    <template #title>
					      IP限流&nbsp;&nbsp;<i v-show="limiter.ipChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
					    </template>
					    <div><el-checkbox v-model="limiter.ipChecked" @change="handleLimiterChecked('ip')">启用</el-checkbox></div>
					    <div>基于令牌桶算法，当访问网关路由URL的请求host主机名数量，超出限流规则的约束，则直接拒决请求。</div>
					  </el-collapse-item>
					  <el-collapse-item title="URI限流">
					    <template #title>
					      URI限流&nbsp;&nbsp;<i v-show="limiter.uriChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
					    </template>
					    <div><el-checkbox v-model="limiter.uriChecked" @change="handleLimiterChecked('uri')">启用</el-checkbox></div>
					    <div>基于令牌桶算法，当访问网关路由URL的请求数量，超出限流规则的约束，则直接拒决请求。</div>
					  </el-collapse-item>
					  <el-collapse-item>
					    <template #title>
					      REQUESTID限流&nbsp;&nbsp;<i v-show="limiter.idChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
					    </template>
					    <div><el-checkbox v-model="limiter.idChecked" @change="handleLimiterChecked('id')">启用</el-checkbox></div>
					    <div>基于令牌桶算法，当访问网关路由URL的RequestId（从请求参数中获取）数量，超出限流规则的约束，则直接拒决请求。</div>
					  </el-collapse-item>
					</el-collapse>
				</el-card>

			</el-col>

			<el-col :span="6">
				<el-card shadow="never" class="box-card">
					<div class="clearfix">
						<span>鉴权器</span>
						<el-button style="float: right; padding: 3px 0; " icon="Question" type="text">说明</el-button>
					</div>
					<br/>
					<el-collapse accordion>
					  <el-collapse-item>
					    <template #title>
					      HEADER验证&nbsp;&nbsp;<i v-show="access.headerChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
					    </template>
					    <div>
								<el-checkbox v-model="access.headerChecked">启用</el-checkbox>
							</div>
					    <div>获取客户端请求中的所带的HEADER头部信息，验证指定键值，不符合验证规则，则直接拒决请求。</div>
						<div style="margin-top: 10px;">
							<el-input size="large"  placeholder="示例：Accept-Language: zh-CN,zh;q=0.9" v-model="form.accessHeader">
							  <template #prepend>HEADER</template>
							</el-input>
						</div>
					  </el-collapse-item>
					  <el-collapse-item>
						  <template #title>
						    IP验证&nbsp;&nbsp;<i v-show="access.ipChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
						  </template>
					    <div><el-checkbox v-model="access.ipChecked">启用</el-checkbox></div>
					    <div>如果启用IP过滤，则会先执行IP过滤后，再执行本IP验证，不符合验证规则，则直接拒决请求。通常用于临时性IP过滤。</div>
						<div style="margin-top: 10px;">
							<el-input size="large"  placeholder="示例：192.168.1.100,92.168.1.*" v-model="form.accessIp">
							  <template #prepend>IP</template>
							</el-input>
						</div>
					  </el-collapse-item>
					  <el-collapse-item>
						  <template #title>
							参数验证&nbsp;&nbsp;<i v-show="access.parameterChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
						  </template>
					    <div><el-checkbox v-model="access.parameterChecked">启用</el-checkbox></div>
					    <div>获取URL请求串中的所带的参数，验证指定参数值，不符合验证规则，则直接拒决请求。</div>
						<div style="margin-top: 10px;">
							<el-input size="large"  placeholder="示例：token=uuid" v-model="form.accessParameter">
							  <template #prepend>请求参数</template>
							</el-input>
						</div>
					  </el-collapse-item>
					  <el-collapse-item>
						  <template #title>
						  	时间验证&nbsp;&nbsp;<i v-show="access.timeChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
						  </template>
					    <div><el-checkbox v-model="access.timeChecked">启用</el-checkbox></div>
					    <div>只允许指定时间段内进行访问，不符合验证规则，则直接拒决请求。</div>
						<div style="margin-top: 10px;">
							<el-input size="large"  placeholder="示例：08:00:00,20:00:00" v-model="form.accessTime">
							  <template #prepend>时间</template>
							</el-input>
						</div>
					  </el-collapse-item>
					  <el-collapse-item title="Cookie验证">
						  <template #title>
						  	Cookie验证&nbsp;&nbsp;<i v-show="access.cookieChecked" class="header-icon el-icon-success" style="color: #34bfa3; font-size: 12pt;"></i>
						  </template>
					    <div><el-checkbox v-model="access.cookieChecked">启用</el-checkbox></div>
					    <div>获取客户端请求所带的cookie信息，验证指定cookie参数值，不符合验证规则，则直接拒决请求。</div>
						<div style="margin-top: 10px;">
							<el-input size="large"  placeholder="示例：name=value" v-model="form.accessCookie">
							  <template #prepend>Cookie</template>
							</el-input>
						</div>
					  </el-collapse-item>
					</el-collapse>
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
// import gatewayJson from '@/api/json/gateway.json'
import {addRoute,updateRoute} from '@/api/gateway_api.js'

export default {
	data() {
		return {
			form:{
				id:'',
				routeId: '',
				systemCode:'',
				name:'',
				stripPrefix:'',
				status:'1',
				uri:'',
				method:'',
				path:'',
				host:'',
				remoteAddr:'',
				header:'',
				rewritePath:'',
				requestParameter:'',
				accessHeader:'',
				accessIp:'',
				accessParameter:'',
				accessTime:'',
				accessCookie:'',
				fallbackMsg:'',
				fallbackTimeout:0,
				replenishRate:20,
				burstCapacity:100,
				burstFrequency:100,
				burstTimeUnit:'min',//minute,hour,day,week,month,year
				groupCode:'',
				monitor:{
					recover: '0',
					frequency: '30m',
					emails: '',
					topic: ''
				}
			},
			filter:{
				ipChecked: false,
				tokenChecked: false,
				idChecked: false
			},
			hystrix:{
				defaultChecked: false,
				customChecked: false
			},
			limiter:{
				ipChecked: false,
				uriChecked: false,
				idChecked: false
			},
			access:{
				headerChecked: false,
				ipChecked: false,
				parameterChecked: false,
				timeChecked: false,
				cookieChecked: false
			},
			monitor: {
				checked: false
			},
			handleType: 'add',
			idDisabled: false,
			methodOptions: [
				{value: null, label: 'ALL'},
				{value: 'POST',label: 'POST'},
				{value: 'GET',label: 'GET'},
				{value: 'PUT',label: 'PUT'},
				{value: 'DELETE',label: 'DELETE'},
			],
			monitorFrequencyName: '',
			monitorOptions: [
				{value: '30m',label: '30分钟一次'},
				{value: '1h', label: '1小时一次'},
				{value: '5h',label: '5小时一次'},
				{value: '12h',label: '12小时一次'},
				{value: '24h',label: '24小时一次'}
			],
			groupName:'',
			groupOptions: this.GLOBAL_VAR.groups
		}
	},
	created: function() {
		//在组件创建完毕后加载
		let query = this.$route.query;
		if (query){
			this.handleType = query.handleType;
			if (this.handleType === 'edit'){
				let route = JSON.parse(query.route);
				console.log('route', route);
				this.init(route);
			}
		}
	},
	mounted: function() {
	},
	beforeDestroy: function() {
	},
	methods:{
		init(route) {
			if (route && route.form){
				this.form = route.form;
				this.filter = route.filter;
				this.hystrix = route.hystrix;
				this.limiter = route.limiter;
				this.access = route.access;
				this.monitor = route.monitor;
				this.idDisabled = true;
				if (this.form.monitor == undefined){
					this.form.monitor = {
						checked: false,
						recover: '0',
						frequency: '30m',
						emails: '',
						topic: ''
					};
				}
				this.handleSelectedGroup(this.form.groupCode);
				this.handleSelectedMonitorFrequency(this.form.monitor? this.form.monitor.frequency: null);
			}
		},
		goBack() {
			this.$router.push({path:'/gatewayList',query:{}});
		},
		handleHystrixChecked(type){//熔断器，二选一
			this.hystrix.defaultChecked = (this.hystrix.defaultChecked && type === 'default')?true:false;
			this.hystrix.customChecked = (this.hystrix.customChecked && type === 'custom')?true:false;
		},
		handleLimiterChecked(type){//限流器，三选一
			this.limiter.ipChecked = (this.limiter.ipChecked && type === 'ip')?true:false;
			this.limiter.uriChecked = (this.limiter.uriChecked && type === 'uri')?true:false;
			this.limiter.idChecked = (this.limiter.idChecked && type === 'id')?true:false;
		},
		handleSelectedGroup(val){
			let size = this.groupOptions.length;
			for (var i=0;i <size; i++){
				if (this.groupOptions[i].value === val){
					this.groupName = this.groupOptions[i].label;
					break;
				}
			}
		},
		handleSelectedMonitorFrequency(val){
			if (val){
				let size = this.monitorOptions.length;
				for (var i=0;i <size; i++){
					if (this.monitorOptions[i].value === val){
						this.monitorFrequencyName = this.monitorOptions[i].label;
						break;
					}
				}
			}
		},
		submit(){
			let data = {form:this.form, filter:this.filter, hystrix:this.hystrix, limiter:this.limiter, access:this.access, monitor:this.monitor};
			let _this = this;
			if (this.handleType === 'edit'){
				updateRoute(data).then(function(result){
					console.log(result);
					_this.GLOBAL_FUN.successMsg();
				});
			} else {
				addRoute(data).then(function(result){
					_this.GLOBAL_FUN.successMsg();
				});
			}
		},
		resetForm() {
			this.form = {
				id: this.handleType === 'edit'?this.form.id:null,
		routeId: this.handleType === 'edit'?this.form.routeId:null,
				systemCode:null,
				name:null,
				stripPrefix:'',
				status:'1',
				uri:null,
				method:null,
				path:null,
				host:null,
				remoteAddr:null,
				header:null,
				rewritePath:null,
				requestParameter:null,
				accessHeader:null,
				accessIp:null,
				accessParameter:null,
				accessTime:'',
				accessCookie:null,
				fallbackMsg:null,
				fallbackTimeout:0,
				replenishRate:20,
				burstCapacity:100,
			}
			this.filter={}
			this.limiter={}
			this.hystrix={}
			this.access={}
		}
	}
}
</script>

<style scoped>
.text {
	font-size: 14px;
}
.item {
	margin-bottom: 18px;
}
.clearfix:before,
.clearfix:after {
	display: table;
	content: '';
}
.clearfix:after {
	clear: both;
}

/* /deep/input[type="number"]::-webkit-outer-spin-button, /deep/input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none !important;
} */
/* input[type="number"]{
  -moz-appearance: textfield !important;
} */
</style>

