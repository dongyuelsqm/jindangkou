package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.ColorDao;
import com.kingdangkou.weixin.weixiaodan.dao.ProductDao;
import com.kingdangkou.weixin.weixiaodan.dao.SizeDao;
import com.kingdangkou.weixin.weixiaodan.entity.*;
import com.kingdangkou.weixin.weixiaodan.exception.ParaInValidException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by apple on 17/2/3.
 */
@Service
public class SubOrderService {
    @Autowired
    private ColorDao colorDao;
    @Autowired
    private SizeDao sizeDao;
    @Autowired
    private ProductDao productDao;
    public Set<SubOrder> convertToSubOrders(String items) throws ParaInValidException {
        Set<SubOrder> subOrders = new HashSet<SubOrder>();
        JSONArray array = JSONArray.fromObject(items);
        HashMap<Integer, ColorEntity> colors = colorDao.getColorMaps();
        HashMap<Integer, SizeEntity> sizes = sizeDao.getMap();
        for(Object obj: array){

            String product_id = ((JSONObject) obj).get("product_id").toString();
            int color_id = Integer.valueOf(((JSONObject) obj).get("color").toString());
            int size_id = Integer.valueOf(((JSONObject) obj).get("size").toString());
            Integer number = Integer.valueOf(((JSONObject) obj).get("number").toString());
            ProductEntity productEntity = productDao.get(product_id);
            ColorEntity colorEntity = colors.get(color_id);
            SizeEntity sizeEntity = sizes.get(size_id);

            if (productEntity == null){
                throw new ParaInValidException("invalid product id");
            }
            if (colorEntity == null){
                throw new ParaInValidException("invalid color id");
            }
            if (sizeEntity == null){
                throw new ParaInValidException("invalid size id");
            }

            SubOrder subOrder = new SubOrder(productEntity, colorEntity, sizeEntity, number);
            subOrders.add(subOrder);
        }
        return subOrders;
    }
}
