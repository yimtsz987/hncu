package com.hncu.dao.mapper;

import com.hncu.common.BaseMapper;
import com.hncu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息数据持久化操作接口
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户
     * @param user
     * @return
     */
    User queryUserByUsername(User user);

    int updateStudentExpandInfo(User user);

    int updateTeacherExpandInfo(User user);

    int updateUserPassword(User user);

    List<User> queryStudentInfoListByTeacherId(@Param("teacherId") String teacherId);
}
