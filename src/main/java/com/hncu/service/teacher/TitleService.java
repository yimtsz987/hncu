package com.hncu.service.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.teacher.TitleMapper;
import com.hncu.entity.SysParam;
import com.hncu.entity.Title;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import com.hncu.utils.SysParamUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教师端 - 课题服务层
 */
@Service
public class TitleService extends BaseService<TitleMapper, Title>{

    @Override
    public PageInfo<Title> queryListWithPage(Title entity, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Title> resultList = mapper.queryList(entity);
        for (int i = 0; i < resultList.size(); i++) {
            resultList.get(i).setCheckTitle(MD5Util.string2MD5(resultList.get(i).getTitle()));
        }
        return new PageInfo<Title>(resultList);
    }

    public boolean saveTitle(Title title){
        if (StringUtils.isEmpty(title.getId())){
            title.setTeacherId(UserUtils.getCurrentUser().getId());
            title.setYear(SysParamUtil.getParamValue("year"));
            return mapper.insertTitle(title) > 0;
        } else {
            return mapper.updateTitle(title) > 0;
        }
    }
    public boolean deleteTitle(String id){
        return mapper.deleteTitle(id, UserUtils.getCurrentUser().getId()) > 0;
    }
}
