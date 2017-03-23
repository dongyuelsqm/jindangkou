package com.kingdangkou.weixin.weixiaodan.controller.website;

import com.kingdangkou.weixin.weixiaodan.controller.TestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dongy on 2017-03-20.
 */
public class StoreInfoControllerTest extends TestBase<StoreInfoController>{

    @Test
    public void update() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/website/storekeeperinfo/add").
                param("username", "username").
                param("phone", "123313123").
                param("QQ", "123456").
                param("email", "dongyue@gmail.com").
                param("storeName", "storename").
                param("address", "{\"address\":\"xihu\",\"cityName\":\"hangzhou\",\"expAreaName\":\"xihuhhhhh\",\"openID\":\"ssss\",\"provinceName\":\"zhejiang\"}").
                param("tel", "456313168").
                param("picture", "picture")).andDo(print());
        resultActions.andExpect(status().isOk());
    }
}