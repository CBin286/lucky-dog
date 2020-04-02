package com.great.dao;

import com.great.javabean.Score;
import com.great.javabean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ClassName: UserDao <br/>
 * Description: <br/>
 * date: 2020/3/24 23:32<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Mapper
public interface UserDao {

    User login(Map<String, String> map);

    User testUserAccount(String user_account);

    void addUserByReg(User user);

    Integer getUserCount();

    List<User> getUserTbl(Map<String, Object> map);

    User getUserMsg(Integer user_id);

    void addUserByAdminAdd(User user);

    void updateUser(User user);

    void deleteUser(Integer user_id);

    List<Score> getScore(Map<String,Integer> map);

    Integer getScoreNum(Map<String,Integer> map);

    Integer checkUserScore(Map<String, Object> map);
}
