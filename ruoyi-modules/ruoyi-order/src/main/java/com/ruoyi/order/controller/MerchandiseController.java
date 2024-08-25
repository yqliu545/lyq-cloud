package com.ruoyi.order.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.order.domain.Merchandise;
import com.ruoyi.order.service.IMerchandiseService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 商品Controller
 *
 * @author lyq
 * @date 2024-08-07
 */
@RestController
@RequestMapping("/merchandise")
public class MerchandiseController extends BaseController
{
    @Autowired
    private IMerchandiseService merchandiseService;

    /**
     * 查询商品列表
     */
    @RequiresPermissions("order:merchandise:list")
    @GetMapping("/list")
    public TableDataInfo list(Merchandise merchandise)
    {
        startPage();
        List<Merchandise> list = merchandiseService.selectMerchandiseList(merchandise);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @RequiresPermissions("order:merchandise:export")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Merchandise merchandise)
    {
        List<Merchandise> list = merchandiseService.selectMerchandiseList(merchandise);
        ExcelUtil<Merchandise> util = new ExcelUtil<Merchandise>(Merchandise.class);
        util.exportExcel(response, list, "商品数据");
    }

    /**
     * 获取商品详细信息
     */
    @RequiresPermissions("order:merchandise:query")
    @GetMapping(value = "/{merchandiseId}")
    public AjaxResult getInfo(@PathVariable("merchandiseId") Long merchandiseId)
    {
        return success(merchandiseService.selectMerchandiseByMerchandiseId(merchandiseId));
    }

    /**
     * 新增商品
     */
    @RequiresPermissions("order:merchandise:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Merchandise merchandise)
    {
        return toAjax(merchandiseService.insertMerchandise(merchandise));
    }

    /**
     * 修改商品
     */
    @RequiresPermissions("order:merchandise:edit")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Merchandise merchandise)
    {
        return toAjax(merchandiseService.updateMerchandise(merchandise));
    }

    /**
     * 删除商品
     */
    @RequiresPermissions("order:merchandise:remove")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{merchandiseIds}")
    public AjaxResult remove(@PathVariable Long[] merchandiseIds)
    {
        return toAjax(merchandiseService.deleteMerchandiseByMerchandiseIds(merchandiseIds));
    }

    @GetMapping("/getMerchandise/{merchandiseId}")
    R<Merchandise> selectMerchandiseById(@PathVariable("merchandiseId") Long merchandiseId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source){
        return R.ok(merchandiseService.selectMerchandiseByMerchandiseId(merchandiseId));
    }
}
