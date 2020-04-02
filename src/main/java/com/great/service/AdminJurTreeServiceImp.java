package com.great.service;

import com.great.dao.AdminDao;
import com.great.javabean.Menu;
import com.great.javabean.Role;
import com.great.javabean.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: AdminJurTreeServiceImp <br/>
 * Description: <br/>
 * date: 2020/3/29 22:04<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Service
public class AdminJurTreeServiceImp {

    @Resource
    private AdminDao adminDao;

    public List<Tree> getAllMenu(){

        List<Menu> menus=adminDao.getAllMenu();

        List<Tree> up=new ArrayList<>();
        List<Tree> down=new ArrayList<>();

        for(int i=0;i<menus.size();i++){

            if (menus.get(i).getMenu_father()==0){
                Tree tree=new Tree(menus.get(i).getMenu_name(),menus.get(i).getMenu_id(),null,menus.get(i).getMenu_father(),menus.get(i).getMenu_url());
                up.add(tree);
            }else {
                Tree tree=new Tree(menus.get(i).getMenu_name(),menus.get(i).getMenu_id(),null,menus.get(i).getMenu_father(),menus.get(i).getMenu_url());
                down.add(tree);
            }
        }

        for(int i=0;i<up.size();i++){
            List<Tree> t=new ArrayList<>();
            for(int j=0;j<down.size();j++){
                if (down.get(j).getFid()==up.get(i).getId()){
                    t.add(down.get(j));
                }
                up.get(i).setChildren(t);
            }
        }

        System.out.println(up);
        return up;
    }

    public List<Tree> getRoleMenu(Integer roleId) {

        List<Menu> menus=adminDao.getRoleMenu(roleId);

        List<Tree> up=new ArrayList<>();
        List<Tree> down=new ArrayList<>();

        for(int i=0;i<menus.size();i++){

            if (menus.get(i).getMenu_father()==0){
                Tree tree=new Tree(menus.get(i).getMenu_name(),menus.get(i).getMenu_id(),null,menus.get(i).getMenu_father(),menus.get(i).getMenu_url());
                up.add(tree);
            }else {
                Tree tree=new Tree(menus.get(i).getMenu_name(),menus.get(i).getMenu_id(),null,menus.get(i).getMenu_father(),menus.get(i).getMenu_url());
                down.add(tree);
            }
        }

        for(int i=0;i<up.size();i++){
            List<Tree> t=new ArrayList<>();
            for(int j=0;j<down.size();j++){
                if (down.get(j).getFid()==up.get(i).getId()){
                    t.add(down.get(j));
                }
                up.get(i).setChildren(t);
            }
        }

        System.out.println(up);
        return up;
    }

    @Transactional
    public void updateRoleMenu(Tree[] treex, Integer roleId) {

        List<Integer> num=new ArrayList<>();

        for (int i=0;i<treex.length;i++) {
            num.add(treex[i].getId());
            for (int j=0;j<treex[i].getChildren().size();j++){
                num.add(treex[i].getChildren().get(j).getId());
            }
        }
        Map<String ,Object> map=new HashMap<>();
        map.put("roleId",roleId);
        map.put("numList",num);

        //删除角色全部菜单
        adminDao.deleteMenuByRoleId(roleId);

        System.out.println(map);
        //添加
        adminDao.insertMenuAndRoleId(map);

    }

    public List<Role> getAllRole() {
        return adminDao.getAllRole();
    }
}
