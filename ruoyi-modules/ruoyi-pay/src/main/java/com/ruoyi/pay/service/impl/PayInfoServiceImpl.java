package com.ruoyi.pay.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pay.mapper.PayInfoMapper;
import com.ruoyi.pay.domain.PayInfo;
import com.ruoyi.pay.service.IPayInfoService;

/**
 * 支付信息Service业务层处理
 * 
 * @author lyq
 * @date 2024-08-07
 */
@Service
public class PayInfoServiceImpl implements IPayInfoService 
{
    @Autowired
    private PayInfoMapper payInfoMapper;

    /**
     * 查询支付信息
     * 
     * @param id 支付信息主键
     * @return 支付信息
     */
    @Override
    public PayInfo selectPayInfoById(Long id)
    {
        return payInfoMapper.selectPayInfoById(id);
    }

    /**
     * 查询支付信息列表
     * 
     * @param payInfo 支付信息
     * @return 支付信息
     */
    @Override
    public List<PayInfo> selectPayInfoList(PayInfo payInfo)
    {
        return payInfoMapper.selectPayInfoList(payInfo);
    }

    /**
     * 新增支付信息
     * 
     * @param payInfo 支付信息
     * @return 结果
     */
    @Override
    public int insertPayInfo(PayInfo payInfo)
    {
        payInfo.setCreateTime(DateUtils.getNowDate());
        return payInfoMapper.insertPayInfo(payInfo);
    }

    /**
     * 修改支付信息
     * 
     * @param payInfo 支付信息
     * @return 结果
     */
    @Override
    public int updatePayInfo(PayInfo payInfo)
    {
        payInfo.setUpdateTime(DateUtils.getNowDate());
        return payInfoMapper.updatePayInfo(payInfo);
    }

    /**
     * 批量删除支付信息
     * 
     * @param ids 需要删除的支付信息主键
     * @return 结果
     */
    @Override
    public int deletePayInfoByIds(Long[] ids)
    {
        return payInfoMapper.deletePayInfoByIds(ids);
    }

    /**
     * 删除支付信息信息
     * 
     * @param id 支付信息主键
     * @return 结果
     */
    @Override
    public int deletePayInfoById(Long id)
    {
        return payInfoMapper.deletePayInfoById(id);
    }
}
