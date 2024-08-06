package com.ruoyi.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class PayProperties {
    private String appId;
    private String privateKey;
    private String alipayPublicKey;
    private String serverUrl;
    private String notifyUrl;
    private String returnUrl;
    private String returnMsg;
    private String format;
    private String charset;
    private String signType;

}
