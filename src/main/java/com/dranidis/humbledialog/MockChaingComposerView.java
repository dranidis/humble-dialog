package com.dranidis.humbledialog;

import java.util.ArrayList;
import java.util.List;

public class MockChaingComposerView implements ChainComposerView {

    private List<Filter> selectionList;

    public MockChaingComposerView() {
        this.selectionList = new ArrayList<>();
    }

    public List<Filter> selectionList() {
        return selectionList;
    }

    @Override
    public void setSelectionList(List<Filter> filters) {
        this.selectionList = filters;
    }

}
