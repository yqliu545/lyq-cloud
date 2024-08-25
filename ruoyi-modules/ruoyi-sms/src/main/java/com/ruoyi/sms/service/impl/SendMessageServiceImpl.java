package com.ruoyi.sms.service.impl;

import com.alibaba.fastjson2.JSON;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.order.api.OrderServiceFeign;
import com.ruoyi.order.api.domin.Merchandise;
import com.ruoyi.order.api.domin.Order;
import com.ruoyi.sms.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSONString;

public class SendMessageServiceImpl implements SendMessageService {
    @Autowired
    private OrderServiceFeign orderServiceFeign;

    @Autowired
    private Client client;

    @Override
    public void sendMessage(String orderNo) {
        //查询订单
        R<Order> result= orderServiceFeign.selectOrderByOrderNo(orderNo, SecurityConstants.INNER);
        Order orderData = result.getData();
        //查询商品信息
        R<Merchandise> good= orderServiceFeign.selectMerchandiseById(orderData.getMerchandiseId(), SecurityConstants.INNER);
        Merchandise data = good.getData();
        Map<String, String> map = new HashMap<>();
        map.put("name",orderData.getCreateBy());
        map.put("content",data.getDescription());
        String params = JSON.toJSONString(map);

        //发送消息
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setPhoneNumbers(orderData.getIphone())
                .setSignName("缤纷世界你我掌握")
                .setTemplateCode("SMS_472075245")
                .setTemplateParam(params);
        try {
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            String code = sendSmsResponse.getBody().getCode();
            if ("OK".equals(code))
            orderServiceFeign.updateOrderStatus(orderNo,SecurityConstants.INNER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
