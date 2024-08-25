package com.ruoyi.order.api.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.order.api.OrderServiceFeign;
import com.ruoyi.order.api.domin.Merchandise;
import com.ruoyi.order.api.domin.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderFallbackFactory implements FallbackFactory<OrderServiceFeign> {

    private static final Logger log = LoggerFactory.getLogger(OrderFallbackFactory.class);
    @Override
    public OrderServiceFeign create(Throwable throwable) {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new OrderServiceFeign()
        {

            @Override
            public R<Boolean> updateOrderStatus(String orderNo, String source) {
                return R.fail("修改订单状态失败："+throwable.getMessage());
            }

            @Override
            public R<Order> selectOrderByOrderNo(String orderNo, String source) {
                return R.fail("查询订单失败："+throwable.getMessage());
            }

            @Override
            public R<Merchandise> selectMerchandiseById(Long merchandiseId, String source) {
                return R.fail("查询商品失败："+throwable.getMessage());
            }
        };
    }
}
