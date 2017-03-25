package com.hncu.service.admin.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.admin.sys.AnswerGroupMapper;
import com.hncu.entity.Answer;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 答辩分组服务层
 */
@Service
public class AnswerGroupService extends BaseService<AnswerGroupMapper, Answer>{

    @Resource
    private UserService userService;

    @Override
    public PageInfo<Answer> queryListWithPage(Answer answer, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
             PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Answer> resultList = mapper.queryList(answer);
        List<User> teacherInfo = Lists.newArrayList();
        String[] teacherIds = null;
        User user = null;
        for (int i = 0; i < resultList.size(); i++) {
            Answer answer1 = resultList.get(i);
            teacherIds = StringUtils.split(answer1.getTeacherIds(),",");
            if (teacherIds != null){
                for (int j = 0; j < teacherIds.length; j++) {
                    user = userService.queryById(teacherIds[j]);
                    teacherInfo.add(user);
                }
                resultList.get(i).setTeacherList(teacherInfo);
            }
        }
        return new PageInfo<Answer>(resultList);
    }
}
