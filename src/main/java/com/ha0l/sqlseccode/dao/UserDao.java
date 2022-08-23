package com.ha0l.sqlseccode.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

/**
 * @author ha0
 * @date 2022-0823
 */
@Mapper
public interface UserDao {

    public HashMap selectUserById(int userId);

    public HashMap selectUserByName(@Param("username") String username);

}
