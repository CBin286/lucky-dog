package com.great.service;

import com.great.dao.AdminDao;
import com.great.javabean.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * ClassName: SystemLogServiceImp <br/>
 * Description: <br/>
 * date: 2020/3/25 23:28<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Service
public class SystemLogServiceImp {

    @Resource
    private AdminDao adminDao;

    @Transactional
    public void insert(SystemLog log) {

        adminDao.insertLog(log);

    }

}
