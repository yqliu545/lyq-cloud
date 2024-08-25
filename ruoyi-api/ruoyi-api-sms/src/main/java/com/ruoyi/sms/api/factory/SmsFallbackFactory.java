package com.ruoyi.sms.api.factory;

import com.ruoyi.sms.api.SmsServiceFeign;
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
public class SmsFallbackFactory implements FallbackFactory<SmsServiceFeign>
{
    private static final Logger log = LoggerFactory.getLogger(SmsFallbackFactory.class);

    @Override
    public SmsServiceFeign create(Throwable throwable)
    {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new SmsServiceFeign()
        {
            @Override
            public void sendMesssage(String orderNo, String source) {
                return;
            }
        };

    }
}
