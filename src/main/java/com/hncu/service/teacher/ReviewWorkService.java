package com.hncu.service.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.teacher.ReviewWorkMapper;
import com.hncu.dao.mapper.teacher.TReviewMapper;
import com.hncu.entity.TeacherMarking;
import com.hncu.entity.UploadParam;
import com.hncu.entity.User;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UploadUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.hncu.common.BaseEntity.PASS_FLAG_YES;

/**
 * 教师端 - 评阅服务层
 */
@Service
public class ReviewWorkService extends BaseService<ReviewWorkMapper, TeacherMarking>{

    @Override
    public TeacherMarking queryById(String id) {
        TeacherMarking teacherMarking = mapper.queryById(id);
        teacherMarking.getMarking().setStudentCheckStr(MD5Util.string2MD5(teacherMarking.getMarking().getSuploadFileOldName()));
        if (StringUtils.isNotEmpty(teacherMarking.getMarking().getTuploadFileOldName())){
            teacherMarking.getMarking().setTeacherCheckStr(MD5Util.string2MD5(teacherMarking.getMarking().getTuploadFileOldName()));
        }
        return teacherMarking;
    }

    @Transactional(readOnly = false)
    public void reviewWorkCheck(TeacherMarking teacherMarking, HttpServletRequest request, HttpServletResponse response) throws IOException {
        teacherMarking.getMarking().setReviewTeacherId(UserUtils.getCurrentUser().getId());
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)){
            UploadParam uploadParam = UploadUtil.upload(request, response);
            teacherMarking.getMarking().setTuploadFile(uploadParam.getUploadFile());
            teacherMarking.getMarking().setTuploadFileOldName(uploadParam.getUploadFileOldName());
            teacherMarking.getMarking().setTuploadPath(uploadParam.getUploadPath());
            teacherMarking.getMarking().preInsertMarkingReportTeacher();
        }
        if (teacherMarking.getMarking().getState().equals(PASS_FLAG_YES)){
            mapper.reviewWorkCheck(teacherMarking);
            mapper.updateStudentReviewStep(new User(teacherMarking.getUser().getId()));
        } else {
            mapper.reviewWorkCheck(teacherMarking);
        }
    }

    public boolean updateStudentReviewStep(User user){
        return mapper.updateStudentReviewStep(user) > 0;
    }

}

