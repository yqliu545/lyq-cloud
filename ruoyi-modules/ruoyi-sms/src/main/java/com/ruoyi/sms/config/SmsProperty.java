package com.ruoyi.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sms")
public class SmsProperty {
    private String accessKey;
    private String accessKeySecret;
    private String signname;
    private String templatecode;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSignname() {
        return signname;
    }

    public void setSignname(String signname) {
        this.signname = signname;
    }

    public String getTemplatecode() {
        return templatecode;
    }

    public void setTemplatecode(String templatecode) {
        this.templatecode = templatecode;
    }
}
