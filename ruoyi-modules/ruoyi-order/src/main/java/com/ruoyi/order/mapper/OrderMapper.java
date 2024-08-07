package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.Order;
import com.ruoyi.order.domain.PayInfo;

/**
 * 订单Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-07
 */
public interface OrderMapper 
{
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    public Order selectOrderById(String id);

    /**
     * 查询订单列表
     * 
     * @param order 订单
     * @return 订单集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 新增订单
     * 
     * @param order 订单
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改订单
     * 
     * @param order 订单
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 删除订单
     * 
     * @param id 订单主键
     * @return 结果
     */
    public int deleteOrderById(String id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderByIds(String[] ids);

    /**
     * 批量删除支付信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayInfoByOrderNos(String[] ids);
    
    /**
     * 批量新增支付信息
     * 
     * @param payInfoList 支付信息列表
     * @return 结果
     */
    public int batchPayInfo(List<PayInfo> payInfoList);
    

    /**
     * 通过订单主键删除支付信息信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deletePayInfoByOrderNo(String id);
}
