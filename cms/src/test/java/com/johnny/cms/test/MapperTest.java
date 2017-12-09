package com.johnny.cms.test;

import com.johnny.cms.domain.Product;
import com.johnny.cms.mapper.ProductMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Author: Johnny
 * Date: 2017/11/29
 * Time: 下午4:59
 */
@RunWith(SpringJUnit4ClassRunner.class)//使用junit4进行测试
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD() {
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        for (int i = 0; i < 10; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 7) + i;
            //错误4的发生原因
            //mapper.insertSelective(new Product());
//            mapper.insertSelective(new Product(10,"applePlus","M","54","88","11",1));
        }
        System.out.println("数据已生成");
    }
}