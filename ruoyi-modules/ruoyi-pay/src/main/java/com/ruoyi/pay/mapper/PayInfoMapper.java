package com.ruoyi.pay.mapper;

import java.util.List;
import com.ruoyi.pay.domain.PayInfo;

/**
 * 支付信息Mapper接口
 * 
 * @author lyq
 * @date 2024-08-07
 */
public interface PayInfoMapper 
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
     * 删除支付信息
     * 
     * @param id 支付信息主键
     * @return 结果
     */
    public int deletePayInfoById(Long id);

    /**
     * 批量删除支付信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayInfoByIds(Long[] ids);
}
