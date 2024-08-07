package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.SysSocial;
import com.ruoyi.system.service.ISysSocialService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 社会化关系Controller
 * 
 * @author lyq
 * @date 2024-08-07
 */
@RestController
@RequestMapping("/social")
public class SysSocialController extends BaseController
{
    @Autowired
    private ISysSocialService sysSocialService;

    /**
     * 查询社会化关系列表
     */
    @RequiresPermissions("system:social:list")
    @GetMapping("/list")
    public TableDataInfo list(SysSocial sysSocial)
    {
        startPage();
        List<SysSocial> list = sysSocialService.selectSysSocialList(sysSocial);
        return getDataTable(list);
    }

    /**
     * 导出社会化关系列表
     */
    @RequiresPermissions("system:social:export")
    @Log(title = "社会化关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSocial sysSocial)
    {
        List<SysSocial> list = sysSocialService.selectSysSocialList(sysSocial);
        ExcelUtil<SysSocial> util = new ExcelUtil<SysSocial>(SysSocial.class);
        util.exportExcel(response, list, "社会化关系数据");
    }

    /**
     * 获取社会化关系详细信息
     */
    @RequiresPermissions("system:social:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysSocialService.selectSysSocialById(id));
    }

    /**
     * 新增社会化关系
     */
    @RequiresPermissions("system:social:add")
    @Log(title = "社会化关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSocial sysSocial)
    {
        return toAjax(sysSocialService.insertSysSocial(sysSocial));
    }

    /**
     * 修改社会化关系
     */
    @RequiresPermissions("system:social:edit")
    @Log(title = "社会化关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSocial sysSocial)
    {
        return toAjax(sysSocialService.updateSysSocial(sysSocial));
    }

    /**
     * 删除社会化关系
     */
    @RequiresPermissions("system:social:remove")
    @Log(title = "社会化关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysSocialService.deleteSysSocialByIds(ids));
    }
}
