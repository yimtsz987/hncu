package com.hncu.test;

import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.utils.DateUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 测试类
 */
@ContextConfiguration(locations = {"classpath*:/spring-core.xml"})
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private UserService userService;

    @Test
    public void queryUserByLoginName(){
        //User user = new User();
        //user.setLoginName("1406102-07");
        //user = userService.queryUserByLoginName("1406102-07");
        //System.out.println(user);
    }

    @Test
    public void testDateUtil() throws ParseException {
        String dateString = "2017-03-01";
        Date date = DateUtils.parse(dateString);
        System.out.println(date);
    }

    @Test
    public void testPassword(){
        List<User> userList = userService.queryList(new User());
        String password = null;
        String idCard = null;
        for (int i = 0; i < userList.size(); i++) {
            idCard = userList.get(i).getIdcard();
            if (!idCard.equals("")){
                password = idCard.substring(idCard.length() - 6, idCard.length());
                userList.get(i).setPassword(userService.enctypePassword(password));
                userService.updateUserPassword(userList.get(i));
            } else {
                password = "123456";
                userList.get(i).setNewPassword(userService.enctypePassword(password));
                userService.updateUserPassword(userList.get(i));
            }
        }
    }
}
