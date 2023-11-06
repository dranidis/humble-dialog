package com.dranidis.humbledialog.view;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dranidis.humbledialog.ReverbFilter;
import com.dranidis.humbledialog.model.EchoFilter;
import com.dranidis.humbledialog.model.Filter;

public class ComposerTest {

    @Test
    public void initialize_adds_filters_to_selection_list() {
        // given
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        MockChainComposerView view = new MockChainComposerView();
        ChainComposer composer = new ChainComposer(view, filters);

        assertEquals(0, view.selectionList().size());

        // when
        composer.initialize();

        // then
        assertEquals(1, view.selectionList().size());
        assertEquals(0, view.composedFilter().size());

    }

    @Test
    public void add_filter_to_chain() {
        // given
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        MockChainComposerView view = new MockChainComposerView();
        ChainComposer composer = new ChainComposer(view, filters);

        // when
        composer.initialize();
        composer.add(0);

        // then
        assertEquals(1, view.composedFilter().size());
    }

    @Test
    public void remove_filter_from_chain() {
        // given
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        MockChainComposerView view = new MockChainComposerView();
        ChainComposer composer = new ChainComposer(view, filters);

        // when
        composer.initialize();
        composer.add(0);
        composer.remove(0);

        // then
        assertEquals(0, view.composedFilter().size());
    }

    @Test
    public void remove_all_filters_from_chain() {
        // given
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        MockChainComposerView view = new MockChainComposerView();
        ChainComposer composer = new ChainComposer(view, filters);

        // when
        composer.initialize();
        composer.add(0);
        composer.add(0);
        composer.removeAll();

        // then
        assertEquals(0, view.composedFilter().size());
    }

    @Test
    public void move_up_filter_in_chain() {
        // given
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        filters.add(new EchoFilter());
        MockChainComposerView view = new MockChainComposerView();
        ChainComposer composer = new ChainComposer(view, filters);

        // when
        composer.initialize();
        composer.add(0);
        composer.add(1);
        composer.moveUp(1);

        // then
        assertEquals("Echo", view.composedFilter().get(0).name());
        assertEquals("Reverb", view.composedFilter().get(1).name());
    }

    @Test
    public void move_down_filter_in_chain() {
        // given
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        filters.add(new EchoFilter());
        MockChainComposerView view = new MockChainComposerView();
        ChainComposer composer = new ChainComposer(view, filters);

        // when
        composer.initialize();
        composer.add(0);
        composer.add(1);
        composer.moveDown(0);

        // then
        assertEquals("Echo", view.composedFilter().get(0).name());
        assertEquals("Reverb", view.composedFilter().get(1).name());
    }
}