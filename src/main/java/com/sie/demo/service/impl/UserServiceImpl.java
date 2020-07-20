package com.sie.demo.service.impl;

import com.sie.demo.dao.UserDao;
import com.sie.demo.model.User;
import com.sie.demo.service.UserService;
import com.sie.demo.util.query.UserQueryParams;
import com.sie.demo.util.ResultJson;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserByName(String username) {
        return userDao.queryByName(username);
    }

    @Override
    public ResultJson getUsersByPage(Integer offset, Integer limit) {
        return new ResultJson(userDao.getUserCount(), userDao.getUsersByPage(offset, limit));
    }

    @Override
    public int insertUser(User user) {
        return userDao.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
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
}
