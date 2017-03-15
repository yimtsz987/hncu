package com.hncu.service.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.teacher.TMiddleCheckMapper;
import com.hncu.dao.mapper.teacher.TReviewMapper;
import com.hncu.entity.TeacherMarking;
import com.hncu.entity.TeacherMiddleCheck;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教师端 - 评阅服务层
 */
@Service
public class TReviewService extends BaseService<TReviewMapper, TeacherMarking>{

    @Override
    public TeacherMarking queryById(String id) {
        TeacherMarking teacherMarking = mapper.queryById(id);
        teacherMarking.getMarking().setStudentCheckStr(MD5Util.string2MD5(teacherMarking.getMarking().getSuploadFileOldName()));
        if (StringUtils.isNotEmpty(teacherMarking.getMarking().getTuploadFileOldName())){
            teacherMarking.getMarking().setTeacherCheckStr(MD5Util.string2MD5(teacherMarking.getMarking().getTuploadFileOldName()));
        }
        return teacherMarking;
    }
}
