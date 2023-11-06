package com.dranidis.humbledialog.view;

import java.util.ArrayList;
import java.util.List;

import com.dranidis.humbledialog.model.Filter;

public class MockChainComposerView implements ChainComposerView {

    private List<Filter> selectionList;
    private List<Filter> chainList;

    public MockChainComposerView() {
        this.selectionList = new ArrayList<>();
        this.chainList = new ArrayList<>();
    }

    public List<Filter> selectionList() {
        return selectionList;
    }

    @Override
    public void setSelectionList(List<Filter> filters) {
        this.selectionList = filters;
    }

    public List<Filter> composedFilter() {
        return chainList;
    }

    @Override
    public void setChainList(List<Filter> chain) {
        this.chainList = chain;
    }
}
