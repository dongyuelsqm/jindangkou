package com.kingdangkou.weixin.weixiaodan.service;

import com.kingdangkou.weixin.weixiaodan.dao.ProductDao;
import com.kingdangkou.weixin.weixiaodan.entity.ProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spitter-servlet.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;
    @Test
    public void testSave() throws Exception {
        productService.save("name", "descriptive", 1.1f, "department", "code", 1, "true", "pic", "video", "quantity", "label");
        List<ProductEntity> productEntities = productDao.find();
        for (ProductEntity productEntity : productEntities){
            productEntity.getName().equals("name");
        }
    }
}