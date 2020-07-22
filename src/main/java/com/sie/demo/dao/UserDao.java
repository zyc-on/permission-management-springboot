package com.sie.demo.dao;

import com.sie.demo.model.User;
import com.sie.demo.util.query.UserQueryParams;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from user t where t.username = #{username}")
    User queryByName(String username);

    @Select("select * from user t order by t.id limit #{offset},#{limit}")
    List<User> getUsersByPage(@Param("offset") Integer offset,@Param("limit") Integer limit);

    @Select("select count(*) from user t")
    int getUserCount();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user(username,password,name,gender,tel,email,status,description,createTime,updateTime) values(#{username},#{password},#{name},#{gender},#{tel},#{email},#{status},#{description},now(),now())")
    int insert(User user);

    int update(User user);

    int deleteById(Integer id);

    User queryById(Integer id);

    List<User> getUsers(UserQueryParams params);
    int getUsersCount(UserQueryParams params);

    List<List<?>> queryUsers(UserQueryParams params);


}
