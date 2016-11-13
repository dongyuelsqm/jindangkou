package com.kingdangkou.weixin.weixiaodan.controller;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by dongy on 2016-11-13.
 */
@Controller
@RequestMapping("/Order")
public class TakeNewOrder {
    @Autowired
    private BasicDataSource dataSource;
    @Inject
    public TakeNewOrder(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM product_order WHERE id = " + id);
        while (resultSet.next()){
            response.getWriter().print(resultSet.getString("address_id"));
        }
    }
}
