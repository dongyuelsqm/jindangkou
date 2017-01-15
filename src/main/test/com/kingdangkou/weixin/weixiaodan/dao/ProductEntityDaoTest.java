package com.kingdangkou.weixin.weixiaodan.dao;

import com.kingdangkou.weixin.weixiaodan.entity.ColorEntity;
import com.kingdangkou.weixin.weixiaodan.entity.ProductEntity;
import com.kingdangkou.weixin.weixiaodan.entity.StorageEntity;
import com.kingdangkou.weixin.weixiaodan.entity.SizeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spitter-servlet.xml")
@Transactional
@Rollback(true)
public class ProductEntityDaoTest {

    @Autowired
    private ProductDao productDao;
    @Test
    public void testSave() throws Exception {
        ProductEntity productEntity = new ProductEntity("name", "descriptive", 1.2f, "code", 10, "postal", "picture", "videos");
        productDao.save(productEntity);
        productEntity.addStorage(new StorageEntity(productEntity, new ColorEntity("green"), new SizeEntity("XXL"), 1));
        productDao.save(productEntity);
    }

    @Test
    public void join_select() throws Exception {
        List<ProductEntity> byLabels = productDao.findByLabels("1");
        assertEquals(1, byLabels.size());

    }
}