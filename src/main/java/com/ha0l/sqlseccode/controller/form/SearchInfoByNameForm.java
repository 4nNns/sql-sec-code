package com.ha0l.sqlseccode.controller.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author ha0
 * @date 2022-0823
 */
@Data
public class SearchInfoByNameForm {

    @NotNull(message = "username不能为空")
    private String username;

}
