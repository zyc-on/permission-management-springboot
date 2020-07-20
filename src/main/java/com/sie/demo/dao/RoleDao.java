package com.sie.demo.dao;



import com.sie.demo.model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface RoleDao {


    Role queryById(Integer id);


    List<Role> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);



    List<Role> queryAll(Role role);


    int insert(Role role);

    int update(Role role);


    int deleteById(Integer id);

    @Select("select count(*) from role t")
    int getRoleCount();

    int resetRolePermission(Integer roleId);

    int setRolePermission(Integer roleId,Integer permissionId);

}
