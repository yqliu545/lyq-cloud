package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysSocial;

/**
 * 社会化关系Service接口
 * 
 * @author lyq
 * @date 2024-08-07
 */
public interface ISysSocialService 
{
    /**
     * 查询社会化关系
     * 
     * @param id 社会化关系主键
     * @return 社会化关系
     */
    public SysSocial selectSysSocialById(Long id);

    /**
     * 查询社会化关系列表
     * 
     * @param sysSocial 社会化关系
     * @return 社会化关系集合
     */
    public List<SysSocial> selectSysSocialList(SysSocial sysSocial);

    /**
     * 新增社会化关系
     * 
     * @param sysSocial 社会化关系
     * @return 结果
     */
    public int insertSysSocial(SysSocial sysSocial);

    /**
     * 修改社会化关系
     * 
     * @param sysSocial 社会化关系
     * @return 结果
     */
    public int updateSysSocial(SysSocial sysSocial);

    /**
     * 批量删除社会化关系
     * 
     * @param ids 需要删除的社会化关系主键集合
     * @return 结果
     */
    public int deleteSysSocialByIds(Long[] ids);

    /**
     * 删除社会化关系信息
     * 
     * @param id 社会化关系主键
     * @return 结果
     */
    public int deleteSysSocialById(Long id);
}
