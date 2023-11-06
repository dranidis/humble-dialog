package com.dranidis.humbledialog.model;

import java.util.ArrayList;
import java.util.List;

public class FilterRepository {

    public List<Filter> getFilters() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        filters.add(new EchoFilter());
        filters.add(new DistortionFilter());
        return filters;
    }

}
