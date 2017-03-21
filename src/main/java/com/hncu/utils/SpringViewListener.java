package com.hncu.utils;

import com.hncu.entity.SysParam;
import com.hncu.service.admin.sys.SysParamService;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

/**
 * Created by yimts on 2017-3-20.
 */
public class SpringViewListener implements ServletContextAware{


    @Resource
    private SysParamService sysParamService;

    private static String sysParamYear;

    public static String getSysParamYear() {
        return sysParamYear;
    }

    public static void setSysParamYear(String sysParamYear) {
        SpringViewListener.sysParamYear = sysParamYear;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        SysParam sysParam = null;
        try {
            sysParam = sysParamService.queryParamByKey("year");
            sysParamYear = sysParam.getParamValue();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
