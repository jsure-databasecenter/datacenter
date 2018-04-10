package com.jsure.datacenter.serviceImpl;

        import com.jsure.datacenter.entitymodel.Role;
        import com.jsure.datacenter.mapper.RoleMapper;
        import com.jsure.datacenter.resultmodel.RoleResult;
        import com.jsure.datacenter.service.PermissionService;
        import com.jsure.datacenter.utils.BeanMapper;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/10
 * @Time: 15:44
 * I am a Code Man -_-!
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleResult queryPermission(Integer roleId) {
        Role permission = roleMapper.selectPermissionByRoleId(roleId);
        RoleResult result = new RoleResult();
        BeanMapper.copy(permission,result);
        return result;
    }
}
