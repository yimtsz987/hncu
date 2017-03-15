package com.hncu.service.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.teacher.TMiddleCheckMapper;
import com.hncu.entity.TeacherMiddleCheck;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教师端 - 中期检查服务层
 */
@Service
public class TMiddleCheckService extends BaseService<TMiddleCheckMapper, TeacherMiddleCheck>{

    @Override
    public PageInfo<TeacherMiddleCheck> queryListWithPage(TeacherMiddleCheck teacherMiddleCheck, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<TeacherMiddleCheck> resultList = mapper.queryList(teacherMiddleCheck);
        for (int i = 0; i < resultList.size(); i++) {
            TeacherMiddleCheck teacherMiddleCheck1 =  resultList.get(i);
            teacherMiddleCheck1.getMiddleCheck().setCheckStr(MD5Util.string2MD5(teacherMiddleCheck1.getMiddleCheck().getUploadFileOldName()));
        }
        return new PageInfo<TeacherMiddleCheck>(resultList);
    }
}
