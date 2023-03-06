package com.softeem.crm.mapper;

import com.softeem.crm.dto.TreeDto;
import com.softeem.crm.pojo.Module;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author wangw
 * @description 针对表【t_module】的数据库操作Mapper
 * @createDate 2022-12-27 14:22:59
 * @Entity com.softeem.crm.pojo.Module
 */
public interface ModuleMapper extends BaseMapper<Module> {
    public List<TreeDto> queryAllModules();
}




