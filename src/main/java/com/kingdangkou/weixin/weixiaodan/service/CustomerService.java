package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.CustomerDao;
import com.kingdangkou.weixin.weixiaodan.entity.Customer;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dongy on 2016-11-20.
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    public Customer get(HttpServletRequest request){
        String openID = request.getParameter("openID");
        return customerDao.get(openID);
    }

    public Result save(Customer customer){
        customerDao.save(customer);
        return new Result(true, "");
    }
}
