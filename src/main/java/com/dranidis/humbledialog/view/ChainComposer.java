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

        // triggers the initialization of the state of buttons
        // when nothing is selected in the two lists
        this.selectFilter(-1);
        this.selectChainFilter(-1);
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

    public void moveUp(int i) {
        if (i > 0 && i < this.chainList.size()) {
            Filter filter = this.chainList.get(i);
            this.chainList.remove(i);
            this.chainList.add(i - 1, filter);
            view.setChainList(chainList);
        }
    }

    public void moveDown(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < this.chainList.size() - 1) {
            Filter filter = this.chainList.get(selectedIndex);
            this.chainList.remove(selectedIndex);
            this.chainList.add(selectedIndex + 1, filter);
            view.setChainList(chainList);
        }
    }

    public void selectFilter(int selectedIndex) {
        view.setAddButtonEnabled(selectedIndex >= 0);
    }

    public void selectChainFilter(int selectedIndex) {
        view.setRemoveButtonEnabled(selectedIndex >= 0);
        view.setMoveUpButtonEnabled(selectedIndex >= 1);
        view.setMoveDownButtonEnabled(selectedIndex >= 0 && selectedIndex < chainList.size() - 1);
    }

}
