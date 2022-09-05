package com.ha0l.sqlseccode.pojo;

import lombok.Data;
import org.hibernate.annotations.Table;

import java.io.Serializable;

/**
 * @author ha0
 * @date 2022-0823
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

}
