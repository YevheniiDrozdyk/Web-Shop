package com.epam.drozdyk.webshop.service.impl;

import com.epam.drozdyk.webshop.bean.model.Category;
import com.epam.drozdyk.webshop.database.TransactionHandler;
import com.epam.drozdyk.webshop.repository.CategoryRepository;
import com.epam.drozdyk.webshop.service.CategoryService;

import java.util.List;

public class DefaultCategoryService implements CategoryService {
    private final TransactionHandler transactionHandler;
    private final CategoryRepository categoryRepository;

    public DefaultCategoryService(TransactionHandler transactionHandler, CategoryRepository categoryRepository) {
        this.transactionHandler = transactionHandler;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return transactionHandler.doInTransaction(categoryRepository::getCategories);
    }
}
