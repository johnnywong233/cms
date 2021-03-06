package com.johnny.cms.mapper;

import com.johnny.cms.domain.Product;
import com.johnny.cms.domain.ProductExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    int countByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    int deleteByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    @Delete({
            "delete from `product`",
            "where `pro_id` = #{proId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer proId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    @Insert({
            "insert into `product` (`pro_id`, `pro_name`, ",
            "`pro_state`, `pro_no`, `pro_price`, ",
            "`pro_number`, `d_id`)",
            "values (#{proId,jdbcType=INTEGER}, #{proName,jdbcType=VARCHAR}, ",
            "#{proState,jdbcType=CHAR}, #{proNo,jdbcType=VARCHAR}, #{proPrice,jdbcType=VARCHAR}, ",
            "#{proNumber,jdbcType=VARCHAR}, #{dId,jdbcType=INTEGER})"
    })
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    int insertSelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    List<Product> selectByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    @Select({
            "select",
            "`pro_id`, `pro_name`, `pro_state`, `pro_no`, `pro_price`, `pro_number`, `d_id`",
            "from `product`",
            "where `pro_id` = #{proId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Product selectByPrimaryKey(Integer proId);

    //TODO
    List<Product> selectByExampleWithCate(ProductExample example);

    Product selectByPrimaryKeyWithCate(Integer proId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    @Update({
            "update `product`",
            "set `pro_name` = #{proName,jdbcType=VARCHAR},",
            "`pro_state` = #{proState,jdbcType=CHAR},",
            "`pro_no` = #{proNo,jdbcType=VARCHAR},",
            "`pro_price` = #{proPrice,jdbcType=VARCHAR},",
            "`pro_number` = #{proNumber,jdbcType=VARCHAR},",
            "`d_id` = #{dId,jdbcType=INTEGER}",
            "where `pro_id` = #{proId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Product record);
}