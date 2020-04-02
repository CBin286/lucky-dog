package com.great.service;

import com.great.dao.AdminDao;
import com.great.javabean.Admin;
import com.great.javabean.Document;
import com.great.javabean.Menu;
import com.great.javabean.ObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * ClassName: AdminServiceImp <br/>
 * Description: <br/>
 * date: 2020/3/24 19:54<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Service
public class AdminServiceImp {

    @Resource
    private AdminDao adminDao;

    @Transactional
    public Admin login(String id,String pwd) {

        Admin admin=null;

        Map<String,String> map=new HashMap<>();

        map.put("id",id);
        map.put("pwd",pwd);

        admin= adminDao.adminLogin(map);


        return admin;

    }

    @Transactional
    public Map<String, List<Menu>> getMenu(Admin admin)  {

        Admin adminAndRole=adminDao.getAdminAndRole(admin.getAdminId());

        List<Menu> menus= adminDao.getMenu(adminAndRole.getRole().getRole_id());

        List<Menu> fatherMenu=new ArrayList<>();

        List<Menu> chileMenu=new ArrayList<>();

        Map<String,List<Menu>> map =new LinkedHashMap<>();
        for(int i=0;i<menus.size();i++){
            if (menus.get(i).getMenu_father()==0){
                fatherMenu.add(menus.get(i));
            }else {
                chileMenu.add(menus.get(i));
            }
        }

        for(int i=0;i<fatherMenu.size();i++){
            List<Menu> s=new ArrayList<>();
            for(int j=0;j<chileMenu.size();j++){
                if (chileMenu.get(j).getMenu_father()==fatherMenu.get(i).getMenu_id()){
                    s.add(chileMenu.get(j));
                    //System.out.println(chileMenu.get(j));
                }
            }
            map.put(fatherMenu.get(i).getMenu_name(),s);
        }

        return map;

    }

    public ObjectResult documentReview(Map<String, Object> infMap) {

        List<Document> documents=adminDao.findDocumentByUid(infMap);
        Integer num =adminDao.findDocumentCount(infMap);

        return new ObjectResult(0,num,documents);

    }
}
