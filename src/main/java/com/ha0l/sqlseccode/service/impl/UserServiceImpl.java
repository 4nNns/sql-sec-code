package com.ha0l.sqlseccode.service.impl;

import com.ha0l.sqlseccode.dao.UserDao;
import com.ha0l.sqlseccode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author ha0
 * @date 2022-0823
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public HashMap selectInfoById(int userId) {
        HashMap map = userDao.selectUserById(userId);
        return map;
    }

    @Override
    public HashMap selectInfoByName(String username) {
        HashMap map = userDao.selectUserByName(username);
        return map;
    }
}
