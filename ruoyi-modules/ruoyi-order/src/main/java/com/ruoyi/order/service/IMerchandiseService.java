package com.ruoyi.order.service;

import java.util.List;
import com.ruoyi.order.domain.Merchandise;

/**
 * 商品Service接口
 * 
 * @author lyq
 * @date 2024-08-07
 */
public interface IMerchandiseService 
{
    /**
     * 查询商品
     * 
     * @param merchandiseId 商品主键
     * @return 商品
     */
    public Merchandise selectMerchandiseByMerchandiseId(Long merchandiseId);

    /**
     * 查询商品列表
     * 
     * @param merchandise 商品
     * @return 商品集合
     */
    public List<Merchandise> selectMerchandiseList(Merchandise merchandise);

    /**
     * 新增商品
     * 
     * @param merchandise 商品
     * @return 结果
     */
    public int insertMerchandise(Merchandise merchandise);

    /**
     * 修改商品
     * 
     * @param merchandise 商品
     * @return 结果
     */
    public int updateMerchandise(Merchandise merchandise);

    /**
     * 批量删除商品
     * 
     * @param merchandiseIds 需要删除的商品主键集合
     * @return 结果
     */
    public int deleteMerchandiseByMerchandiseIds(Long[] merchandiseIds);

    /**
     * 删除商品信息
     * 
     * @param merchandiseId 商品主键
     * @return 结果
     */
    public int deleteMerchandiseByMerchandiseId(Long merchandiseId);
}
