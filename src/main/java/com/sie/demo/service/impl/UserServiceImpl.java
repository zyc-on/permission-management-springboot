package com.sie.demo.service.impl;

import com.sie.demo.dao.UserDao;
import com.sie.demo.model.User;
import com.sie.demo.service.UserService;
import com.sie.demo.util.query.PageHelper;
import com.sie.demo.util.query.UserQueryParams;
import com.sie.demo.util.ResultJson;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public ResultJson getUserByName(String username) {
        return new ResultJson(userDao.queryByName(username));
    }

    @Override
    public ResultJson getUsersByPage(Integer offset, Integer limit) {
        return new ResultJson(userDao.getUserCount(), userDao.getUsersByPage(offset, limit));
    }

    @Override
    public ResultJson insertUser(User user) {
        userDao.insert(user);
        return ResultJson.success();
    }

    @Override
    public ResultJson updateUser(User user) {
        userDao.update(user);
        return ResultJson.success();
    }

    @Override
    public ResultJson deleteById(Integer id) {
        userDao.deleteById(id);
        return ResultJson.success();
    }

    public ResultJson deleteUsersByIds(Integer[] ids){
        for (Integer id : ids) {
            userDao.deleteById(id);
        }
        return ResultJson.success();
    }

    @Override
    public ResultJson getUserById(Integer id) {
        System.out.println(userDao.queryById(id).getCreateTime());
        return new ResultJson(userDao.queryById(id));
    }

    @Override
    public ResultJson getUsers(UserQueryParams params) {
        return new ResultJson(userDao.getUsersCount(params),userDao.getUsers(params));
    }

    @Override
    public ResultJson queryUsers(UserQueryParams params) {
        params.setOffset(PageHelper.countOffset(params.getPage(),params.getLimit()));
        List<List<?>> list = userDao.queryUsers(params);
        Integer total = (Integer) list.get(1).get(0);
        return new ResultJson(total,list.get(0));
    }

    @Override
    public ResultJson getUserRoles(Integer userId) {
        return new ResultJson(userDao.getUserRoles(userId));
    }

    @Override
    public ResultJson setUserRoles(Integer userId, Integer[] roleIds) {
        userDao.resetUserRole(userId);
        for (Integer roleId : roleIds) {
            userDao.setUserRole(userId,roleId);
        }
        return ResultJson.success();
    }
}
