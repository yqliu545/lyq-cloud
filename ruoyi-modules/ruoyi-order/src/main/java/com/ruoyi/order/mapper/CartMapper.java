package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.Cart;

/**
 * 购物车Mapper接口
 * 
 * @author lyq
 * @date 2024-08-10
 */
public interface CartMapper 
{
    /**
     * 查询购物车
     * 
     * @param id 购物车主键
     * @return 购物车
     */
    public Cart selectCartById(Long id);

    /**
     * 查询购物车列表
     * 
     * @param cart 购物车
     * @return 购物车集合
     */
    public List<Cart> selectCartList(Cart cart);

    /**
     * 新增购物车
     * 
     * @param cart 购物车
     * @return 结果
     */
    public int insertCart(Cart cart);

    /**
     * 修改购物车
     * 
     * @param cart 购物车
     * @return 结果
     */
    public int updateCart(Cart cart);

    /**
     * 删除购物车
     * 
     * @param id 购物车主键
     * @return 结果
     */
    public int deleteCartById(Long id);

    /**
     * 批量删除购物车
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCartByIds(Long[] ids);
}
