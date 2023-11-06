package com.dranidis.humbledialog.model;

import java.util.ArrayList;
import java.util.List;

import com.dranidis.humbledialog.ReverbFilter;

public class FilterRepository {

    public List<Filter> getFilters() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        filters.add(new EchoFilter());
        return filters;
    }

}
