package com.ha0l.sqlseccode.controller;

import com.ha0l.sqlseccode.config.R;
import com.ha0l.sqlseccode.controller.form.SearchInfoByIdForm;
import com.ha0l.sqlseccode.controller.form.SearchInfoByNameForm;
import com.ha0l.sqlseccode.dao.UserDao;
import com.ha0l.sqlseccode.pojo.User;
import com.ha0l.sqlseccode.service.UserService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.sql.*;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.out;

/**
 * @author ha0
 * @date 2022-0823
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    private static String driver = "com.mysql.jdbc.Driver";

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @PostMapping("/searchInfoById")
    public R searchInfoById(@Valid @RequestBody SearchInfoByIdForm form) {
        HashMap map = userService.selectInfoById(form.getUserId());
        return R.ok(map);
    }

    @GetMapping("/jdbc/searchUserByName_JDBC")
    public String searchUserByName_JDBC(@RequestParam("username") String username) {
        StringBuilder result = new StringBuilder();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                out.println("Connect to database successfully.");
            }
            Statement statement = con.createStatement();
            String sql = "select * from users where username = '" + username + "'";
            logger.info(sql);
            ResultSet rs = statement.executeQuery(sql);
//            safe-sec-code
//            String sql = "select * from users where username = ?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, username);
            while (rs.next()) {
                String res_name = rs.getString("username");
                String res_pwd = rs.getString("password");
                String info = String.format("%s: %s\n", res_name, res_pwd);
                result.append(info);
                logger.info(info);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            logger.error("Sorry,can`t find the Driver!");
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        return result.toString();
    }

    @PostMapping("/mybatis/searchInfoByName")
    public R searchInfoByName(@Valid @RequestBody SearchInfoByNameForm form) {
        HashMap map = userService.selectInfoByName(form.getUsername());
        return R.ok(map);
    }

    /**
     * 常规SQL注入（MyBatis）
     * @param username
     * @return
     */
    @GetMapping("/mybatis/searchUserByName")
    public List<User> searchUserByName(@RequestParam("username") String username) {
        return userDao.selectUserByName(username);
    }

    /**
     * like相关SQL注入（MyBatis）
     * @param username
     * @return
     */
    @GetMapping("/mybatis/searchLikeUserByName")
    public List<User> searchLikeUserByName(@RequestParam("username") String username) {
        return userDao.likeUserByName(username);
    }

    /**
     * order by相关SQL注入（MyBatis）
     * @param sort
     * @return
     */
    @GetMapping("/mybatis/searchUserByOrder")
    public List<User> searchUserByOrder(@RequestParam("sort") String sort) {
        return userDao.selectOrder(sort);
    }

    @GetMapping("/hibernate/searchUserByName_HQL")
    public void searchUserByName_HQL(@RequestParam("username") String username) {
        Configuration conf = new Configuration().configure();
        SessionFactory sessionFactory = conf.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User user= session.get(User.class,1);
        String sql="from com.ha0l.sqlseccode.pojo.User where username=:username ";
        Query query=session.createQuery(sql).setString("username",username);
        String sql2="from com.ha0l.sqlseccode.pojo.User where username=" + username;
        Query query2=session.createQuery(sql2);
        tx.commit();
        session.close();
    }
}
