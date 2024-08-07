package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.MerchandiseMapper;
import com.ruoyi.order.domain.Merchandise;
import com.ruoyi.order.service.IMerchandiseService;

/**
 * 商品Service业务层处理
 * 
 * @author lyq
 * @date 2024-08-07
 */
@Service
public class MerchandiseServiceImpl implements IMerchandiseService 
{
    @Autowired
    private MerchandiseMapper merchandiseMapper;

    /**
     * 查询商品
     * 
     * @param merchandiseId 商品主键
     * @return 商品
     */
    @Override
    public Merchandise selectMerchandiseByMerchandiseId(Long merchandiseId)
    {
        return merchandiseMapper.selectMerchandiseByMerchandiseId(merchandiseId);
    }

    /**
     * 查询商品列表
     * 
     * @param merchandise 商品
     * @return 商品
     */
    @Override
    public List<Merchandise> selectMerchandiseList(Merchandise merchandise)
    {
        return merchandiseMapper.selectMerchandiseList(merchandise);
    }

    /**
     * 新增商品
     * 
     * @param merchandise 商品
     * @return 结果
     */
    @Override
    public int insertMerchandise(Merchandise merchandise)
    {
        merchandise.setCreateTime(DateUtils.getNowDate());
        return merchandiseMapper.insertMerchandise(merchandise);
    }

    /**
     * 修改商品
     * 
     * @param merchandise 商品
     * @return 结果
     */
    @Override
    public int updateMerchandise(Merchandise merchandise)
    {
        merchandise.setUpdateTime(DateUtils.getNowDate());
        return merchandiseMapper.updateMerchandise(merchandise);
    }

    /**
     * 批量删除商品
     * 
     * @param merchandiseIds 需要删除的商品主键
     * @return 结果
     */
    @Override
    public int deleteMerchandiseByMerchandiseIds(Long[] merchandiseIds)
    {
        return merchandiseMapper.deleteMerchandiseByMerchandiseIds(merchandiseIds);
    }

    /**
     * 删除商品信息
     * 
     * @param merchandiseId 商品主键
     * @return 结果
     */
    @Override
    public int deleteMerchandiseByMerchandiseId(Long merchandiseId)
    {
        return merchandiseMapper.deleteMerchandiseByMerchandiseId(merchandiseId);
    }
}
