package com.ruoyi.pay.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.pay.domain.PayInfo;
import com.ruoyi.pay.service.IPayInfoService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 支付信息Controller
 * 
 * @author lyq
 * @date 2024-08-07
 */
@RestController
@RequestMapping("/pay_info")
public class PayInfoController extends BaseController
{
    @Autowired
    private IPayInfoService payInfoService;

    /**
     * 查询支付信息列表
     */
    @RequiresPermissions("pay:pay_info:list")
    @GetMapping("/list")
    public TableDataInfo list(PayInfo payInfo)
    {
        startPage();
        List<PayInfo> list = payInfoService.selectPayInfoList(payInfo);
        return getDataTable(list);
    }

    /**
     * 导出支付信息列表
     */
    @RequiresPermissions("pay:pay_info:export")
    @Log(title = "支付信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PayInfo payInfo)
    {
        List<PayInfo> list = payInfoService.selectPayInfoList(payInfo);
        ExcelUtil<PayInfo> util = new ExcelUtil<PayInfo>(PayInfo.class);
        util.exportExcel(response, list, "支付信息数据");
    }

    /**
     * 获取支付信息详细信息
     */
    @RequiresPermissions("pay:pay_info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(payInfoService.selectPayInfoById(id));
    }

    /**
     * 新增支付信息
     */
    @RequiresPermissions("pay:pay_info:add")
    @Log(title = "支付信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayInfo payInfo)
    {
        return toAjax(payInfoService.insertPayInfo(payInfo));
    }

    /**
     * 修改支付信息
     */
    @RequiresPermissions("pay:pay_info:edit")
    @Log(title = "支付信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayInfo payInfo)
    {
        return toAjax(payInfoService.updatePayInfo(payInfo));
    }

    /**
     * 删除支付信息
     */
    @RequiresPermissions("pay:pay_info:remove")
    @Log(title = "支付信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(payInfoService.deletePayInfoByIds(ids));
    }
}
