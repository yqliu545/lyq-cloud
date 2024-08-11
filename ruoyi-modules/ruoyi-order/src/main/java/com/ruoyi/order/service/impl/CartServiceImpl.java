package com.ruoyi.order.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.CartMapper;
import com.ruoyi.order.domain.Cart;
import com.ruoyi.order.service.ICartService;

/**
 * 购物车Service业务层处理
 * 
 * @author lyq
 * @date 2024-08-10
 */
@Service
public class CartServiceImpl implements ICartService 
{
    @Autowired
    private CartMapper cartMapper;

    /**
     * 查询购物车
     * 
     * @param id 购物车主键
     * @return 购物车
     */
    @Override
    public Cart selectCartById(Long id)
    {
        return cartMapper.selectCartById(id);
    }

    /**
     * 查询购物车列表
     * 
     * @param cart 购物车
     * @return 购物车
     */
    @Override
    public List<Cart> selectCartList(Cart cart)
    {
        return cartMapper.selectCartList(cart);
    }

    /**
     * 新增购物车
     * 
     * @param cart 购物车
     * @return 结果
     */
    @Override
    public int insertCart(Cart cart)
    {
        return cartMapper.insertCart(cart);
    }

    /**
     * 修改购物车
     * 
     * @param cart 购物车
     * @return 结果
     */
    @Override
    public int updateCart(Cart cart)
    {
        return cartMapper.updateCart(cart);
    }

    /**
     * 批量删除购物车
     * 
     * @param ids 需要删除的购物车主键
     * @return 结果
     */
    @Override
    public int deleteCartByIds(Long[] ids)
    {
        return cartMapper.deleteCartByIds(ids);
    }

    /**
     * 删除购物车信息
     * 
     * @param id 购物车主键
     * @return 结果
     */
    @Override
    public int deleteCartById(Long id)
    {
        return cartMapper.deleteCartById(id);
    }
}
