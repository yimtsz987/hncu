package com.hncu.service.teacher;

import com.google.common.collect.Lists;
import com.hncu.common.BaseService;
import com.hncu.dao.mapper.teacher.TUnderstandingMapper;
import com.hncu.entity.TeacherUnderstanding;
import com.hncu.entity.Understanding;
import com.hncu.entity.User;
import com.hncu.utils.SysParamUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 教师端 - 审题服务层
 */
@Service
public class TUnderstandingService extends BaseService<TUnderstandingMapper, Understanding>{

    @Resource
    private TStudentService tStudentService;

    public List<TeacherUnderstanding> queryUnderstantList(){
        List<Understanding> understandingList = Lists.newArrayList();
        List<User> userList = tStudentService.queryStudentList(UserUtils.getCurrentUser().getId(), SysParamUtil.getParamValue("year"));
        List<TeacherUnderstanding> teacherUnderstandingList = Lists.newArrayList();
        Understanding understanding;
        TeacherUnderstanding teacherUnderstanding = null;
        /*for (User user : userList) {
            understanding = mapper.queryByStudentId(user.getId());
            understandingList.add(understanding);
            teacherUnderstanding.setUser(user);
        }*/
        return teacherUnderstandingList;
    }
}
