package com.hncu.utils;

import com.hncu.common.SpringContextHolder;
import com.hncu.entity.Classes;
import com.hncu.entity.Department;
import com.hncu.entity.Major;
import com.hncu.entity.User;
import com.hncu.security.Principal;
import com.hncu.service.UserService;
import com.hncu.service.admin.sys.ClassesService;
import com.hncu.service.admin.sys.DepartmentService;
import com.hncu.service.admin.sys.MajorService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * 用户信息帮助类
 */
public class UserUtils {

    private static UserService userService = SpringContextHolder.getBean(UserService.class);
    private static MajorService majorService = SpringContextHolder.getBean(MajorService.class);
    private static ClassesService classesService = SpringContextHolder.getBean(ClassesService.class);
    private static DepartmentService departmentService = SpringContextHolder.getBean(DepartmentService.class);

    public static final String CACHE_ROLE_LIST = "user_role_list";
    public static final String CACHE_MENU_LIST = "user_menu_list";
    public static final String CACHE_PERMISSION_LIST = "user_permission_list";
    public static final String CACHE_DEPARTMENT_LIST = "user_department_list";
    public static final String CACHE_BRANCH_LIST = "user_branch_list";
    public static final String CACHE_CLASSES_LIST = "user_classes_list";
    public static final String CACHE_MAJOR_LIST = "user_major_list";

    /**
     * 获取当前登陆的用户信息
     * @return
     */
    public static Principal getPrincipal () {
        Subject subject = SecurityUtils.getSubject();
        Principal principal =  (Principal)subject.getPrincipal();
        return principal;
    }

    /**
     * 获取当前登陆的用户对象
     * @return 如果登陆的用户为空，则返回空User
     */
    public static User getCurrentUser () {
        Principal principal = getPrincipal();
        if (principal != null) {
           User user = userService.queryById(principal.getId());
            if (user != null) {
                return user;
            } else {
                return new User ();
            }
        } else {
            return new User ();
        }
    }

    /**
     * 获取当前登陆用户所拥有的菜单列表
     * @return
     */
    /*public static List<Menu> getMenuList () {
        return userService.getMenuList();
    }*/

    /**
     * 获取当前登陆的用户的角色列表
     * @return
     */
    /*public static List <Role> getRoleList () {
        return userService.getRoleList();
    }*/


    /**
     * 获得用户的会话信息
     * @return
     */
    public static Session getSession () {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return session;
    }

    //--------------------------用户会话信息的缓存操作-----------------------//
    public static void putCache (String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getCache (String key) {
        return getSession().getAttribute(key);
    }

    public static void removeCache (String key) {
        getSession().removeAttribute(key);
    }

    /**
     * 通过ID查询用户信息
     * @param userId
     * @return
     */
    public static User queryUserById(String userId){
        User user = userService.queryById(userId);
        if (user != null){
            return user;
        } else {
            return new User();
        }
    }

    public static List<Major> queryMajorList(){
        List<Major> majorList = majorService.queryList(new Major());
        return majorList;
    }

    public static List<Classes> queryClassesList(){
        List<Classes> classesList = classesService.queryList(new Classes());
        return classesList;
    }

    public static List<Department> queryDepartmentList(){
        List<Department> departmentList = departmentService.queryList(new Department());
        return departmentList;
    }
}
