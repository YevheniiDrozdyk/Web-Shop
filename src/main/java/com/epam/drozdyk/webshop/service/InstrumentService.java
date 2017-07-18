package com.epam.drozdyk.webshop.service;

import com.epam.drozdyk.webshop.bean.filter.InstrumentFilter;
import com.epam.drozdyk.webshop.bean.model.Instrument;

import java.util.List;

public interface InstrumentService {

    Instrument getInstrument(String vendorCode);

    List<Instrument> getInstruments();

    List<Instrument> getInstruments(InstrumentFilter instrumentFilter);

    Integer getInstrumentCount(InstrumentFilter filter);
}
