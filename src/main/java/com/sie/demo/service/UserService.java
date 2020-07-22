package com.sie.demo.service;

import com.sie.demo.model.User;
import com.sie.demo.util.query.UserQueryParams;
import com.sie.demo.util.ResultJson;


public interface UserService {
    User getUserByName(String username);

    ResultJson getUsersByPage(Integer offset, Integer limit);

    int insertUser(User user);

    int updateUser(User user);

    boolean deleteById(Integer id);

    ResultJson getUserById(Integer id);

    ResultJson getUsers(UserQueryParams params);

    ResultJson queryUsers(UserQueryParams params);
}
