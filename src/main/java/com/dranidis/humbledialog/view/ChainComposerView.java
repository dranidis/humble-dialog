package com.dranidis.humbledialog.view;

import java.util.List;

import com.dranidis.humbledialog.model.Filter;

public interface ChainComposerView {
    void setSelectionList(List<Filter> filters);

    void setChainList(List<Filter> chain);

}
