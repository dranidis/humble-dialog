package com.dranidis.humbledialog;

import java.util.ArrayList;
import java.util.List;

public class ChainComposer {

    private List<Filter> filters;
    private MockChaingComposerView view;

    public ChainComposer(MockChaingComposerView view) {
        this.view = view;
        this.filters = new ArrayList<>();
    }

    public void initialize() {
        filters.add(new ReverbFilter());
        view.setSelectionList(filters);
    }

}
