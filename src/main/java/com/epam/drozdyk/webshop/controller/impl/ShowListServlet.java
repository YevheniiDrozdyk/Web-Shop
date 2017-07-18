package com.epam.drozdyk.webshop.controller.impl;

import com.epam.drozdyk.webshop.bean.filter.InstrumentFilter;
import com.epam.drozdyk.webshop.bean.model.Category;
import com.epam.drozdyk.webshop.bean.model.Instrument;
import com.epam.drozdyk.webshop.bean.model.Producer;
import com.epam.drozdyk.webshop.controller.BaseServlet;
import com.epam.drozdyk.webshop.mapper.filter.InstrumentFilterMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.*;
import static com.epam.drozdyk.webshop.constant.Constant.JspName.LIST_JSP;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.SHOW_LIST_SERVLET_URL;

@WebServlet(SHOW_LIST_SERVLET_URL)
public class ShowListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InstrumentFilter instrumentFilter = new InstrumentFilterMapper().mapInstrumentFilter(req);
        List<Instrument> instruments = instrumentService.getInstruments(instrumentFilter);
        List<Category> categories = categoryService.getCategories();
        List<Producer> producers = producerService.getProducers();
        Integer pageCount = (instrumentService.getInstrumentCount(instrumentFilter) - 1) / instrumentFilter.getItemsLimit() + 1;

        req.setAttribute(INSTRUMENT_FILTER_ATTR, instrumentFilter);
        req.setAttribute(INSTRUMENTS_LIST_ATTR, instruments);
        req.setAttribute(CATEGORIES_LIST_ATTR, categories);
        req.setAttribute(PRODUCERS_LIST_ATTR, producers);
        req.setAttribute(PAGE_COUNT_ATTR, pageCount);

        forwardToPage(LIST_JSP, req, resp);
    }
}
