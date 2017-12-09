package com.johnny.cms.service;

import com.johnny.cms.domain.Category;
import com.johnny.cms.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getDepts() {
        return categoryMapper.selectByExample(null);
    }

}
