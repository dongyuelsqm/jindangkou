package com.kingdangkou.weixin.weixiaodan.controller.website;

import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.service.StoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongy on 2017-03-20.
 */
@Controller
@RequestMapping("/website/")
public class StoreInfoController {

    @Autowired
    private StoreInfoService storeInfoService;
    @RequestMapping(value = "/storekeeperinfo/add", method = RequestMethod.POST)
    public void update(@RequestParam("username") String username,
                       @RequestParam("phone") String phone,
                       @RequestParam("QQ") String qq,
                       @RequestParam("email") String email,
                       @RequestParam("name") String name,
                       @RequestParam("address") String address,
                       @RequestParam("tel") String tel,
                       @RequestParam("picture") String picture, HttpServletResponse response) throws IOException {
        Result update = storeInfoService.update(username, phone, qq, email, name, address, tel, picture);
        response.getWriter().print(update);
    }
}
