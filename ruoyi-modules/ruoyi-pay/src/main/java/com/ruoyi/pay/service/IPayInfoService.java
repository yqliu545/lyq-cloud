package com.ruoyi.pay.service;

import java.util.List;
import com.ruoyi.pay.domain.PayInfo;

/**
 * 支付信息Service接口
 * 
 * @author lyq
 * @date 2024-08-07
 */
public interface IPayInfoService 
{
    /**
     * 查询支付信息
     * 
     * @param id 支付信息主键
     * @return 支付信息
     */
    public PayInfo selectPayInfoById(Long id);

    /**
     * 查询支付信息列表
     * 
     * @param payInfo 支付信息
     * @return 支付信息集合
     */
    public List<PayInfo> selectPayInfoList(PayInfo payInfo);

    /**
     * 新增支付信息
     * 
     * @param payInfo 支付信息
     * @return 结果
     */
    public int insertPayInfo(PayInfo payInfo);

    /**
     * 修改支付信息
     * 
     * @param payInfo 支付信息
     * @return 结果
     */
    public int updatePayInfo(PayInfo payInfo);

    /**
     * 批量删除支付信息
     * 
     * @param ids 需要删除的支付信息主键集合
     * @return 结果
     */
    public int deletePayInfoByIds(Long[] ids);

    /**
     * 删除支付信息信息
     * 
     * @param id 支付信息主键
     * @return 结果
     */
    public int deletePayInfoById(Long id);
}
