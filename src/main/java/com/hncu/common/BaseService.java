package com.hncu.common;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hncu.utils.StringUtils;
import com.hncu.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 所有服务类的超类
 */
public class BaseService<D extends BaseMapper <T>, T extends BaseEntity> {


    @Autowired
    protected D mapper;

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    public T queryById (String id) {
        return mapper.queryById(id);
    }

    /**
     * 查询列表
     * @param entity
     * @return
     */
    public List<T> queryList (T entity) {
        return mapper.queryList(entity);
    }

    /**
     * 分页查询列表 pageHelper PageInfo 分页参数，起始页码 每一页的大小， 排序
     * @param entity
     * @param pageParam
     * @return
     */
    public PageInfo<T> queryListWithPage (T entity, PageParam pageParam) {
        //判断是否含有排序的字符串
        if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.getOrderBy());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<T> resultList = mapper.queryList(entity);
        return new PageInfo<T>(resultList);
    }

    /**
     * 保存数据，判断id是否为空，为空时新增，不为空，修改
     * @param entity
     * @return
     */
    public boolean save (T entity) {
        int res = 0;
        if(StringUtils.isBlank(entity.getId())) {
            //entity.preInsert();
            res = mapper.insert(entity);
        } else {
            //entity.preUpdate();
            res = mapper.update(entity);
        }
        return res > 0;
    }

    /**
     * 删除数据
     * @param entity
     * @return
     */
    public boolean delete (T entity) {
        int res = mapper.delete(entity);
        return res > 0;
    }

    /**
     * 检查是否已存在
     * @param entity
     * @return
     */
    public boolean checkOnly(T entity){
        int res = 0;
        if (mapper.checkOnly(entity) == null){
            res = 1;
        } else {
            res = 0;
        }
        return res > 0;
    }

    /**
     * 查询下载信息
     * @param id
     * @return
     */
    public T queryDownloadByInfo(String id){
        return mapper.queryDownloadByInfo(id, UserUtils.getCurrentUser().getId());
    }
}
