package com.hncu.test;

import com.hncu.utils.DateUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;

/**
 * 测试类
 */
@ContextConfiguration(locations = {"classpath*:/spring-core.xml"})
public class UserServiceTest extends AbstractJUnit4SpringContextTests {


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
}
