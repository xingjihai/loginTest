package com.shiroDemo;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiroDemo.service.UserServiceImpl;

public class AdminAuthorizingRealm extends AuthorizingRealm{

    @Autowired
    private UserServiceImpl userService;
    public static final String SESSION_USER_KEY = "gray";
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(SESSION_USER_KEY);  
        //方法二：String username = (String) principals.getPrimaryPrincipal();  获取用户名,然后查询数据库进行
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
//        info.addRole(user.getRole().trim());  
        Set<String> set=new HashSet<String>();
        set.add(user.getRole().trim());
        info.setStringPermissions(set);
        return info;  
    }

    /**
     * 登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        // 把token转换成User对象  
        User userLogin = tokenToUser((UsernamePasswordToken) authcToken);  
        // 验证用户是否可以登录  
        User ui = userService.doUserLogin(userLogin);  
        if(ui == null)  
            return null; // 异常处理，找不到数据  
        // 设置session  
        Session session = SecurityUtils.getSubject().getSession();  
        session.setAttribute(SESSION_USER_KEY, ui);   
        //当前 Realm 的 name  
        String realmName = this.getName();  
        //登陆的主要信息: 可以是一个实体类的对象, 但该实体类的对象一定是根据 token 的 username 查询得到的.  
//      Object principal = ui.getUsername(); 
        Object principal = authcToken.getPrincipal();  
        return new SimpleAuthenticationInfo(principal, authcToken.getCredentials(), realmName);  
    }
    
    
    private User tokenToUser(UsernamePasswordToken authcToken) {  
        User user = new User();  
        user.setUsername(authcToken.getUsername());  
        user.setPassword(String.valueOf(authcToken.getPassword()));  
        return user;  
    }

  
    
}
