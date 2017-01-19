package com.hncu.utils;


import com.hncu.common.SpringContextHolder;
import com.hncu.entity.Dict;
import com.hncu.service.admin.sys.DictService;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典信息帮助类
 */
public class DictUtils {

    private static DictService dictService
            = SpringContextHolder.getBean(DictService.class);

    public static final String DICT_CACHE_PREFIX = "dict_";

    /**
     * 根据字典类型查询字典列表
     * @param type
     * @return
     */
    public static List<Dict> getDictList (String type) {
        List<Dict> dictList = (List<Dict>)CacheUtil.get(DICT_CACHE_PREFIX + type);
        if (CollectionUtil.isEmpty(dictList)) {
            Dict dict = new Dict();
            dict.setType(type);
            dictList = dictService.queryList(dict);
            if (! CollectionUtil.isEmpty(dictList)) {
                CacheUtil.put(DICT_CACHE_PREFIX + type, dictList);
                return dictList;
            } else {
                return new ArrayList<Dict>();
            }
        } else {
            return dictList;
        }
    }

    /**
     * 根据字典类型以及字典的值，获取对应的标签
     * @param value
     * @param type
     * @param defalutValue
     * @return
     */
    public static String getDictLabel (String value, String type, String defalutValue) {
        if (StringUtils.isNotBlank(value)
                && StringUtils.isNotBlank(type)) {
            for (Dict dict : getDictList(type)) {
                if (value.equals(dict.getValue())) {
                    return dict.getLabel();
                }
            }
        }

        return defalutValue;
    }

    public static String getDictLabel (String value, String type) {
        return getDictLabel (value, type, "");
    }

    /**
     * 根据标签值获得对应的label
     * @param label
     * @param type
     * @param defalutValue
     * @return
     */
    public static String getDictValue (String label, String type, String defalutValue) {
        if (StringUtils.isNotBlank(label)
                && StringUtils.isNotBlank(type)) {
            for (Dict dict : getDictList(type)) {
                if (label.equals(dict.getLabel())) {
                    return dict.getValue();
                }
            }
        }

        return defalutValue;
    }


    public static String getDictValue (String label, String type) {
        return getDictValue (label, type, "");
    }


}
