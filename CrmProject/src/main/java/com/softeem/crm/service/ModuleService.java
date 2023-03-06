package com.softeem.crm.service;

import com.softeem.crm.dto.TreeDto;
import com.softeem.crm.pojo.Module;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_module】的数据库操作Service
 * @createDate 2022-12-27 14:22:59
 */
public interface ModuleService extends IService<Module> {
    public List<TreeDto> queryAllModules();

    public List<TreeDto> queryAllModules02(Integer roleId);

    public Map<String, Object> moduleList();

    public void saveModule(Module module);

    public void updateModule(Module module);

    List<Map<String, Object>> queryAllModulesByGrade(Integer grade);

    public void deleteModuleById(Integer mid);
}
