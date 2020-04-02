package com.great.javabean;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: Admin <br/>
 * Description: <br/>
 * date: 2020/3/9 19:44<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Component
public class Admin {
    private Integer adminId;
    private String adminAccount;
    private String adminPwd;
    private Role role;
    private List<Menu> menus;
    public Admin() {
    }

    public Admin(Integer adminId, String adminAccount, String adminPwd, Role role, List<Menu> menus) {
        this.adminId = adminId;
        this.adminAccount = adminAccount;
        this.adminPwd = adminPwd;
        this.role = role;
        this.menus = menus;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminAccount='" + adminAccount + '\'' +
                ", adminPwd='" + adminPwd + '\'' +
                ", role=" + role +
                ", menus=" + menus +
                '}';
    }
}
