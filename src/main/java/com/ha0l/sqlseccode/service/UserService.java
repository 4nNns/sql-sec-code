package com.ha0l.sqlseccode.service;

import java.util.HashMap;

/**
 * @author ha0
 * @date 2022-0823
 */
public interface UserService {

    public HashMap selectInfoById(int userId);

    public HashMap selectInfoByName(String username);

}
