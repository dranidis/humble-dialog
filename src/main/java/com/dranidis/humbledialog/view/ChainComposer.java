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
        if (i >= 0 && i < selectionList.size()) {
            this.chainList.add(selectionList.get(i));
            view.setChainList(chainList);
        }
    }

    public void remove(int i) {
        if (i >= 0 && i < this.chainList.size()) {
            this.chainList.remove(i);
            view.setChainList(chainList);
        }
    }

    public void removeAll() {
        this.chainList.clear();
        view.setChainList(chainList);
    }

}
