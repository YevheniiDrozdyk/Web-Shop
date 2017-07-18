package com.epam.drozdyk.webshop.repository;

import com.epam.drozdyk.webshop.bean.filter.InstrumentFilter;
import com.epam.drozdyk.webshop.bean.model.Instrument;

import java.util.List;

public interface InstrumentRepository {

    Instrument getInstrument(String vendorCode);

    List<Instrument> getInstruments();

    List<Instrument> getInstruments(InstrumentFilter filter);

    Integer getInstrumentCount(InstrumentFilter filter);
}
