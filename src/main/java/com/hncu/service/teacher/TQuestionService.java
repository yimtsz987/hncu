package com.hncu.service.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.student.QuestionMapper;
import com.hncu.dao.mapper.teacher.TQuestionMapper;
import com.hncu.entity.*;
import com.hncu.service.UserService;
import com.hncu.service.student.QuestionService;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UploadUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 问题咨询服务层
 */
@Service
public class TQuestionService extends BaseService<TQuestionMapper, TeacherQuestion> {

    @Resource
    private UserService userService;

    @Resource
    private QuestionService questionService;

    @Override
    public TeacherQuestion queryById(String id) {
        TeacherQuestion teacherQuestion = mapper.queryById(id);
        teacherQuestion.getMarking().setStudentCheckStr(MD5Util.string2MD5(teacherQuestion.getMarking().getSuploadFileOldName()));
        if (StringUtils.isNotEmpty(teacherQuestion.getMarking().getTuploadFileOldName())){
            teacherQuestion.getMarking().setTeacherCheckStr(MD5Util.string2MD5(teacherQuestion.getMarking().getTuploadFileOldName()));
        }
        return teacherQuestion;
    }

    @Transactional(readOnly = false)
    public void solveQuestion(TeacherMarking teacherMarking, HttpServletRequest request, HttpServletResponse response) throws IOException {
        teacherMarking.getMarking().setTeacherId(UserUtils.getCurrentUser().getId());
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)){
            UploadParam uploadParam = UploadUtil.upload(request, response);
            teacherMarking.getMarking().setTuploadFile(uploadParam.getUploadFile());
            teacherMarking.getMarking().setTuploadFileOldName(uploadParam.getUploadFileOldName());
            teacherMarking.getMarking().setTuploadPath(uploadParam.getUploadPath());
            teacherMarking.getMarking().preInsertMarkingReportTeacher();
        }
        mapper.solveQuestion(teacherMarking);
    }


    @Override
    public PageInfo<TeacherQuestion> queryStudentInfoListWithPage(TeacherQuestion teacherQuestion, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<TeacherQuestion> resultList = mapper.queryStudentInfoList(teacherQuestion);
        String questionState = "0"; //0无消息1有消息
        for (int i = 0; i < resultList.size(); i++) {
            List<Marking> markingList = questionService.queryQuestionListByStudentId(resultList.get(i).getUser().getId());
            for (int j = 0; j < markingList.size(); j++) {
                if ("0".equals(markingList.get(j).getState())){
                    questionState = "1";
                    break;
                }
            }
            resultList.get(i).setQuestionState(questionState);
            resultList.get(i).setQuestionList(markingList);
            questionState = "0";
        }
        return new PageInfo<TeacherQuestion>(resultList);
    }
}
