package com.sie.demo.dao;

import com.sie.demo.model.Permission;
import com.sie.demo.util.PermissionTree;
import com.sie.demo.util.query.PermissionQueryParams;
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

    List<Permission> getRootPermissions();

    List<Permission> getChildPermissions(Integer parentId);

    List<List<?>> queryPermissions(PermissionQueryParams params);

    List<PermissionTree> getPermissionTree();

    int updateStatus(Integer status,Integer id);

    List<Permission> getAsideMenus();

}