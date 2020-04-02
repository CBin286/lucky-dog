package com.great.service;

import com.great.dao.DocumentDao;
import com.great.dao.UserDao;
import com.great.javabean.*;
import org.apache.jasper.tagplugins.jstl.core.Out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserServiceImp <br/>
 * Description: <br/>
 * date: 2020/3/24 23:32<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Service
public class UserServiceImp {

    @Resource
    private UserDao userDao;
    @Resource
    private DocumentDao documentDao;

    public User login(String id,String pwd){

        Map<String,String> map=new HashMap<>();
        map.put("id",id);
        map.put("pwd",pwd);
        User user=userDao.login(map);

        return user;
    }

    public boolean testUserAccount(String user_account){

        boolean flag=false;

        User user=userDao.testUserAccount(user_account);

        if (user!=null){
            flag=true;
        }

        return flag;
    }

    @Transactional
    public void addUserByReg(User user){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String user_date = df.format(new Date());

        user.setUser_date(user_date);
        userDao.addUserByReg(user);

    }

    //查询全部文档类型
    public List<DocumentType>findDocumentType(){
        return documentDao.findDocumentType();
    }

    /**
     * 插入文档信息
     * @param document
     * @return
     */
    @Transactional
    public Integer insertInfByUid (Document document){
        return documentDao.insertInfByUid(document);
    }

    public ObjectResult getScore(Integer user_id,Integer page, Integer limit) {

        Map<String,Integer> map=new HashMap<>();

        int maxlimit=limit;
        int minlimit=(page-1)*limit;

        map.put("user_id",user_id);
        map.put("maxlimit",maxlimit);
        map.put("minlimit",minlimit);

        List<Score> scores=userDao.getScore(map);
        Integer num=userDao.getScoreNum(map);

        //System.out.println(scores);

        return new ObjectResult(0,num,scores);
    }

    public ObjectResult getDocumentTbl(Integer page, Integer limit, String document_head) {

        Map<String,Object> map=new HashMap<>();

        int maxlimit=limit;
        int minlimit=(page-1)*limit;

        map.put("document_head",document_head);
        map.put("maxlimit",maxlimit);
        map.put("minlimit",minlimit);

        List<Document> documents=documentDao.getDocumentTbl(map);
        Integer num=documentDao.getDocumentNum(map);

        //System.out.println(documents);

        return new ObjectResult(0,num,documents);
    }

    public ObjectResult getUserDocumentTbl(Map<String, Object> map) {

        List<Document> documents=documentDao.getUserDocumentTbl(map);
        Integer num=documentDao.getUserDocumentTblNum(map);

        //System.out.println(documents);

        return new ObjectResult(0,num,documents);
    }

    public Integer checkUserScore(Integer user_id, Double document_integral) {
        Map<String,Object> map=new HashMap<>();

        map.put("user_id",user_id);
        map.put("document_integral",document_integral);

        return userDao.checkUserScore(map);

    }
}
