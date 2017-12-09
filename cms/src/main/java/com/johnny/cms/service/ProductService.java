package com.johnny.cms.service;

import com.johnny.cms.domain.Product;
import com.johnny.cms.domain.ProductExample;
import com.johnny.cms.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Johnny
 * Date: 2017/11/29
 * Time: 下午2:48
 */
@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    /**
     * 查询所有
     *
     * @return list of product
     */
    public List<Product> getAll() {
        return productMapper.selectByExampleWithCate(null);
    }

    /**
     * 数据保存
     *
     * @param product product
     */
    public void saveEmp(Product product) {
        productMapper.insertSelective(product);
    }

    /**
     * 检验商品是否存在
     *
     * @param proName name
     * @return true：代表当前姓名可用   false：不可用
     */
    public boolean checkExist(String proName) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andProNameEqualTo(proName);
        long count = productMapper.countByExample(example);
        return count != 0;
    }

    /**
     * 根据传参搜索，此处只能搜索商品名
     *
     * @param proName 待搜索字段
     */
    public List<Product> search(String proName) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        proName = "%" + proName + "%";
        criteria.andProNameLike(proName);
        return productMapper.selectByExample(example);
    }

    /**
     * 根据id查询商品
     *
     * @param id id
     * @return Product
     */
    public Product getEmp(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    /**
     * 数据更新
     *
     * @param product product
     */
    public void updateEmp(Product product) {
//        productMapper.updateByPrimaryKey(product);
        productMapper.updateByPrimaryKeySelective(product);
    }

    /**
     * 商品删除
     *
     * @param id id
     */
    public void deleteEmp(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andProIdIn(ids);
        productMapper.deleteByExample(example);
    }

}
