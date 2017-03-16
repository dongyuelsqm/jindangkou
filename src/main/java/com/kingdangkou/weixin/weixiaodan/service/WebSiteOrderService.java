package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.OrderDao;
import com.kingdangkou.weixin.weixiaodan.entity.Order;
import com.kingdangkou.weixin.weixiaodan.enums.OrderStateEnum;
import com.kingdangkou.weixin.weixiaodan.model.Result;
import com.kingdangkou.weixin.weixiaodan.tools.express.kuaidiniao.ExpressOrder;
import com.kingdangkou.weixin.weixiaodan.tools.express.kuaidiniao.KdApiEOrderDemo;
import com.kingdangkou.weixin.weixiaodan.tools.express.kuaidiniao.PayType;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dongy on 2017-03-14.
 */
@Service
public class WebSiteOrderService {
    @Autowired
    private KdApiEOrderDemo kdApi;
    @Autowired
    private OrderDao orderDao;
    public Result sendGoods(String id, String ship, String payType, String isNotify) throws Exception {
        Order order = orderDao.get(Order.class, id);
        String s = kdApi.orderOnlineByJson(new ExpressOrder(String.valueOf(order.getId()), ship, PayType.valueOf(payType).getValue(), 1, order.getAddress(), order.getAddress(), 0, Integer.valueOf(isNotify)));
        JSONObject object = JSONObject.fromObject(s);
        JSONObject order1 = (JSONObject) object.get("Order");
        String logisticCode = (String) order1.get("LogisticCode");
        order.setExpressNumber(logisticCode);
        order.setState(OrderStateEnum.IN_TRANSIT.getValue());
        orderDao.save(order);
        return new Result(true, object.get("PrintTemplate"));
    }
}
