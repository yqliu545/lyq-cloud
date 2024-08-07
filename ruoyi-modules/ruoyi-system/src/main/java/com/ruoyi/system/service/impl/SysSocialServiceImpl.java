package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysSocialMapper;
import com.ruoyi.system.domain.SysSocial;
import com.ruoyi.system.service.ISysSocialService;

/**
 * 社会化关系Service业务层处理
 * 
 * @author lyq
 * @date 2024-08-07
 */
@Service
public class SysSocialServiceImpl implements ISysSocialService 
{
    @Autowired
    private SysSocialMapper sysSocialMapper;

    /**
     * 查询社会化关系
     * 
     * @param id 社会化关系主键
     * @return 社会化关系
     */
    @Override
    public SysSocial selectSysSocialById(Long id)
    {
        return sysSocialMapper.selectSysSocialById(id);
    }

    /**
     * 查询社会化关系列表
     * 
     * @param sysSocial 社会化关系
     * @return 社会化关系
     */
    @Override
    public List<SysSocial> selectSysSocialList(SysSocial sysSocial)
    {
        return sysSocialMapper.selectSysSocialList(sysSocial);
    }

    /**
     * 新增社会化关系
     * 
     * @param sysSocial 社会化关系
     * @return 结果
     */
    @Override
    public int insertSysSocial(SysSocial sysSocial)
    {
        sysSocial.setCreateTime(DateUtils.getNowDate());
        return sysSocialMapper.insertSysSocial(sysSocial);
    }

    /**
     * 修改社会化关系
     * 
     * @param sysSocial 社会化关系
     * @return 结果
     */
    @Override
    public int updateSysSocial(SysSocial sysSocial)
    {
        sysSocial.setUpdateTime(DateUtils.getNowDate());
        return sysSocialMapper.updateSysSocial(sysSocial);
    }

    /**
     * 批量删除社会化关系
     * 
     * @param ids 需要删除的社会化关系主键
     * @return 结果
     */
    @Override
    public int deleteSysSocialByIds(Long[] ids)
    {
        return sysSocialMapper.deleteSysSocialByIds(ids);
    }

    /**
     * 删除社会化关系信息
     * 
     * @param id 社会化关系主键
     * @return 结果
     */
    @Override
    public int deleteSysSocialById(Long id)
    {
        return sysSocialMapper.deleteSysSocialById(id);
    }
}
