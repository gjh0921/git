package com.softeem.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.softeem.constant.RedisConstant;
import com.softeem.dao.SetmealDao;
import com.softeem.entity.PageResult;
import com.softeem.pojo.Setmeal;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${out_put_path}")//从属性文件读取输出目录的路径
    private String outputpath ;
    //生成静态页面
    public void generateMobileStaticHtml() {
        //准备模板文件中所需的数据
        List<Setmeal> setmealList = this.findAll();
        //生成套餐列表静态页面
        generateMobileSetmealListHtml(setmealList);
        //生成套餐详情静态页面（多个）
        generateMobileSetmealDetailHtml(setmealList);
    }

    //生成套餐列表静态页面
    public void generateMobileSetmealListHtml(List<Setmeal> setmealList) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("setmealList", setmealList);
        this.generateHtml("mobile_setmeal.ftl","m_setmeal.html",dataMap);
    }

    //生成套餐详情静态页面（多个）
    public void generateMobileSetmealDetailHtml(List<Setmeal> setmealList) {
        for (Setmeal setmeal : setmealList) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("setmeal", this.findBySetmealId(setmeal.getId()));
            this.generateHtml("mobile_setmeal_detail.ftl",
                    "setmeal_detail_"+setmeal.getId()+".html",
                    dataMap);
        }
    }

    //生成html网页方法
    public void generateHtml(String templateName,String htmlPageName,Map<String, Object> dataMap){
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Writer out = null;
        try {
            // 加载模版文件
            Template template = configuration.getTemplate(templateName);
            // 生成数据
            //“C:\Users/86187/IideaProjects/health_parent_gjh/health_mobile/src/main/webapp/pages+"\\"+xxx.html
            File docFile = new File(outputpath + "\\" + htmlPageName);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // 输出文件
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override
    public List<Map<String, Object>> findSetmealCount() {
        return setmealDao.findSetmealCount();
    }
    //将图片名称保存到Redis
    private void savePic2Redis(String pic){
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pic);
    }
    //新增套餐
    @Override
    public void saveOrUpdate(Setmeal setmeal, Integer[] checkgroupIds) {
        if(setmeal.getId()!=null&&setmeal.getId()!=0){
            Setmeal meal = setmealDao.findById(setmeal.getId());
            if(!meal.getImg().equals(setmeal.getImg())) {
                jedisPool.getResource().
                        srem(RedisConstant.SETMEAL_PIC_DB_RESOURCES, meal.getImg());
            }
            setmealDao.updateSetmeal(setmeal);
            setmealDao.deleteAssociation(setmeal.getId());
        }else {
            setmealDao.add(setmeal);
        }
        savePic2Redis(setmeal.getImg());
        if(checkgroupIds != null && checkgroupIds.length > 0){
            //绑定套餐和检查组的多对多关系
            Map map = new HashMap<>();
            map.put("setmeal",setmeal);
            map.put("checkgroupIds",checkgroupIds);
            setmealDao.setSetmealAndCheckGroup(map);
        }
        generateMobileStaticHtml();//不管是修改套餐还是添加套餐都调用此方法
    }
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }
    @Override
    public void delete(Integer id) {
        setmealDao.deleteAssociation(id);
        List<Integer> ids = setmealDao.findCheckGroupIdsBySetmealId(id);
        if(!ids.isEmpty()){
            throw new RuntimeException("当前套餐包含检查组，不能删除");
        }
        setmealDao.delete(id);
    }

    @Override
    public Map findById(Integer id) {
        Setmeal setmeal = setmealDao.findById(id);
        List<Integer> checkGroupIds = setmealDao.findCheckGroupIdsBySetmealId(id);
        Map map=new HashMap<>();
        map.put("setmeal",setmeal);
        map.put("checkGroupIds",checkGroupIds);
        return map;
    }
    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    @Override
    public Setmeal findBySetmealId(Integer id) {
        return setmealDao.findSetmealById(id);
    }


}


