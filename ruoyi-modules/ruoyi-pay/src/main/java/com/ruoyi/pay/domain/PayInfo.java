package com.ruoyi.pay.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 支付信息对象 pay_info
 *
 * @author lyq
 * @date 2024-08-07
 */
public class PayInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    private String sellerId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 支付平台1支付宝2微信 */
    @Excel(name = "支付平台1支付宝2微信")
    private String payPlatform;

    /** 支付流水号 */
    @Excel(name = "支付流水号")
    private String tradeNo;

    /** 支付状态0未支付1已支付 */
    @Excel(name = "支付状态0未支付1已支付")
    private String status;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal payAmount;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo()
    {
        return orderNo;
    }
    public void setPayPlatform(String payPlatform)
    {
        this.payPlatform = payPlatform;
    }

    public String getPayPlatform()
    {
        return payPlatform;
    }
    public void setTradeNo(String tradeNo)
    {
        this.tradeNo = tradeNo;
    }

    public String getTradeNo()
    {
        return tradeNo;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setPayAmount(BigDecimal payAmount)
    {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount()
    {
        return payAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("orderNo", getOrderNo())
            .append("payPlatform", getPayPlatform())
            .append("tradeNo", getTradeNo())
            .append("status", getStatus())
            .append("payAmount", getPayAmount())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
