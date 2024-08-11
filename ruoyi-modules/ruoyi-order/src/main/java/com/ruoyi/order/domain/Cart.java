package com.ruoyi.order.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 购物车对象 t_cart
 * 
 * @author lyq
 * @date 2024-08-10
 */
public class Cart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品id */
    @Excel(name = "商品id")
    private Long merchandiseId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 购买数量 */
    @Excel(name = "购买数量")
    private Integer num;

    /** 添加数量 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加数量", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setNum(Integer num) 
    {
        this.num = num;
    }

    public Integer getNum() 
    {
        return num;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merchandiseId", getMerchandiseId())
            .append("userId", getUserId())
            .append("num", getNum())
            .append("time", getTime())
            .toString();
    }
}
