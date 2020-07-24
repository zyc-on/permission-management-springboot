package com.sie.demo.service;

import com.sie.demo.model.User;
import com.sie.demo.util.query.UserQueryParams;
import com.sie.demo.util.ResultJson;


public interface UserService {
    ResultJson getUserByName(String username);

    ResultJson getUsersByPage(Integer offset, Integer limit);

    ResultJson insertUser(User user);

    ResultJson updateUser(User user);

    ResultJson deleteById(Integer id);

    ResultJson deleteUsersByIds(Integer[] ids);

    ResultJson getUserById(Integer id);

    ResultJson getUsers(UserQueryParams params);

    ResultJson queryUsers(UserQueryParams params);

    ResultJson getUserRoles(Integer userId);

    ResultJson setUserRoles(Integer userId,Integer[] roles);

}
