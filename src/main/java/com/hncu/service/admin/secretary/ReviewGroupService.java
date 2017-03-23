package com.hncu.service.admin.secretary;

import com.google.common.collect.Lists;
import com.hncu.common.BaseService;
import com.hncu.dao.mapper.admin.secretary.ReviewGroupMapper;
import com.hncu.entity.TeacherAndStudent;
import com.hncu.utils.CollectionUtil;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * 评阅分组服务层
 */
@Service
public class ReviewGroupService extends BaseService<ReviewGroupMapper, TeacherAndStudent>{

    @Transactional(readOnly = false)
    public boolean randomGroup(){
        List<TeacherAndStudent> teacherAndStudentListOld = queryList(new TeacherAndStudent());
        List<TeacherAndStudent> teacherAndStudentListNew = Lists.newArrayList();
        List<String> teacherIds = Lists.newArrayList();
        int count = 0;
        for (int i = 0; i < teacherAndStudentListOld.size(); i++) {
            TeacherAndStudent teacherAndStudent = teacherAndStudentListOld.get(i);
            if (StringUtils.isEmpty(teacherAndStudent.getReviewTeacherId())){
                teacherIds.add(teacherAndStudent.getTeacherId());
                teacherAndStudentListNew.add(teacherAndStudent);
            }
        }
        Collections.shuffle(teacherIds);
        for (int i = 0; i < teacherAndStudentListNew.size(); i++) {
            teacherAndStudentListNew.get(i).setReviewTeacherId(teacherIds.get(i));
        }
        for (int i = 0; i < teacherAndStudentListNew.size(); i++) {
            TeacherAndStudent teacherAndStudent = teacherAndStudentListNew.get(i);
            mapper.randomGroup(teacherAndStudent);
            count++;
        }
        return count > 0;
    }
}
