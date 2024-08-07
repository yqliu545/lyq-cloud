package com.ruoyi.pay.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.annotation.InnerAuth;
import com.ruoyi.pay.config.PayProperties;
import com.ruoyi.pay.domain.AliPayParams;
import com.ruoyi.pay.domain.Order;
import com.ruoyi.pay.service.AliPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/alipay")
public class PayController {

    private static final Logger log = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private PayProperties payProperties;

    @Autowired
    private AliPayService aliPayService;

    //下单
    @PostMapping("/makeOrder")
    public R<String> makeOrder(@RequestBody Order order,HttpServletResponse response) throws IOException {
        return R.ok(aliPayService.makeOrder(order));
    }



    //支付平台支付成功的同步回调
    @RequestMapping("/returnUrl")
    public void returnUrl(HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, String> params = new HashMap<>();
        Map parameterMap = request.getParameterMap();
        for (Iterator iter = parameterMap.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) parameterMap.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr =(i==values.length-1)? valueStr+values[i] : valueStr+values[i]+"," ;
            }
            params.put(name, valueStr);
        }
        //获取支付宝的返回参数
        String outTradeNo = request.getParameter("out_trade_no");
        String tradeNo = request.getParameter("trade_no");
        String sellerId = request.getParameter("seller_id");
        String totalAmount = request.getParameter("total_amount");
        //调用sdk验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, payProperties.getAlipayPublicKey(), payProperties.getCharset(), payProperties.getSignType());
        if (signVerified) {
            //修改订单状态，根据商户订单号查询订单信息
//            aliPayService.alipayCallback(request,response);
            log.info("支付成功同步通知=>{}",outTradeNo);
            response.sendRedirect("http://127.0.0.1:80/index");
        }else {
            //验签失败
            response.sendRedirect("http://127.0.0.1:80/index");
        }


    }

    //支付平台支付成功的异步回调
    @PostMapping("/notifyUrl")
    public String notifyUrl(HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map<String, String> params = new HashMap<>();
        Map parameterMap = request.getParameterMap();
        for (Iterator iter = parameterMap.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) parameterMap.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr =(i==values.length-1)? valueStr+values[i] : valueStr+values[i]+"," ;
            }
            params.put(name, valueStr);
        }
        //获取支付宝的返回参数
        String outTradeNo = request.getParameter("out_trade_no");
        String tradeNo = request.getParameter("trade_no");
        String sellerId = request.getParameter("seller_id");
        String totalAmount = request.getParameter("total_amount");
        String trade_status = request.getParameter("trade_status");
        //调用sdk验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, payProperties.getAlipayPublicKey(), payProperties.getCharset(), payProperties.getSignType());
        if (signVerified) {
            if (trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")) {
                //修改订单状态，根据商户订单号查询订单信息
                log.info("支付成功异步通知=>{}",outTradeNo);
            }else {
                //失败
                return "failure";
            }
        }else {
            //验签失败
            return "failure";
        }

        return "success";

    }


    @InnerAuth
    @PostMapping("/inpay")
    public R<String> pay(@RequestBody AliPayParams aliPayParams){
        return R.ok(aliPayService.pay(aliPayParams));
    }



}
