package com.hncu.service;

import com.hncu.common.BaseService;
import com.hncu.dao.mapper.RoleMapper;
import com.hncu.dao.mapper.UserMapper;
import com.hncu.entity.Permission;
import com.hncu.entity.Role;
import com.hncu.entity.User;
import com.hncu.utils.CacheUtil;
import com.hncu.utils.EncryptUtil;
import com.hncu.utils.UserUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 用户信息服务类
 */
@Service
public class UserService extends BaseService<UserMapper, User> {

    @Resource
    private RoleMapper roleMapper;

    public static final int HASH_ITERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    public static final String USER_CACHE = "userCache";
    public static final String USER_CACHE_ID = "id_";
    public static final String USER_CACHE_USERNAME = "username_";

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User queryUserByUsername(String username){
        User user = (User) CacheUtil.get(USER_CACHE, USER_CACHE_USERNAME+ username);
        if (user == null){
            user = mapper.queryUserByUsername(new User(null,username));
            if (user == null){
                return null;
            }

            CacheUtil.put(USER_CACHE, USER_CACHE_USERNAME + username, user);
            CacheUtil.put(USER_CACHE, USER_CACHE_ID + user.getId(), user);
        }
        return user;
    }

    /**
     * 获取当前登录用户的角色列表
     * @return
     */
    public List<Role> getRoleList(){
        List<Role> roleList = (List<Role>) UserUtils.getCache(UserUtils.CACHE_ROLE_LIST);
        if (roleList == null){
            User user = UserUtils.getCurrentUser();
            //查询用户所拥有的角色列表
            roleList = roleMapper.queryByUserId(new Role(user,getPermissionListByRole()));
            UserUtils.putCache(UserUtils.CACHE_ROLE_LIST, roleList);
        }

        return roleList;
    }

    public List<Permission> getPermissionListByRole(){
        List<Permission> permissionList = (List<Permission>) UserUtils.getCache(UserUtils.CACHE_PERMISSION_LIST);
        if (permissionList == null){
            User user = UserUtils.getCurrentUser();
            permissionList = roleMapper.queryPermissionByUserId(new Permission(user));
            UserUtils.putCache(UserUtils.CACHE_PERMISSION_LIST, permissionList);
        }
        return permissionList;
    }

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    public boolean updateUserPassword(User user){
        clearUserCache(user);
        return mapper.updateUserPassword(user) > 0;
    }

    /**
     * 对密码进行加密 SHA-1
     * @param plainPassword 明文密码
     * @return
     */
    public String enctypePassword (String plainPassword) {
        byte[] salt = EncryptUtil.generateSalt(SALT_SIZE);
        byte[] hashPass = EncryptUtil.sha1(plainPassword.getBytes(), salt, HASH_ITERATIONS);
        return EncryptUtil.encodeHex(salt) + EncryptUtil.encodeHex(hashPass);
    }

    /**
     * 校验密码是否有效
     * @param plainPass 明文密码
     * @param password 密文密码
     * @return
     */
    public boolean validatePassword (String plainPass, String password) {
        byte[] salt = EncryptUtil.decodeHex(password.substring(0, 16));
        byte[] hashPass = EncryptUtil.sha1(plainPass.getBytes(), salt, HASH_ITERATIONS);
        return password.equals(EncryptUtil.encodeHex(salt) + EncryptUtil.encodeHex(hashPass));
    }

    /**
     * 清除用户缓存
     * @param user
     */
    private void clearUserCache (User user) {
        CacheUtil.remove(USER_CACHE, USER_CACHE_ID + user.getId());
        CacheUtil.remove(USER_CACHE, USER_CACHE_USERNAME + user.getUsername());
    }

    public void resetPassword(User user){
        String idCard = user.getIdcard();
        if (!user.getIdcard().equals("")){
            user.setNewPassword(enctypePassword(idCard.substring(idCard.length() - 6, idCard.length())));
            mapper.updateUserPassword(user);
        } else {
            user.setNewPassword(enctypePassword("123456"));
            mapper.updateUserPassword(user);
        }
    }

    /**
     * 查询用户密码
     * @param user
     * @param oldPassword
     * @return
     */
    public boolean queryPassword(User user,String oldPassword){
        user = mapper.queryById(user.getId());
        if (user != null){
            if (user.getPassword().equals(oldPassword)){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public List<User> queryStudentInfoListByTeacherId(String teacherId){
        return mapper.queryStudentInfoListByTeacherId(teacherId);
    }
}
