package com.softeem.crm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.softeem.crm.pojo.CustomerServer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.crm.vo.CustomerServerQuery;
import com.softeem.crm.vo.CustomerServerVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangw
 * @description 针对表【t_customer_server】的数据库操作Mapper
 * @createDate 2022-12-27 14:22:59
 * @Entity com.softeem.crm.pojo.CustomerServer
 */
public interface CustomerServerMapper extends BaseMapper<CustomerServer> {
    IPage<CustomerServerVo> selectByParams(IPage page, @Param("customerServerQuery") CustomerServerQuery customerServerQuery);
}




