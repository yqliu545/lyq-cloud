package com.ruoyi.pay.service;

import com.ruoyi.pay.domain.AliPayParams;
import com.ruoyi.pay.domain.Order;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface AliPayService {

    String makeOrder(Order order);






    String pay(AliPayParams aliPayParams);
}
