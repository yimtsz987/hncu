package com.hncu.service.admin.secretary;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.admin.secretary.ReviewGroupMapper;
import com.hncu.entity.TeacherAndStudent;
import com.hncu.utils.RandomGroupUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 评阅分组服务层
 */
@Service
public class ReviewGroupService extends BaseService<ReviewGroupMapper, TeacherAndStudent>{

    @Transactional(readOnly = false)
    public boolean randomGroup(){
        List<TeacherAndStudent> teacherAndStudentListOld = mapper.queryList(new TeacherAndStudent());
        for (int i = 0; i < teacherAndStudentListOld.size(); i++) {
            teacherAndStudentListOld.get(i).setReviewTeacherId(teacherAndStudentListOld.get(i).getTeacherId());
        }
        List<TeacherAndStudent> teacherAndStudentListNew = RandomGroupUtil.randomGroup(teacherAndStudentListOld);
        int count = 0;
        for (int i = 0; i < teacherAndStudentListNew.size(); i++) {
            TeacherAndStudent teacherAndStudent = teacherAndStudentListNew.get(i);
            mapper.randomGroup(teacherAndStudent);
            mapper.updateReviewFlag(teacherAndStudent);
            count++;
        }
        return count > 0;
    }
}
