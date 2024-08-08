package com.ruoyi.order.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 商品对象 merchandise
 *
 * @author lyq
 * @date 2024-08-07
 */
public class Merchandise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long merchandiseId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 商品类型 */
    @Excel(name = "商品类型")
    private String type;

    /** 商品分类id */
    @Excel(name = "商品分类id")
    private Long categoryId;

    /** 商品属性 */
    @Excel(name = "商品属性")
    private String shuxing;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 商品状态1在售2下架 */
    @Excel(name = "商品状态1在售2下架")
    private String status;

    /** 数量（库存） */
    @Excel(name = "数量", readConverterExp = "库=存")
    private Long stock;

    /** 主图地址 */
    @Excel(name = "主图地址")
    private String mainImage;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    public void setMerchandiseId(Long merchandiseId)
    {
        this.merchandiseId = merchandiseId;
    }

    public Long getMerchandiseId()
    {
        return merchandiseId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }
    public void setAttribute(String attribute)
    {this.shuxing = attribute;}

    public String getAttribute()
    {
        return shuxing;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setStock(Long stock)
    {
        this.stock = stock;
    }

    public Long getStock()
    {
        return stock;
    }
    public void setMainImage(String mainImage)
    {
        this.mainImage = mainImage;
    }

    public String getMainImage()
    {
        return mainImage;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("merchandiseId", getMerchandiseId())
            .append("name", getName())
            .append("type", getType())
            .append("categoryId", getCategoryId())
            .append(" attribute", getAttribute())
            .append("price", getPrice())
            .append("status", getStatus())
            .append("stock", getStock())
            .append("mainImage", getMainImage())
            .append("description", getDescription())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
