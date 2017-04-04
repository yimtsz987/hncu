package com.hncu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.NoticeMapper;
import com.hncu.entity.Notice;
import com.hncu.entity.Role;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 通知公告服务层
 */
@Service
public class NoticeService extends BaseService<NoticeMapper, Notice>{

    @Resource
    private RoleService roleService;

    public boolean saveNoticeByRole(Notice notice){
        if (StringUtils.isEmpty(notice.getId())){
            notice.preInserNotice();
            notice.setUserRoles(notice.getRoleIds());
            return mapper.insertNoticeByRole(notice) > 0;
        } else {
            notice.preInserNotice();
            notice.setUserRoles(notice.getRoleIds());
            return mapper.updateNotice(notice) > 0;
        }

    }

    public boolean deleteNotice(Notice notice){
        return mapper.deleteNotice(notice) > 0;
    }

    @Override
    public PageInfo<Notice> queryListWithPage(Notice notice, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Notice> resultList = mapper.queryList(notice);
        for (int i = 0; i < resultList.size(); i++) {
            Notice notice1 = resultList.get(i);
            String[] roles = StringUtils.split(notice1.getUserRoles(), ",");
            String roleName = "";
            for (int j = 0; j < roles.length; j++) {
                Role role = roleService.queryById(roles[j]);
                roleName = roleName + role.getName()+ "，" ;
            }
            resultList.get(i).setUserRoleName(roleName);
        }
        return new PageInfo<Notice>(resultList);
    }

    public PageInfo<Notice> queryTeacherNoticeListWithPage(Notice notice, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Notice> resultList = mapper.queryList(notice);
        for (int i = 0; i < resultList.size(); i++) {
            Notice notice1 = resultList.get(i);
            String[] roles = StringUtils.split(notice1.getUserRoles(), ",");
            boolean deleteFlag = true;
            for (int j = 0; j < roles.length; j++) {
                if (roles[j].equals("4")){
                    deleteFlag = false;
                    break;
                }
            }
            if (deleteFlag){
                resultList.remove(i);
            }
        }
        return new PageInfo<Notice>(resultList);
    }

    public PageInfo<Notice> queryStudentNoticeListWithPage(Notice notice, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Notice> resultList = mapper.queryList(notice);
        for (int i = 0; i < resultList.size(); i++) {
            Notice notice1 = resultList.get(i);
            String[] roles = StringUtils.split(notice1.getUserRoles(), ",");
            boolean deleteFlag = true;
            for (int j = 0; j < roles.length; j++) {
                if (roles[j].equals("5")){
                    deleteFlag = false;
                    break;
                }
            }
            if (deleteFlag){
                resultList.remove(i);
            }
        }
        return new PageInfo<Notice>(resultList);
    }
}
