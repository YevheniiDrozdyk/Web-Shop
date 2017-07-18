package com.epam.drozdyk.webshop.database;

public interface TransactionMethod<T> {

    T invoke();
}
