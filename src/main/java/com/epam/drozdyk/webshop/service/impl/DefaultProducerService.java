package com.epam.drozdyk.webshop.service.impl;

import com.epam.drozdyk.webshop.bean.model.Producer;
import com.epam.drozdyk.webshop.database.TransactionHandler;
import com.epam.drozdyk.webshop.repository.ProducerRepository;
import com.epam.drozdyk.webshop.service.ProducerService;

import java.util.List;

public class DefaultProducerService implements ProducerService {
    private final TransactionHandler transactionHandler;
    private final ProducerRepository producerRepository;

    public DefaultProducerService(TransactionHandler transactionHandler, ProducerRepository producerRepository) {
        this.transactionHandler = transactionHandler;
        this.producerRepository = producerRepository;
    }

    @Override
    public List<Producer> getProducers() {
        return transactionHandler.doInTransaction(producerRepository::getProducers);
    }
}
