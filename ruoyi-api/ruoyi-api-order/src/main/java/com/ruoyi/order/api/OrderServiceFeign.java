package com.ruoyi.order.api;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.order.api.domin.Order;
import com.ruoyi.order.api.factory.OrderFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "orderServiceFeign", value = ServiceNameConstants.ORDER_SERVICE, fallbackFactory = OrderFallbackFactory.class)
public interface OrderServiceFeign {
    @GetMapping("/order/order/updateStatus/{orderNo}")
    public R<Boolean> updateOrderStatus(@PathVariable("orderNo") String orderNo, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
