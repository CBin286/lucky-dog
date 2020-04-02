package com.great.dao;

import com.great.javabean.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ClassName: AdminDao <br/>
 * Description: <br/>
 * date: 2020/3/24 19:54<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Mapper
public interface AdminDao {
    Admin adminLogin(Map<String, String> map);

    Admin getAdminAndRole(Integer adminId);

    List<Menu> getMenu(int role_id);

    void insertLog(SystemLog log);

    List<Menu> getAllMenu();

    List<Menu> getRoleMenu(Integer roleId);

    void deleteMenuByRoleId(Integer roleId);

    void insertMenuAndRoleId(Map<String, Object> map);

    List<Role> getAllRole();

    List<Document> findDocumentByUid(Map<String, Object> infMap);

    Integer findDocumentCount(Map<String, Object> infMap);
}
