package com.epam.drozdyk.webshop.repository;

import com.epam.drozdyk.webshop.bean.model.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> getCategories();
}
