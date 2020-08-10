package com.sie.demo.service.impl;

import com.sie.demo.dao.PermissionDao;
import com.sie.demo.dao.UserDao;
import com.sie.demo.dto.LoginUser;
import com.sie.demo.model.User;

import com.sie.demo.util.Status;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Ivan
 * @description:
 * @date 2020-07-30 09:50:45
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.queryByName(username);
        if (user==null){
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        }else if (user.getStatus()== Status.DISABLED){
            throw new LockedException("账户失效，请联系管理员");
        }


        LoginUser loginUser = new LoginUser();

        BeanUtils.copyProperties(user,loginUser);

        loginUser.setPermissions(userDao.getUserPermissions(user.getId()));
        return loginUser;
    }
}
