package com.ruoyi.sms.controller;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.security.annotation.InnerAuth;
import com.ruoyi.sms.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController("/send")
public class SendMessageController {
    @Autowired
    private SendMessageService sendMessageService;
    @GetMapping("/{orderNo}")
    @InnerAuth
    public void sendMesssage(@PathVariable("orderNo") String orderNo){
        sendMessageService.sendMessage(orderNo);
    }
}
