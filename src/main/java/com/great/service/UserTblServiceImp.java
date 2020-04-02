package com.great.service;

import com.great.dao.UserDao;
import com.great.javabean.ObjectResult;
import com.great.javabean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserTblServiceImp <br/>
 * Description: <br/>
 * date: 2020/3/26 11:08<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Service
public class UserTblServiceImp {

    @Resource
    private UserDao userDao;


    public ObjectResult getUserTbl(Map<String, Object> map) {
        ObjectResult objectResult=new ObjectResult();

        objectResult.setCode(0);

        Integer num=userDao.getUserCount();
        List<User> users=userDao.getUserTbl(map);

        objectResult.setCount(num);
        objectResult.setData(users);
        return objectResult;
    }

    public User getUserMsg(Integer user_id) {
        return  userDao.getUserMsg(user_id);
    }

    @Transactional
    public void addUserByAdminAdd(User user){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String user_date = df.format(new Date());

        user.setUser_date(user_date);

        userDao.addUserByAdminAdd(user);
    }

    @Transactional
    public void updateUser(User user){
        userDao.updateUser(user);
    }

    @Transactional
    public void deleteUser(Integer user_id) {
        userDao.deleteUser(user_id);
    }
}
