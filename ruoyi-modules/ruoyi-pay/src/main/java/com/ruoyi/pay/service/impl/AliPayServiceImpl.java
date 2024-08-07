package com.ruoyi.pay.service.impl;

import cn.hutool.core.lang.UUID;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.pay.config.PayProperties;
import com.ruoyi.pay.domain.AliPayParams;
import com.ruoyi.pay.domain.Order;
import com.ruoyi.pay.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service
public class AliPayServiceImpl implements AliPayService {
    @Autowired
    private PayProperties payProperties;

    @Override
    public String makeOrder(Order order) {
        // 初始化SDK
        AlipayClient alipayClient = null;
        try {
            alipayClient = new DefaultAlipayClient(getAlipayConfig());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        // 构造请求参数以调用接口
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();

        // 设置商户门店编号
//        model.setStoreId("2088721025990492");

        // 设置订单绝对超时时间
//        model.setTimeExpire("2024-08-7 10:05:01");

        // 设置业务扩展参数
//        ExtendParams extendParams = new ExtendParams();
//        extendParams.setSysServiceProviderId("2088511833207846");
//        extendParams.setHbFqSellerPercent("100");
//        extendParams.setHbFqNum("3");
//        extendParams.setIndustryRefluxInfo("{\"scene_code\":\"metro_tradeorder\",\"channel\":\"xxxx\",\"scene_data\":{\"asset_name\":\"ALIPAY\"}}");
//        extendParams.setSpecifiedSellerName("XXX的跨境小铺");
//        extendParams.setRoyaltyFreeze("true");
//        extendParams.setCardType("S0JP0000");
//        model.setExtendParams(extendParams);

        // 设置订单标题
        model.setSubject("Iphone616G");

        // 设置请求来源地址
//        model.setRequestFromUrl("https://");

        // 设置产品码
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        // 设置PC扫码支付的方式
        model.setQrPayMode("1");

//        // 设置商户自定义二维码宽度
        model.setQrcodeWidth(100L);

//        // 设置请求后页面的集成方式
        model.setIntegrationType("PCWEB");

        // 设置订单包含的商品列表信息
//        List<GoodsDetail> goodsDetail = new ArrayList<GoodsDetail>();
//        GoodsDetail goodsDetail0 = new GoodsDetail();
//        goodsDetail0.setGoodsName("ipad");
//        goodsDetail0.setAlipayGoodsId("20010001");
//        goodsDetail0.setQuantity(1L);
//        goodsDetail0.setPrice("2000");
//        goodsDetail0.setGoodsId("apple-01");
//        goodsDetail0.setGoodsCategory("34543238");
//        goodsDetail0.setCategoriesTree("124868003|126232002|126252004");
//        goodsDetail0.setShowUrl("http://www.alipay.com/xxx.jpg");
//        goodsDetail.add(goodsDetail0);
//        model.setGoodsDetail(goodsDetail);

        // 设置商户的原始订单号
//        model.setMerchantOrderNo("20161008001");

        // 设置二级商户信息
//        SubMerchant subMerchant = new SubMerchant();
//        subMerchant.setMerchantId("2088000603999128");
//        subMerchant.setMerchantType("alipay");
//        model.setSubMerchant(subMerchant);

        // 设置开票信息
//        InvoiceInfo invoiceInfo = new InvoiceInfo();
//        InvoiceKeyInfo keyInfo = new InvoiceKeyInfo();
//        keyInfo.setTaxNum("1464888883494");
//        keyInfo.setIsSupportInvoice(true);
//        keyInfo.setInvoiceMerchantName("ABC|003");
//        invoiceInfo.setKeyInfo(keyInfo);
//        invoiceInfo.setDetails("[{\"code\":\"100294400\",\"name\":\"服饰\",\"num\":\"2\",\"sumPrice\":\"200.00\",\"taxRate\":\"6%\"}]");
//        model.setInvoiceInfo(invoiceInfo);

        // 设置商户订单号
        String uuid = DateUtils.parseDateToStr("yyyyMMddHHmmssSSS", new Date());
        model.setOutTradeNo(uuid);

        // 设置外部指定买家
//        ExtUserInfo extUserInfo = new ExtUserInfo();
//        extUserInfo.setCertType("IDENTITY_CARD");
//        extUserInfo.setCertNo("362334768769238881");
//        extUserInfo.setName("李明");
//        extUserInfo.setMobile("16587658765");
//        extUserInfo.setMinAge("18");
//        extUserInfo.setNeedCheckInfo("F");
//        extUserInfo.setIdentityHash("27bfcd1dee4f22c8fe8a2374af9b660419d1361b1c207e9b41a754a113f38fcc");
//        model.setExtUserInfo(extUserInfo);

        // 设置订单总金额
        model.setTotalAmount(String.valueOf(10));

        // 设置商户传入业务信息
//        model.setBusinessParams("{\"mc_create_trade_ip\":\"127.0.0.1\"}");

        // 设置优惠参数
//        model.setPromoParams("{\"storeIdType\":\"1\"}");

        request.setBizModel(model);
        request.setReturnUrl("http://127.0.0.1:8080/pay/alipay/returnUrl");
//        request.setNotifyUrl(payProperties.getNotifyUrl());
        // 第三方代调用模式下请设置app_auth_token
        // request.putOtherTextParam("app_auth_token", "<-- 请填写应用授权令牌 -->");

        AlipayTradePagePayResponse response = null;
        try {
            response = alipayClient.pageExecute(request, "POST");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        // 如果需要返回GET请求，请使用
        // AlipayTradePagePayResponse response = alipayClient.pageExecute(request, "GET");
        String pageRedirectionData = response.getBody();
        System.out.println(pageRedirectionData);

        if (response.isSuccess()) {
            System.out.println("调用成功");
            return pageRedirectionData;
        } else {
            System.out.println("调用失败");
            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
            // System.out.println(diagnosisUrl);
            return null;
        }

    }



    @Override
    public String pay(AliPayParams aliPayParams) {
        // 初始化SDK
        AlipayClient alipayClient = null;
        try {
            alipayClient = new DefaultAlipayClient(getAlipayConfig());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        // 构造请求参数以调用接口
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        // 设置订单标题
        model.setSubject(aliPayParams.getSubject());
        // 设置请求来源地址
//        model.setRequestFromUrl("https://");
        // 设置产品码
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        // 设置PC扫码支付的方式
        model.setQrPayMode("1");
//        // 设置商户自定义二维码宽度
        model.setQrcodeWidth(100L);
//        // 设置请求后页面的集成方式
        model.setIntegrationType("PCWEB");
        // 设置商户订单号
        model.setOutTradeNo(aliPayParams.getOrderNo());
        // 设置订单总金额
        model.setTotalAmount(aliPayParams.getAmount());
        request.setBizModel(model);
        request.setReturnUrl("http://127.0.0.1:8080/pay/alipay/returnUrl");
//        request.setNotifyUrl(payProperties.getNotifyUrl());
        AlipayTradePagePayResponse response = null;
        try {
            response = alipayClient.pageExecute(request, "POST");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        String pageRedirectionData = response.getBody();
        if (response.isSuccess()) {
            System.out.println("调用成功");
            return pageRedirectionData;
        } else {
            System.out.println("调用失败");
            return null;
        }
    }

    private AlipayConfig getAlipayConfig() {
        AlipayConfig alipayConfig = new AlipayConfig();
        //真的
//        alipayConfig.setAppId(payProperties.getAppId());
//        alipayConfig.setAlipayPublicKey(payProperties.getAlipayPublicKey());
//        alipayConfig.setPrivateKey(payProperties.getPrivateKey());
//        alipayConfig.setFormat(payProperties.getFormat());
//        alipayConfig.setCharset(payProperties.getCharset());
//        alipayConfig.setServerUrl(payProperties.getServerUrl());
//        alipayConfig.setSignType(payProperties.getSignType());


        String privateKey  = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCGJ0BpDDQ5RpfAbPiHMKjDhPR3z/IBvg66pdmmDOleHqdG2PrzGdbVGVp/WrJNjcSy6BagXhAdiQ7cSNkoErOBZoOzA5clSKMqhVXRVnLeGyxVBDHG3alPYZFYI+VEnIYmehtAoCkiFwKYszjBF08Nz2TQDJ4YbAC4F4UWtvtIARhR8nO7eouXvZYtVWKnUCnPitbrecHi3PDlkJ7bgE1nGOnlwzOXuF/2+kv22CUjXXwYJWjGK4+nr2EgqRTS1NJp8DJ1Wnqwi42wTszCoOEw6POKlPL2r+z0ZSpH3jdX+ip1LweouF6Sr5GRUvqQBMnPwtYdwqXhG6xtXmUBvUXDAgMBAAECggEADuPIaGBsY3cMXNU7eHXUJW9aWvn/mGXp6JmD4MRKGqI4Wsq2f8j8aUaZHs2IQMddb6YG7OHD1CNkPNrxD1uUBObF81aQrhiz04JdFhEMSs6lSwJeK/5qTl877JZ/WfAti5O99pxQNHJtXVl3ESdvJM9CFz1vRrKmGciyguToWwNq0avbQuT7cWczWKRmPjYJw+I2Ib7AJXDW0EkCYy5aFgWV0Avgfb7u8A2QRMJUe3Uk/YWEy3PisMMVz0VAeLrxIi4d7EW0BRuEvPDsvAANJhMbFaJSkoxgB34d8dXWL8nqWidV32joxVijRD6s8fEFqIeulIIAE5DDeKGS6N1LUQKBgQDJbQCnVB9UyRvlT5y9MD7Wv/nvByBSha36ZaQQzXYXhIyL3fU+QQm6rs1vgTSU3eQdKDZro8Dhedln+PxY/MgFJmV03eE4AL3OYhWrJTu1NFCeZqNBCDRUawzeDEtg86GwrRyUoaUkg00CYIZT344z9vBFnWm+L/F4g3BGh/FFfQKBgQCqgDIrnKGXA3hY0Q95PXsMLuOuYwQhBAm4BrkB6AEoAWPCapHCCgze5Q0+66hybzolnkQTjc3sButZ2uow9jzhowOXtZzdyutQx91tdc9GqMEd3Ctn2mOSxPVIOOgTgG08MJvYXBNufWv4P5U20lIje5UBrZYNJ1DzLa083imcPwKBgAR0FYSdlofAXo6GYRfJvknvOq5vQOayL/OOVb9NW6QPeTAKe0aiF336KW6is6/q7AU9A18D4trZX9Ytqh6pkB6VB786vKUJVyj+AC6lhxlti67E5C0m6klHiKRML0p4eIW4fh5HKKiTApvf2Hh4Q+OgPRW88j6TPsqEVXIpxUrdAoGAOw5kUk7SeaguCzV+kpQFBtdG7aRGTdlENIW/lzVxERZKI5EeI4OvqZTU3bJqihiUn2sOUWeQp+xNoLX+4C6qL81y9R1m1Dvw/e38eGmR/ft9yIX2UuaGDtRB0V5EpFybeXmswWhDIsY2m6oKWQ7IfJnVptslWjEADPsJQkgBSakCgYEAr9YW/jxzlSk6+476sEjfIiEoxLlB6PRlPa+KdsGKESABGeDhq+7lJvAG+8aHQ7B0+MsSok6shyGOmGd5bK4vNu/tcJUvqu8svidlNZ3yLC3SfOTN9IjeGLAjHKOxkj+OSHAERMD2pwt8xl2opVB+pmCe02j06JGZQ4kNlWTHEIg=";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2nE6SCwuzE30mpihAOrXZWE6UP4wTA7IOsEVkY2WKCNhn9H1gngyCy5bHuTQTkBSicmMEKZwh+SEV1pQzOfb7CiXyVyZNz/eC0MueWW1j1DFlXkxt3BMp7xCRhkYRTzX5Mmg0J8ShQRVri1xECs86FT1jA1+w34qwKVtz7iAX6VSiZ8NyM7pfKZeVTYwy4B5Pj1mLFlPbZXKUIwO7qACwCCSFT1cA1F6LtXWqB1pUWpBqcX+AEueLSa2d0egCDbAGcHZxj2TbxuazhSLFSaNDQsVvGrxChx+3wNU43BPuos5QwhX162AweyhHVDtdqacJAQMc58E45PR5rzJYi4rFwIDAQAB";
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        alipayConfig.setAppId("9021000133641194");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF-8");
        alipayConfig.setSignType("RSA2");
        return alipayConfig;
    }
}
