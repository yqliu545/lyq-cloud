package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.core.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.order.domain.PayInfo;
import com.ruoyi.order.mapper.OrderMapper;
import com.ruoyi.order.domain.Order;
import com.ruoyi.order.service.IOrderService;

/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-07
 */
@Service
public class OrderServiceImpl implements IOrderService 
{
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public Order selectOrderById(String id)
    {
        return orderMapper.selectOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param order 订单
     * @return 订单
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增订单
     * 
     * @param order 订单
     * @return 结果
     */
    @Transactional
    @Override
    public int insertOrder(Order order)
    {
        order.setCreateTime(DateUtils.getNowDate());
        int rows = orderMapper.insertOrder(order);
        insertPayInfo(order);
        return rows;
    }

    /**
     * 修改订单
     * 
     * @param order 订单
     * @return 结果
     */
    @Transactional
    @Override
    public int updateOrder(Order order)
    {
        order.setUpdateTime(DateUtils.getNowDate());
        orderMapper.deletePayInfoByOrderNo(order.getId());
        insertPayInfo(order);
        return orderMapper.updateOrder(order);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteOrderByIds(String[] ids)
    {
        orderMapper.deletePayInfoByOrderNos(ids);
        return orderMapper.deleteOrderByIds(ids);
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteOrderById(String id)
    {
        orderMapper.deletePayInfoByOrderNo(id);
        return orderMapper.deleteOrderById(id);
    }

    /**
     * 新增支付信息信息
     * 
     * @param order 订单对象
     */
    public void insertPayInfo(Order order)
    {
        List<PayInfo> payInfoList = order.getPayInfoList();
        String id = order.getId();
        if (StringUtils.isNotNull(payInfoList))
        {
            List<PayInfo> list = new ArrayList<PayInfo>();
            for (PayInfo payInfo : payInfoList)
            {
                payInfo.setOrderNo(id);
                list.add(payInfo);
            }
            if (list.size() > 0)
            {
                orderMapper.batchPayInfo(list);
            }
        }
    }
}
