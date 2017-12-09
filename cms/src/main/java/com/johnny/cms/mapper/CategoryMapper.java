package com.johnny.cms.mapper;

import com.johnny.cms.domain.Category;
import com.johnny.cms.domain.CategoryExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbggenerated
     */
    int countByExample(CategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbggenerated
     */
    int deleteByExample(CategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbggenerated
     */
    @Delete({
            "delete from `category`",
            "where `cate_id` = #{cateId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer cateId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbggenerated
     */
    @Insert({
            "insert into `category` (`cate_id`, `cate_name`)",
            "values (#{cateId,jdbcType=INTEGER}, #{cateName,jdbcType=VARCHAR})"
    })
    int insert(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbggenerated
     */
    int insertSelective(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbggenerated
     */
    List<Category> selectByExample(CategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbggenerated
     */
    @Select({
            "select",
            "`cate_id`, `cate_name`",
            "from `category`",
            "where `cate_id` = #{cateId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Category selectByPrimaryKey(Integer cateId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbggenerated
     */
    @Update({
            "update `category`",
            "set `cate_name` = #{cateName,jdbcType=VARCHAR}",
            "where `cate_id` = #{cateId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Category record);
}