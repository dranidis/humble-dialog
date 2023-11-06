package com.dranidis.humbledialog.view;

import java.util.ArrayList;
import java.util.List;

import com.dranidis.humbledialog.model.Filter;

public class ChainComposer {

    private ChainComposerView view;

    private List<Filter> selectionList;
    private List<Filter> chainList;
    private int selectedIndex;
    private int chainSelectedIndex;

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
        this.selectedIndex = -1;
        this.chainSelectedIndex = -1;
        this.selectFilter(selectedIndex);
        this.selectChainFilter(chainSelectedIndex);
    }

    public void add() {
        int i = selectedIndex;
        if (i >= 0 && i < selectionList.size()) {
            this.chainList.add(selectionList.get(i));
            view.setChainList(chainList);
        }
        // deselect the filter in the selection list
        selectedIndex = -1;
        view.setSelectedIndex(selectedIndex);
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

    public void selectFilter(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        view.setSelectedIndex(selectedIndex);
        view.setAddButtonEnabled(selectedIndex >= 0);
    }

    public void selectChainFilter(int selectedIndex) {
        this.chainSelectedIndex = selectedIndex;
        view.setRemoveButtonEnabled(selectedIndex >= 0);
        view.setMoveUpButtonEnabled(selectedIndex >= 1);
        view.setMoveDownButtonEnabled(selectedIndex >= 0 && selectedIndex < chainList.size() - 1);
    }

    public void moveUp() {
        moveUp(chainSelectedIndex);
    }

    public void moveDown() {
        moveDown(chainSelectedIndex);
    }

    private void moveUp(int i) {
        int oldChainSelectedIndex = chainSelectedIndex;

        if (i > 0 && i < this.chainList.size()) {
            Filter filter = this.chainList.get(i);
            this.chainList.remove(i);
            this.chainList.add(i - 1, filter);
            view.setChainList(chainList);
        }
        this.chainSelectedIndex = oldChainSelectedIndex - 1;
        view.setChainSelectedIndex(chainSelectedIndex);
    }

    private void moveDown(int selectedIndex) {
        int oldChainSelectedIndex = chainSelectedIndex;

        if (selectedIndex >= 0 && selectedIndex < this.chainList.size() - 1) {
            Filter filter = this.chainList.get(selectedIndex);
            this.chainList.remove(selectedIndex);
            this.chainList.add(selectedIndex + 1, filter);
            view.setChainList(chainList);
        }
        this.chainSelectedIndex = oldChainSelectedIndex + 1;
        view.setChainSelectedIndex(chainSelectedIndex);
    }

}
