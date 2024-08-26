package com.ruoyi.order.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.security.annotation.InnerAuth;
import com.ruoyi.order.domain.Merchandise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.order.domain.Order;
import com.ruoyi.order.service.IOrderService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 订单Controller
 *
 * @author ruoyi
 * @date 2024-08-07
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController
{
    @Autowired
    private IOrderService orderService;

    /**
     * 查询订单列表
     */
    @RequiresPermissions("order:order:list")
    @GetMapping("/list")
    public TableDataInfo list(Order order)
    {
        startPage();
        List<Order> list = orderService.selectOrderList(order);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @RequiresPermissions("order:order:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Order order)
    {
        List<Order> list = orderService.selectOrderList(order);
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        util.exportExcel(response, list, "订单数据");
    }

    /**
     * 获取订单详细信息
     */
    @RequiresPermissions("order:order:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(orderService.selectOrderById(id));
    }

    /**
     * 新增订单
     */
    @RequiresPermissions("order:order:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Order order)
    {
        return toAjax(orderService.insertOrder(order));
    }

    /**
     * 修改订单
     */
    @RequiresPermissions("order:order:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Order order)
    {
        return toAjax(orderService.updateOrder(order));
    }

    /**
     * 删除订单
     */
    @RequiresPermissions("order:order:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(orderService.deleteOrderByIds(ids));
    }


    //下单
    @PostMapping("/makeOrder")
    public R<String> makeOrder(@RequestBody Order order, HttpServletResponse response) throws IOException {
        return R.ok(orderService.makeOrder(order));
    }

    //收到支付成功后修改订单状态
    @InnerAuth
    @GetMapping("/updateStatus/{orderNo}/{status}")
    public R<Boolean> updateOrderStatus(@PathVariable("orderNo") String orderNo,@PathVariable("status") String status){
        return R.ok(orderService.handleOrderStatus(orderNo,status));
    }

    @GetMapping("/getOrder/{orderNo}")
    public R<Order> selectOrderByOrderNo(@PathVariable("orderNo")String orderNo, @RequestHeader(SecurityConstants.FROM_SOURCE) String source){
        return R.ok(orderService.selectOrderByOrderNo(orderNo));
    }

}
