package com.hncu.service.student;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.student.ChooseTeacherMapper;
import com.hncu.dao.mapper.student.ChooseTitleMapper;
import com.hncu.entity.TeacherInfo;
import com.hncu.entity.Title;
import com.hncu.entity.User;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 选择课题服务层
 */
@Service
public class ChooseTitleService extends BaseService<ChooseTitleMapper, Title>{

    @Transactional(readOnly = false)
    public void chooseTitle(Title title){
        if (StringUtils.isNotBlank(title.getId())){
            title.setStudentId(UserUtils.getCurrentUser().getId());
            mapper.chooseTitle(title);
            mapper.titleIdEdit(title.getId(), UserUtils.getCurrentUser().getId());
            mapper.stepTwoEnd(UserUtils.getCurrentUser());
        }
    }

}
