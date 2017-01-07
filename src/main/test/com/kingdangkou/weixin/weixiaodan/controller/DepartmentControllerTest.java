package com.kingdangkou.weixin.weixiaodan.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-01-07.
 */
public class DepartmentControllerTest extends TestBase<DepartmentController>{

    @Autowired
    private DepartmentController departmentController;

    @Override
    protected DepartmentController getController() {
        return departmentController;
    }

    @Test
    public void list() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/department/list")).andDo(print());
        resultActions.andExpect(status().isOk());
    }

}