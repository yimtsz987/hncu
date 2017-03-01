package com.hncu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.MiddleCheckParamMapper;
import com.hncu.entity.MiddleCheck;
import com.hncu.entity.MiddleCheckParam;
import com.hncu.service.student.MiddleCheckService;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 中期检查参数服务层
 */
@Service
public class MiddleCheckParamService extends BaseService<MiddleCheckParamMapper, MiddleCheckParam>{

    @Resource
    private MiddleCheckService middleCheckService;

    @Override
    public PageInfo<MiddleCheckParam> queryListWithPage(MiddleCheckParam middleCheckParam, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<MiddleCheckParam> resultList = mapper.queryList(middleCheckParam);
        MiddleCheck middleCheck;
        for (int i = 0; i < resultList.size(); i++) {
            MiddleCheckParam middleCheckParam1 =  resultList.get(i);
            middleCheck = middleCheckService.queryMiddleCheckByParamId(UserUtils.getCurrentUser().getId(), middleCheckParam1.getId());
            if (middleCheck != null){
                if (StringUtils.isNotBlank(middleCheck.getState())){
                    middleCheckParam1.setStudentState(middleCheck.getState());
                    middleCheckParam1.setMiddleCheckReportId(middleCheck.getId());
                } else {
                    middleCheckParam1.setStudentState("0");
                }
            } else {
                middleCheckParam1.setStudentState("0");
            }
        }
        return new PageInfo<MiddleCheckParam>(resultList);
    }
}
