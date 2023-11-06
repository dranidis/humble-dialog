package com.dranidis.humbledialog.view;

import java.util.ArrayList;
import java.util.List;

import com.dranidis.humbledialog.model.Filter;

public class ChainComposer {

    private List<Filter> selectionList;
    private List<Filter> chainList;
    private ChainComposerView view;

    public ChainComposer(ChainComposerView view, List<Filter> filters) {
        this.view = view;
        this.selectionList = filters;
        this.chainList = new ArrayList<>();
    }

    public void initialize() {
        view.setSelectionList(selectionList);
        view.setChainList(new ArrayList<>());
    }

    public void add(int i) {
        this.chainList.add(selectionList.get(i));
        view.setChainList(chainList);
    }

}
