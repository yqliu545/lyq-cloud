package com.ruoyi.pay.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.pay.domain.Order;
import com.ruoyi.pay.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/alipay")
public class PayController {

    @Autowired
    private AliPayService aliPayService;

    //下单
    @PostMapping("/makeOrder")
    public R<String> makeOrder(@RequestBody Order order,HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().write(aliPayService.makeOrder(order));
//        response.getWriter().flush();
//        response.getWriter().close();
        return R.ok(aliPayService.makeOrder(order));
    }
    //生成支付二维码
    @GetMapping("/code")
    public void createAlipayCode(String url, HttpServletResponse response){
        aliPayService.makeQRCode(url,response);
    }
    //检查订单状态
    @RequestMapping("/checkOrder")
    public String checkOrder(String tradeNo) throws Exception{
        if (StringUtils.isEmpty(tradeNo)){
            return null;
        }
        String status=aliPayService.checkAliOrder(tradeNo);
        return status;
    }

    //支付平台支付成功的回调
    @RequestMapping("/callback")
    public void paySuccessCallback(HttpServletRequest request,HttpServletResponse response) throws Exception{
        aliPayService.alipayCallback(request,response);
    }



}
