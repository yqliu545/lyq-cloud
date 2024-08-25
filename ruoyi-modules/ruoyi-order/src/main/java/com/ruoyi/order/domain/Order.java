package com.ruoyi.order.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 订单对象 order
 *
 * @author ruoyi
 * @date 2024-08-07
 */
public class Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** （主键） */
    private String id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 商品id */
    @Excel(name = "商品id")
    private Long merchandiseId;

    /** 购买人id */
    @Excel(name = "购买人id")
    private Long userId;

    /** 购买数量 */
    @Excel(name = "购买数量")
    private Integer numbers;

    /** 实际消费金额 */
    @Excel(name = "实际消费金额")
    private BigDecimal payment;

    /** 是否支付1.已支付，0未支付,-1订单取消，2交易成功 */
    @Excel(name = "是否支付1.已支付，0未支付,-1订单取消，2交易成功")
    private String status;

    /** 支付类型 1在线支付 */
    @Excel(name = "支付类型 1在线支付")
    private String paymentType;

    /** 交易完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;


    private String iphone;

    /** 支付信息信息 */
    private List<PayInfo> payInfoList;

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo()
    {
        return orderNo;
    }
    public void setMerchandiseId(Long merchandiseId)
    {
        this.merchandiseId = merchandiseId;
    }

    public Long getMerchandiseId()
    {
        return merchandiseId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setNumbers(Integer number)
    {
        this.numbers = number;
    }

    public Integer getNumbers() {
        return numbers;
    }
    public void setPayment(BigDecimal payment)
    {
        this.payment = payment;
    }

    public BigDecimal getPayment()
    {
        return payment;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setPaymentType(String paymentType)
    {
        this.paymentType = paymentType;
    }

    public String getPaymentType()
    {
        return paymentType;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setPaymentTime(Date paymentTime)
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime()
    {
        return paymentTime;
    }

    public List<PayInfo> getPayInfoList()
    {
        return payInfoList;
    }

    public void setPayInfoList(List<PayInfo> payInfoList)
    {
        this.payInfoList = payInfoList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNo", getOrderNo())
            .append("merchandiseId", getMerchandiseId())
            .append("userId", getUserId())
            .append("number", getNumbers())
            .append("payment", getPayment())
            .append("status", getStatus())
            .append("paymentType", getPaymentType())
            .append("endTime", getEndTime())
            .append("paymentTime", getPaymentTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("payInfoList", getPayInfoList())
            .toString();
    }
}
