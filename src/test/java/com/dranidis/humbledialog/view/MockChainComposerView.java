package com.dranidis.humbledialog.view;

import java.util.ArrayList;
import java.util.List;

import com.dranidis.humbledialog.model.Filter;

/**
 * Mock implementation of the ChainComposerView interface.
 * <p>
 * The mock implementation exactly as the real implementation of the
 * ChainComposerView interface, does not contain any logic. It consists only of
 * getters and setters.
 * <p>
 * The state representation of the mock implementation reflects the state of the
 * real implementation. It contains the data in the lists and the state of the
 * buttons.
 */
public class MockChainComposerView implements ChainComposerView {

    private List<Filter> selectionList;
    private List<Filter> chainList;
    // the default state of a Jbutton is true
    private boolean addEnabled = true;
    private boolean removeEnabled = true;
    private boolean moveUpEnabled = true;
    private boolean moveDownEnabled = true;

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

    @Override
    public void setRemoveButtonEnabled(boolean b) {
        this.removeEnabled = b;
    }

    @Override
    public void setMoveUpButtonEnabled(boolean b) {
        this.moveUpEnabled = b;
    }

    @Override
    public void setMoveDownButtonEnabled(boolean b) {
        this.moveDownEnabled = b;
    }

    @Override
    public void setAddButtonEnabled(boolean b) {
        this.addEnabled = b;
    }

    public boolean addButtonEnabled() {
        return addEnabled;
    }

    public boolean removeButtonEnabled() {
        return removeEnabled;
    }

    public boolean moveUpButtonEnabled() {
        return moveUpEnabled;
    }

    public boolean moveDownButtonEnabled() {
        return moveDownEnabled;
    }
}
