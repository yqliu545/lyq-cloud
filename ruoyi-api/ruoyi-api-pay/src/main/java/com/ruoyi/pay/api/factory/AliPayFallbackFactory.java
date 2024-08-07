package com.ruoyi.pay.api.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.pay.api.AliPayServiceFeign;
import com.ruoyi.pay.api.domain.AliPayParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 日志服务降级处理
 *
 * @author ruoyi
 */
@Component
public class AliPayFallbackFactory implements FallbackFactory<AliPayServiceFeign>
{
    private static final Logger log = LoggerFactory.getLogger(AliPayFallbackFactory.class);

    @Override
    public AliPayServiceFeign create(Throwable throwable)
    {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new AliPayServiceFeign()
        {
            @Override
            public R<String> pay(AliPayParams aliPayParams, String source) {
                return null;
            }

        };

    }
}
