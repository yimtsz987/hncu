package com.hncu.service.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.teacher.TMarkingMapper;
import com.hncu.dao.mapper.teacher.TScheduleMapper;
import com.hncu.entity.TeacherMarking;
import com.hncu.entity.TeacherSchedule;
import com.hncu.entity.UploadParam;
import com.hncu.entity.User;
import com.hncu.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 教师端 - 开题服务层
 */
@Service
public class TMarkingService extends BaseService<TMarkingMapper, TeacherMarking>{


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
    public void checkMarking(TeacherMarking teacherMarking, HttpServletRequest request, HttpServletResponse response) throws IOException {
        teacherMarking.getMarking().setTeacherId(UserUtils.getCurrentUser().getId());
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)){
            UploadParam uploadParam = UploadUtil.upload(request, response);
            teacherMarking.getMarking().setTuploadFile(uploadParam.getUploadFile());
            teacherMarking.getMarking().setTuploadFileOldName(uploadParam.getUploadFileOldName());
            teacherMarking.getMarking().setTuploadPath(uploadParam.getUploadPath());
            teacherMarking.getMarking().preInsertMarkingReportTeacher();
        }
        mapper.checkMarking(teacherMarking);
    }

    public boolean updateStudentStep(User user){
        return mapper.updateStudentStep(user) > 0;
    }
}
