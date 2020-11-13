package org.os.GraduationProject.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.os.GraduationProject.pojo.User;
import org.os.GraduationProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService service;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("执行授权逻辑");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();

        User dbUser = service.findById(user.getId());
        info.addStringPermission(dbUser.getPower());

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)arg0;

        User user = service.findByUser(token.getUsername());
        if(user==null){
            //用户名不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }

        //当前realm对象的name
        String realmName = getName();
        //2.判断密码
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUser());
        //封装用户信息，构建AuthenticationInfo对象并返回
        return new SimpleAuthenticationInfo(user, user.getPassword(),
                credentialsSalt, realmName);
    }
}
