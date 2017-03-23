package com.hncu.service.admin.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hncu.common.BaseMapper;
import com.hncu.common.BaseService;
import com.hncu.common.PageParam;
import com.hncu.dao.mapper.admin.sys.DownloadMapper;
import com.hncu.entity.DownLoad;
import com.hncu.entity.Role;
import com.hncu.service.RoleService;
import com.hncu.utils.CollectionUtil;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 下载
 */
@Service
public class DownloadService extends BaseService<DownloadMapper, DownLoad> {

    @Resource
    private RoleService roleService;

    @Override
    public PageInfo<DownLoad> queryListWithPage(DownLoad downLoad, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<DownLoad> resultList = mapper.queryList(downLoad);
        for (int i = 0; i < resultList.size(); i++) {
            DownLoad downLoad1 = resultList.get(i);
            String[] receiveId = StringUtils.split(downLoad1.getReceive(),",");
            List<Role> roleList = Lists.newArrayList();
            Role role = null;
            for (int j = 0; j < receiveId.length; j++) {
                role = roleService.queryById(receiveId[j]);
                roleList.add(role);
            }
            downLoad1.setReceiveName(CollectionUtil.extractToString(roleList,"name","，"));
        }
        return new PageInfo<DownLoad>(resultList);
    }
}
