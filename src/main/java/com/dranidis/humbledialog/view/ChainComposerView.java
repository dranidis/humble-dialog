package com.dranidis.humbledialog.view;

import java.util.List;

import com.dranidis.humbledialog.model.Filter;

public interface ChainComposerView {
    void setSelectionList(List<Filter> filters);

    void setChainList(List<Filter> chain);

    void setRemoveButtonEnabled(boolean b);

    void setMoveUpButtonEnabled(boolean b);

    void setMoveDownButtonEnabled(boolean b);

    void setAddButtonEnabled(boolean b);

}
