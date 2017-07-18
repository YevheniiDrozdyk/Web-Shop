package com.epam.drozdyk.webshop.service.impl;

import com.epam.drozdyk.webshop.bean.filter.InstrumentFilter;
import com.epam.drozdyk.webshop.bean.model.Instrument;
import com.epam.drozdyk.webshop.database.TransactionHandler;
import com.epam.drozdyk.webshop.repository.InstrumentRepository;
import com.epam.drozdyk.webshop.service.InstrumentService;

import java.util.List;

public class DefaultInstrumentService implements InstrumentService {
    private final TransactionHandler transactionHandler;
    private final InstrumentRepository instrumentRepository;

    public DefaultInstrumentService(TransactionHandler transactionHandler, InstrumentRepository instrumentRepository) {
        this.transactionHandler = transactionHandler;
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public Instrument getInstrument(String vendorCode) {
        return transactionHandler.doInTransaction(() ->
                instrumentRepository.getInstrument(vendorCode));
    }

    @Override
    public List<Instrument> getInstruments() {
        return transactionHandler.doInTransaction(instrumentRepository::getInstruments);
    }

    @Override
    public List<Instrument> getInstruments(InstrumentFilter instrumentFilter) {
        return transactionHandler.doInTransaction(() ->
                instrumentRepository.getInstruments(instrumentFilter));
    }

    @Override
    public Integer getInstrumentCount(InstrumentFilter filter) {
        return transactionHandler.doInTransaction(() ->
                instrumentRepository.getInstrumentCount(filter));
    }
}
