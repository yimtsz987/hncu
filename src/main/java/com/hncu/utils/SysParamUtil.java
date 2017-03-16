package com.hncu.utils;

import com.hncu.common.SpringContextHolder;
import com.hncu.entity.SysParam;
import com.hncu.service.admin.sys.SysParamService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统参数工具类
 */
public class SysParamUtil {

    private static SysParamService sysParamService = SpringContextHolder.getBean(SysParamService.class);

    public static final String SYS_PARAM_CACHE_PREFIX = "sysParam_";

    /**
     * 根据系统参数键名查询系统参数列表
     * @param key
     * @return
     */
    public static SysParam getSysParam(String key){
        /*List<SysParam> sysParamList = (List<SysParam>) CacheUtil.get(SYS_PARAM_CACHE_PREFIX + key);
        if (CollectionUtil.isEmpty(sysParamList)){
            SysParam sysParam = new SysParam();
            sysParam.setParamKey(key);
            sysParamList = sysParamService.queryList(sysParam);
            if (! CollectionUtil.isEmpty(sysParamList)) {
                CacheUtil.put(SYS_PARAM_CACHE_PREFIX + key, sysParamList);
                return sysParamList;
            } else {
                return new ArrayList<SysParam>();
            }
        } else {
            return sysParamList;
        }*/
        SysParam sysParam = (SysParam) CacheUtil.get(SYS_PARAM_CACHE_PREFIX + key);
        if (sysParam == null){
            sysParam = sysParamService.queryParamByKey(key);
            if (sysParam != null){
                CacheUtil.put(SYS_PARAM_CACHE_PREFIX + key, sysParam);
                return sysParam;
            } else {
                return new SysParam();
            }
        } else {
            return sysParam;
        }
    }

    /**
     * 通过键名获取对应的值
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getParamValue (String key,String defaultValue){
        if (StringUtils.isNotBlank(key)){
            return getSysParam(key).getParamValue();
        }
        return defaultValue;
    }

    public static String getParamValue(String key){
        return getParamValue(key, "");
    }

    /**
     * 通过键名获取对应的日期值
     * @param key
     * @return
     */
    public static Date getParamDateValue (String key) throws ParseException {
        if (StringUtils.isNotBlank(key)){
            return DateUtils.parse(getSysParam(key).getParamValue(),"yyyy-MM-dd");
        }
        return null;
    }
}
