package com.ruoyi.sms.api;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.sms.api.factory.SmsFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(contextId = "smsServiceFeign", value = ServiceNameConstants.SMS_SERVICE, fallbackFactory = SmsFallbackFactory.class)
public interface SmsServiceFeign {

    @GetMapping("/sms/send/{orderNo}")
    public void sendMesssage(@PathVariable("orderNo") String orderNo, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
