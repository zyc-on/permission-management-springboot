package com.sie.demo.dao;

import com.sie.demo.model.NameMapper;
import com.sie.demo.model.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    Permission queryById(Integer id);


    List<Permission> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);



    List<Permission> queryAll(Permission permission);


    int insert(Permission permission);


    int update(Permission permission);


    int deleteById(Integer id);

    @Select("select count(*) from permission t")
    int getPermissionsCount();

    @Select("select id,parentId,type,name from permission t")
    List<NameMapper> getIdAndNameMapper();

    List<Permission> getPermissionTree();

    List<Permission> getRootPermissions();

    List<Permission> getChildPermissions(Integer parentId);

}