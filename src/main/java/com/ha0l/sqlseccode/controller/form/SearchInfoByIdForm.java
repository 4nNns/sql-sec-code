package com.ha0l.sqlseccode.controller.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author ha0
 * @date 2022-0823
 */
@Data
public class SearchInfoByIdForm {

    @NotNull(message = "userId不能为空")
    @Min(value = 1, message = "userId不能小于1")
    private Integer userId;
}
