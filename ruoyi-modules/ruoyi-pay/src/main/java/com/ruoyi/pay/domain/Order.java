package com.ruoyi.pay.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class Order extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String merchandiseId;
    private Double sumPrice;
    private Integer number;
    private Double price;
}
