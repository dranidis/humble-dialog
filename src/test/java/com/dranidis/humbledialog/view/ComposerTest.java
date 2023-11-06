package com.dranidis.humbledialog.view;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dranidis.humbledialog.ReverbFilter;
import com.dranidis.humbledialog.model.EchoFilter;
import com.dranidis.humbledialog.model.Filter;

/**
 * All the tests should only interact with the composer. There should be no
 * update interactions with the view! Only get interactions.
 * <p>
 * The composer has the sole responsibility of updating the view.
 */
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
        assertEquals(false, view.addButtonEnabled());
        assertEquals(false, view.removeButtonEnabled());
        assertEquals(false, view.moveUpButtonEnabled());
        assertEquals(false, view.moveDownButtonEnabled());
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

    @Test
    public void selecting_a_filter_in_selection_list_enables_add_button() {
        // given
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        MockChainComposerView view = new MockChainComposerView();
        ChainComposer composer = new ChainComposer(view, filters);

        composer.initialize();
        assertEquals(false, view.addButtonEnabled());
        // when

        composer.selectFilter(0);

        // then
        assertEquals(true, view.addButtonEnabled());
    }

    @Test
    public void selecting_a_filter_in_chain_list_enables_remove_button() {
        // given
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        MockChainComposerView view = new MockChainComposerView();
        ChainComposer composer = new ChainComposer(view, filters);

        composer.initialize();
        assertEquals(false, view.removeButtonEnabled());

        // when

        composer.selectChainFilter(0);

        // then
        assertEquals(true, view.removeButtonEnabled());
    }

    @Test
    public void selecting_a_filter_in_chain_list_enables_move_up_button_when_not_first_filter() {
        // given
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        filters.add(new EchoFilter());
        MockChainComposerView view = new MockChainComposerView();
        ChainComposer composer = new ChainComposer(view, filters);

        composer.initialize();
        assertEquals(false, view.moveUpButtonEnabled());
        // when

        composer.selectChainFilter(1);

        // then
        assertEquals(true, view.moveUpButtonEnabled());
    }

    @Test
    public void selecting_a_filter_in_chain_list_disables_move_up_button_when_first_filter() {
        // given
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        filters.add(new EchoFilter());
        MockChainComposerView view = new MockChainComposerView();
        ChainComposer composer = new ChainComposer(view, filters);

        composer.initialize();
        assertEquals(false, view.moveUpButtonEnabled());
        // when

        composer.selectChainFilter(0);

        // then
        assertEquals(false, view.moveUpButtonEnabled());
    }

    @Test
    public void selecting_a_filter_in_chain_list_enables_move_down_button_when_not_last_filter() {
        // given
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        filters.add(new EchoFilter());
        MockChainComposerView view = new MockChainComposerView();
        ChainComposer composer = new ChainComposer(view, filters);

        composer.initialize();
        composer.add(0);
        composer.add(1);
        assertEquals(false, view.moveDownButtonEnabled());
        // when

        composer.selectChainFilter(0);

        // then
        assertEquals(true, view.moveDownButtonEnabled());
    }

    @Test
    public void selecting_a_filter_in_chain_list_disables_move_down_button_when_last_filter() {
        // given
        List<Filter> filters = new ArrayList<>();
        filters.add(new ReverbFilter());
        filters.add(new EchoFilter());
        MockChainComposerView view = new MockChainComposerView();
        ChainComposer composer = new ChainComposer(view, filters);

        composer.initialize();
        composer.add(0);
        composer.add(1);
        assertEquals(false, view.moveDownButtonEnabled());
        // when

        composer.selectChainFilter(1);

        // then
        assertEquals(false, view.moveDownButtonEnabled());
    }
}