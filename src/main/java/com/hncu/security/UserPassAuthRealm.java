package com.hncu.security;

import com.hncu.entity.Role;
import com.hncu.entity.User;
import com.hncu.service.UserService;
import com.hncu.utils.EncryptUtil;
import com.hncu.utils.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2015/7/22.
 */
@Component
public class UserPassAuthRealm extends AuthorizingRealm{

    @Resource
    private UserService userService;

    @Resource
    private FormAuthFilter formAuthFilter;

    /***
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Principal principal = (Principal)getAvailablePrincipal(principalCollection);
        User user = userService.queryUserByUsername(principal.getUsername());
        if (user != null){
            //权限信息对象info,用来存放查出的用户所有角色及权限
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户角色集合
            user.setRoleList(userService.getRoleList());
            info.setRoles(user.getRolesName());
            List<Role> roleList = user.getRoleList();
            for (Role role : roleList){
                if (StringUtils.isNotBlank(role.getPermission())){
                    for (String permission : StringUtils.split(role.getPermission(),",")){
                        info.addStringPermission(permission);
                    }
                    //info.addStringPermissions(role.getPermissionsName());
                }
            }

            info.addStringPermission("user");

            return info;
        }
        return null;
    }

    /**
     * 进行登陆的认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        //UsernamePasswordToken authTocken = (UsernamePasswordToken)authenticationToken;
        CaptchaAuthenticationToken authTocken = (CaptchaAuthenticationToken) authenticationToken;
        User user = userService.queryUserByUsername(authTocken.getUsername());
        if (user != null){
            byte[] salt = EncryptUtil.decodeHex(user.getPassword().substring(0,16));

            return new SimpleAuthenticationInfo(new Principal(user),
                    user.getPassword().substring(16),
                    ByteSource.Util.bytes(salt), getName());
        } else {
            return null;
        }
    }

    /**
     * 设定密码校验的算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher () {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(EncryptUtil.SHA1);
        matcher.setHashIterations(userService.HASH_ITERATIONS);
        setCredentialsMatcher(matcher);
    }
}
