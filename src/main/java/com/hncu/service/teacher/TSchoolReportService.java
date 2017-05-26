package com.hncu.service.teacher;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.teacher.TSchoolReportMapper;
import com.hncu.entity.*;
import com.hncu.service.UserService;
import com.hncu.service.admin.sys.ScoreScaleService;
import com.hncu.utils.DateUtils;
import com.hncu.utils.MD5Util;
import com.hncu.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 上传成绩服务层
 */
@Service
public class TSchoolReportService extends BaseService<TSchoolReportMapper, SchoolReport>{

    @Resource
    private ScoreScaleService scoreScaleService;

    @Transactional(readOnly = false)
    public void saveSchoolReport(SchoolReport schoolReport){
        if (StringUtils.isEmpty(schoolReport.getId())){
            if (schoolReport.getPassFlag().equals("0")){
                mapper.insertSecondAnswer(schoolReport);
            }
            schoolReport.preInsertSchoolReport();
            SchoolReport schoolReportOld = mapper.queryByStudentId(schoolReport.getStudentId());
            List<ScoreScale> scoreScaleList = scoreScaleService.queryList(new ScoreScale());
            Double score = (Double.parseDouble(schoolReportOld.getMarkingScore()) * (scoreScaleList.get(0).getScale()*0.01)) +
                    (Double.parseDouble(schoolReportOld.getReviewScore()) * (scoreScaleList.get(1).getScale()*0.01)) +
                    (Double.parseDouble(schoolReport.getAnswerScore()) * (scoreScaleList.get(2).getScale()*0.01));
            String scoreString = String.valueOf((int) Math.rint(score));
            schoolReport.setScore(scoreString);
            schoolReport.setScoreMD5(MD5Util.string2MD5(scoreString));
            mapper.updateSchoolReport(schoolReport);
            mapper.updateStudentStep(new User(schoolReport.getStudentId()));
        } else {
            if (schoolReport.getPassFlag().equals("0")){
                mapper.insertSecondAnswer(schoolReport);
            } else {
                mapper.deleteSecondAnswer(schoolReport);
            }
            mapper.updateSchoolReport(schoolReport);
        }
    }

}
