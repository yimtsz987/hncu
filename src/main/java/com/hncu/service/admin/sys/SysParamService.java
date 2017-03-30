package com.hncu.service.admin.sys;

import com.google.common.collect.Lists;
import com.hncu.common.BaseEntity;
import com.hncu.common.BaseService;
import com.hncu.common.SysYear;
import com.hncu.dao.mapper.admin.sys.SysParamMapper;
import com.hncu.entity.SysParam;
import com.hncu.entity.TeacherInfo;
import com.hncu.entity.TeacherYearInfo;
import com.hncu.utils.CacheUtil;
import com.hncu.utils.CollectionUtil;
import com.hncu.utils.SpringViewListener;
import com.hncu.utils.SysParamUtil;
import com.hncu.web.admin.sys.SysParamController;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统参数服务层
 */
@Service
public class SysParamService extends BaseService<SysParamMapper, SysParam>{

    @Resource
    private TeacherService teacherService;

    public SysParam queryParamByKey (String paramKey){
        return mapper.queryParamByKey(paramKey);
    }

    public List<String> queryKeyList(SysParam sysParam){
        return mapper.queryKeyList(sysParam);
    }

    public List<String> queryLabelList(SysParam sysParam){
        return mapper.queryLabelList(sysParam);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean save(SysParam entity) {
        boolean flag = false;
        CacheUtil.put(SysParamUtil.SYS_PARAM_CACHE_PREFIX + entity.getParamKey(), entity);
        flag = super.save(entity);
        if (entity.getParamKey().equals("year")){
            List<TeacherInfo> teacherInfoList = teacherService.queryTeacherIdList();
            List<TeacherYearInfo> teacherYearInfoList = Lists.newArrayList();
            for (int i = 0; i < teacherInfoList.size(); i++) {
                TeacherYearInfo teacherYearInfo = new TeacherYearInfo();
                teacherYearInfo.setTeacherId(teacherInfoList.get(i).getId());
                teacherYearInfo.setYear(entity.getParamValue());
                teacherYearInfoList.add(teacherYearInfo);
            }
            List<TeacherYearInfo> teacherYearInfos = mapper.queryTeacherYearInfo(teacherYearInfoList.get(0).getTeacherId());
            boolean insertFlag = true;
            for (int i = 0; i < teacherYearInfos.size(); i++) {
                if (teacherYearInfos.get(i).getYear().equals(entity.getParamValue())){
                    insertFlag = false;
                }
            }
            if (insertFlag){
                mapper.insertTeacherYearAnswer(teacherYearInfoList);
                mapper.insertTeacherYearReview(teacherYearInfoList);
                mapper.insertTeacherYearStudent(teacherYearInfoList);
            }
            SysYear.sysYear = entity.getParamValue();
            // todo 更新监听器 SpringViewListener.setSysParamYear(entity.getParamValue());
        }
        return flag;
    }
}
