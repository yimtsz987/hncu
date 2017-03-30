package com.hncu.service.admin.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.admin.sys.AnswerGroupMapper;
import com.hncu.entity.Answer;
import com.hncu.entity.TeacherInfo;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.utils.CollectionUtil;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 答辩分组服务层
 */
@Service
public class AnswerGroupService extends BaseService<AnswerGroupMapper, Answer>{

    @Resource
    private UserService userService;

    @Resource
    private TeacherService teacherService;

    @Override
    public PageInfo<Answer> queryListWithPage(Answer answer, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
             PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Answer> resultList = mapper.queryList(answer);
        String[] teacherIds = null;
        User user = null;
        for (int i = 0; i < resultList.size(); i++) {
            Answer answer1 = resultList.get(i);
            teacherIds = StringUtils.split(answer1.getTeacherIds(),",");
            String teacherNameString = null;
            List<User> teacherInfo = Lists.newArrayList();
            if (teacherIds != null){
                for (int j = 0; j < teacherIds.length; j++) {
                    user = userService.queryById(teacherIds[j]);
                    teacherInfo.add(user);
                }
                teacherNameString = CollectionUtil.extractToString(teacherInfo,"name", ",");
            } else {
                teacherNameString = "-";
            }
            resultList.get(i).setTeacherNameString(teacherNameString);
        }
        return new PageInfo<Answer>(resultList);
    }

    @Transactional(readOnly = false)
    public void updateLeader(TeacherInfo teacherInfo){
        mapper.updateLeader(teacherInfo);
        mapper.updateAnswerInfo(teacherInfo);
    }

    @Transactional(readOnly = false)
    public void deleteLeader(TeacherInfo teacherInfo){
        mapper.deleteAnswerInfo(teacherInfo);
        teacherInfo.settId(null);
        mapper.deleteLeader(teacherInfo);
    }

    @Transactional(readOnly = false)
    public List<TeacherInfo> queryAnswerTeacherList(Answer answerClasses){
        Answer answer = mapper.queryByClasses(answerClasses);
        List<TeacherInfo> teacherInfoList = Lists.newArrayList();
        String[] teacherIds = StringUtils.split(answer.getTeacherIds(),",");
        TeacherInfo teacherInfo = null;
        if (teacherIds != null){
            for (int i = 0; i < teacherIds.length; i++) {
                teacherInfo = teacherService.queryById(teacherIds[i]);
                teacherInfoList.add(teacherInfo);
            }
        }
        return teacherInfoList;
    }

    @Transactional(readOnly = false)
    public void updateAnswerTeacher(Answer answer){
        Answer answerOld = mapper.queryById(answer.getId());
        if (StringUtils.isNotEmpty(answerOld.getTeacherIds())){
            answer.setTeacherIds(answerOld.getTeacherIds() + answer.getTeacherId() + ",");
            answer.setTeacherNum(answerOld.getTeacherNum() + 1);
            mapper.updateAnswerTeacher(answer);
            TeacherInfo teacherInfo = new TeacherInfo();
            teacherInfo.settId(answer.getTeacherId());
            teacherInfo.setAnswerId(answer.getId());
            mapper.updateAnswerInfo(teacherInfo);
        } else {
            answer.setTeacherIds(answer.getTeacherId() + ",");
            answer.setTeacherNum(answerOld.getTeacherNum() + 1);
            mapper.updateAnswerTeacher(answer);
            TeacherInfo teacherInfo = new TeacherInfo();
            teacherInfo.settId(answer.getTeacherId());
            teacherInfo.setAnswerId(answer.getId());
            mapper.updateAnswerInfo(teacherInfo);
        }

    }

    @Transactional(readOnly = false)
    public void deleteAnswerTeacher(Answer answer){
        Answer answerOld = mapper.queryById(answer.getId());
        String[] teacherIds = StringUtils.split(answerOld.getTeacherIds(), ",");
        List<TeacherInfo> teacherInfoList = Lists.newArrayList();
        for (int i = 0; i < teacherIds.length; i++) {
            TeacherInfo teacherInfo = new TeacherInfo();
            teacherInfo.setId(teacherIds[i]);
            teacherInfoList.add(teacherInfo);
        }
        for (int i = 0; i < teacherInfoList.size(); i++) {
            if (teacherInfoList.get(i).getId().equals(answer.getTeacherId())){
                teacherInfoList.remove(i);
            }
        }
        String newTeacherIds = CollectionUtil.extractToString(teacherInfoList, "id", ",");
        if (StringUtils.isNotEmpty(newTeacherIds)){
            newTeacherIds = newTeacherIds + ",";
        } else {
            newTeacherIds = null;
        }
        answer.setTeacherNum(answerOld.getTeacherNum() - 1);
        answer.setTeacherIds(newTeacherIds);
        mapper.updateAnswerTeacher(answer);
        TeacherInfo teacherInfo = new TeacherInfo();
        teacherInfo.settId(answer.getTeacherId());
        mapper.deleteAnswerInfo(teacherInfo);
    }

    public boolean updateAnswerTimeInfo(Answer answer){
        return mapper.updateAnswerTimeInfo(answer) >0;
    }
}
