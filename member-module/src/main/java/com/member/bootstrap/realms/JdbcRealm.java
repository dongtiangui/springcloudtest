package com.member.bootstrap.realms;
import com.member.bootstrap.model.MemberModel;
import com.member.bootstrap.model.MemberPermissionModel;
import com.member.bootstrap.model.MemberTypeModel;
import com.member.bootstrap.services.MemberServiceInterface;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JdbcRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return JdbcRealm.class.getName();
    }
    @Autowired
    private MemberServiceInterface memberServiceInterface;

    /**
     * 授权
     * @param principals
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String memberName = (String) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> permissions = new HashSet<>();
        MemberModel memberModel = memberServiceInterface.login(memberName);
        if (memberModel==null){
            throw new UnknownAccountException();
        }
        List<MemberTypeModel> memberTypeModelList = memberModel.getMemberTypeModels();
        for (MemberTypeModel memberTypeModel : memberTypeModelList) {
            for (MemberPermissionModel permissionModel: memberTypeModel.getPermissionModels()){
                permissions.add(permissionModel.getPermission_name());
            }
        }
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        MemberModel memberModel = memberServiceInterface.login(username);
        if (memberModel == null){
            throw new UnknownAccountException();
        }
        String password = memberModel.getPassword();
        ByteSource solt = ByteSource.Util.bytes(username);
        return new SimpleAuthenticationInfo(username,password,solt,getName());
    }
}
