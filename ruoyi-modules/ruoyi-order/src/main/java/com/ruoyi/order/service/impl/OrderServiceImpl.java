package com.ruoyi.order.service.impl;

import java.util.List;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.order.domain.Merchandise;
import com.ruoyi.pay.api.AliPayServiceFeign;
import com.ruoyi.pay.api.domain.AliPayParams;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
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

    @Autowired
    private MerchandiseServiceImpl merchandiseService;

    @Autowired
    private AliPayServiceFeign aliPayServiceFeign;

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
     * 主页新增订单
     * @param order
     * @return
     */
    @Transactional
    @Override
    public String makeOrder(Order order) {
        order.setCreateTime(DateUtils.getNowDate());
        order.setOrderNo(getOrderNo());

        orderMapper.insertOrder(order);
        Merchandise merchandise = merchandiseService.selectMerchandiseByMerchandiseId(order.getMerchandiseId());
        AliPayParams aliPayParams = new AliPayParams(order.getPayment().toString(), merchandise.getName(), order.getOrderNo());
        //调用支付宝支付
        R<String> form=aliPayServiceFeign.pay(aliPayParams, SecurityConstants.INNER);
        return form.getData();
    }

    @Override
    public Boolean handleOrderStatus(String orderNo) {
        //查询订单
        Order order=orderMapper.selectOrderByOrderNo(orderNo);
        //修改状态
        order.setStatus("1");
        int i = orderMapper.updateOrder(order);
        if (i>0){
            return true;
        }
        return false;
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

    public static  synchronized String getOrderNo(){
        String nowdate = DateUtils.parseDateToStr("yyyyMMddHHmmssSSS", DateUtils.getNowDate());
        String randomNumeric = RandomStringUtils.randomNumeric(3);
        return nowdate+randomNumeric;
    }
}
