package com.dranidis.humbledialog;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ComposerTest {

    @Test
    public void testCompose() {
        // given
        MockChaingComposerView view = new MockChaingComposerView();
        ChainComposer composer = new ChainComposer(view);

        assertEquals(0, view.selectionList().size());

        // when
        composer.initialize();
        // then
        assertEquals(1, view.selectionList().size());
    }
}