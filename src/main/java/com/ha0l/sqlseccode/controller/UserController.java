package com.ha0l.sqlseccode.controller;

import com.ha0l.sqlseccode.config.R;
import com.ha0l.sqlseccode.controller.form.SearchInfoByIdForm;
import com.ha0l.sqlseccode.controller.form.SearchInfoByNameForm;
import com.ha0l.sqlseccode.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * @author ha0
 * @date 2022-0823
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/searchInfoById")
    public R searchInfoById(@Valid @RequestBody SearchInfoByIdForm form) {
        HashMap map = userService.selectInfoById(form.getUserId());
        return R.ok(map);
    }

    @PostMapping("/searchInfoByName")
    public R searchInfoByName(@Valid @RequestBody SearchInfoByNameForm form) {
        HashMap map = userService.selectInfoByName(form.getUsername());
        return R.ok(map);
    }
}
