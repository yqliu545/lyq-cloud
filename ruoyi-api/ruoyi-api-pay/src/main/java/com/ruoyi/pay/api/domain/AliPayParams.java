package com.ruoyi.pay.api.domain;

public class AliPayParams {
    private String amount;
    private String subject;
    private String orderNo;

    public AliPayParams() {
    }

    public AliPayParams(String amount, String subject, String orderNo) {
        this.amount = amount;
        this.subject = subject;
        this.orderNo = orderNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
