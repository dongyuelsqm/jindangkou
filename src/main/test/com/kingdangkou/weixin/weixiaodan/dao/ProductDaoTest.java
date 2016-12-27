package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.ColorEntity;
import com.kingdangkou.weixin.weixiaodan.entity.Product;
import com.kingdangkou.weixin.weixiaodan.entity.ProductQuantityEntity;
import com.kingdangkou.weixin.weixiaodan.entity.SizeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
@Transactional
@Rollback(true)
public class ProductDaoTest {

    @Autowired
    private ProductDao productDao;
    @Test
    public void testSave() throws Exception {
        Product product = new Product("name", "descriptive", 1.2f, "code", 10, "postal", "picture", "videos");
        productDao.save(product);
        product.addProductQuantity(new ProductQuantityEntity(product, new ColorEntity("green"), new SizeEntity("XXL"), 1));
        productDao.save(product);
    }
}