package com.jsure.datacenter.filter;

import com.google.common.base.Throwables;
import com.jsure.datacenter.constant.SystemConstant;
import com.jsure.datacenter.model.entitymodel.TUser;
import com.jsure.datacenter.service.TokenService;
import com.jsure.datacenter.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.util.List;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/5/22
 * @Time: 14:42
 * I am a Code Man -_-!
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

//    @Autowired
//    private OmsUserInfoService omsUserInfoService;

    @Autowired
    private TokenService tokenService;

    @Override
    public String getName() {
        return "shiroRealm";
    }

    /**
     * 授权方法
     *
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        //获取用户和Session
//        Subject user = SecurityUtils.getSubject();
//        Session session = user.getSession();
//        UserInfoDto userInfoDto = SessionUtils.getUserInfo();
//        //session信息为空，则让shiro登出
//        String sessionId = session.getId().toString();
//        if (null == userInfoDto) {
//            user.logout();
//            return null;
//        }
//        //取出session中的功能权限URL列表
//        List<RoleInfoDto> roles = SessionUtils.getRoles();
//        if (roles == null) {
//            roles = Lists.newArrayList();
//        }
//        Map<String, String> permissionMap = RedisUtils.getHashMap(Constants.SHIRO_FUNCTIONAL_URL_ROLE);
//        Set<String> authoritySet = Sets.newHashSet(); // 权限集合
//        //拼装授权字符串URL参数
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        for (RoleInfoDto dto : roles) {
//            info.addRole(String.valueOf(dto.getRoleId()));
//            String urlTmp = permissionMap.get(String.valueOf(dto.getRoleId()));
//            if (ObjectUtils.isNotNullAndEmpty(urlTmp)) {
//                String[] urls = urlTmp.split("\\|");
//                for (String url : urls) {
//                    authoritySet.add(url);
//                }
//            }
//        }
//        //对象赋值权限
//        info.setStringPermissions(authoritySet);
        return info;
    }

    /**
     * 认证回调函数,登录时调用
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        TUser user = null;
        try {
            //根据登陆名查询用户信息
            user = tokenService.findUserByUserName(token.getUsername());
        } catch (Exception e) {
            log.error("failed to doGetAuthenticationInfo, PARAMETER:{}, CAUSE:{}", token, Throwables.getStackTraceAsString(e));
        }
        if (ObjectUtils.isNullOrEmpty(user)) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(SystemConstant.JWT_SECERT), getName());
    }

    /**
     * 清楚缓存
     */
    public void clearAuthz() {
        Subject subject = SecurityUtils.getSubject();
        this.doClearCache(subject.getPrincipals());
        subject.releaseRunAs();
    }

    public void clear() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
        Subject subject = SecurityUtils.getSubject();
        subject.releaseRunAs();
    }


    public static void main(String[] args) {
        //加密方式
        String algorithmName="MD5";

        //加密的字符串
        String source="123456";

        //盐值，用于和密码混合起来用
        ByteSource salt = ByteSource.Util.bytes(SystemConstant.JWT_SECERT);

        //加密的次数,可以进行多次的加密操作
        int hashIterations = 2;

        //通过SimpleHash 来进行加密操作
        SimpleHash hash = new SimpleHash(algorithmName, source, salt, hashIterations);

        System.out.println(hash.toString());
    }
}
