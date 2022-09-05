package com.ha0l.sqlseccode.dao;

import com.ha0l.sqlseccode.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * @author ha0
 * @date 2022-0823
 */
@Mapper
public interface UserDao {

    public HashMap selectUserById(int userId);

    @Select("select * from tb_users where username = '${username}'")
    public List<User> selectUserByName(@Param("username") String username);

    public HashMap selectUsersByName(@Param("username") String username);

    public List<User> likeUserByName(String username);

    public List<User> selectOrder(@Param("order") String order);

}
