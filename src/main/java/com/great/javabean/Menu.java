package com.great.javabean;

import java.util.List;

/**
 * ClassName: Menu <br/>
 * Description: <br/>
 * date: 2020/3/16 16:28<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
public class Menu {
    private Integer menu_id;
    private String menu_name;
    private Integer menu_father;
    private String menu_url;
    private List<Menu> menus;

    public Menu() {

    }

    public Menu(Integer menu_id, String menu_name, Integer menu_father, String menu_url, List<Menu> menus) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.menu_father = menu_father;
        this.menu_url = menu_url;
        this.menus = menus;
    }

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public Integer getMenu_father() {
        return menu_father;
    }

    public void setMenu_father(Integer menu_father) {
        this.menu_father = menu_father;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu_id=" + menu_id +
                ", menu_name='" + menu_name + '\'' +
                ", menu_father=" + menu_father +
                ", menu_url='" + menu_url + '\'' +
                ", menus=" + menus +
                '}';
    }
}
