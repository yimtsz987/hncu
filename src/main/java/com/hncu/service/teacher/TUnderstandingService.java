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
public class TUnderstandingService extends BaseService<TUnderstandingMapper, TeacherUnderstanding>{

    @Resource
    private TStudentService tStudentService;

}
