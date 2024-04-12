package com.alinesno.infra.base.gateway.core.bean;

import java.util.List;

import com.alinesno.infra.base.gateway.core.entity.Balanced;
import com.alinesno.infra.base.gateway.core.entity.LoadServer;

import lombok.Data;

/**
 * @description
 * @author jianglong
 * @date 2020/06/30
 * @version v1.0.0
 */
@Data
public class BalancedRsp implements java.io.Serializable {
    private Balanced balanced;
    private List<LoadServer> serverList;
}
