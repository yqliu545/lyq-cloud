package com.ruoyi.pay.api;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.pay.api.domain.AliPayParams;
import com.ruoyi.pay.api.factory.AliPayFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "aliPayServiceFeign", value = ServiceNameConstants.PAY_SERVICE, fallbackFactory = AliPayFallbackFactory.class)
public interface AliPayServiceFeign {

    @PostMapping("/alipay/inpay")
    public R<String> pay(@RequestBody AliPayParams aliPayParams,@RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
