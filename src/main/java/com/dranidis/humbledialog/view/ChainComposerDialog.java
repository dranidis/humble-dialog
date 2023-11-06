package com.dranidis.humbledialog.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.dranidis.humbledialog.model.Filter;
import com.dranidis.humbledialog.model.FilterRepository;

/**
 * Once we have all of the functionality we want in the smart object `Composer`,
 * it is time to work with the dialog class. To do this, we implement the view
 * interface on a dialog class. Notice that each action method is very small,
 * just a simple delegation. Every method that receives data on the view is as
 * close to a pure “set” method as possible.
 */
public class ChainComposerDialog extends JDialog implements ChainComposerView {

    private ChainComposer composer;

    private DefaultListModel<String> selectionListModel = new DefaultListModel<>();
    private JList<String> selectionJList = new JList<>(selectionListModel);

    private DefaultListModel<String> chainListModel = new DefaultListModel<>();
    private JList<String> chainJList = new JList<>(chainListModel);

    private JButton addButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");
    private JButton removeAllButton = new JButton("Remove All");
    private JButton moveUpButton = new JButton("Move Up");
    private JButton moveDownButton = new JButton("Move Down");

    public ChainComposerDialog(JFrame frame, FilterRepository filterRepository) {
        super(frame, "Chain Composer", true);

        this.composer = new ChainComposer(this, filterRepository.getFilters());

        setSize(400, 300);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);

        JPanel moveButtonPanel = new JPanel();
        moveButtonPanel.setLayout(new GridLayout(2, 1));
        moveButtonPanel.add(moveUpButton);
        moveButtonPanel.add(moveDownButton);

        // Add components to the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 5;
        panel.add(new JScrollPane(selectionJList), c);
        c.gridx = 1;
        c.gridheight = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        panel.add(buttonPanel, c);
        c.gridy = 1;
        panel.add(moveButtonPanel, c);
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 5;
        c.weightx = 1.0;
        c.weighty = 1.0;
        panel.add(new JScrollPane(chainJList), c);

        addButton.addActionListener(e -> onAdd());
        removeButton.addActionListener(e -> onRemove());
        removeAllButton.addActionListener(e -> onRemoveAll());
        moveUpButton.addActionListener(e -> onMoveUp());
        moveDownButton.addActionListener(e -> onMoveDown());
        selectionJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting())
                onSelectionChanged();
        });
        chainJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting())
                onChainSelectionChanged();
        });

        getContentPane().add(panel);
        pack();

        composer.initialize();

        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    /*
     * Action methods
     * 
     * Each action method is very small, just a simple delegation. 
     * 
     */

    private void onAdd() {
        composer.add();
    }

    private void onRemove() {
        composer.remove(chainJList.getSelectedIndex());
    }

    private void onRemoveAll() {
        composer.removeAll();
    }

    private void onMoveUp() {
        composer.moveUp(chainJList.getSelectedIndex());
    }

    private void onMoveDown() {
        composer.moveDown(chainJList.getSelectedIndex());
    }

    private void onSelectionChanged() {
        composer.selectFilter(selectionJList.getSelectedIndex());
    }

    private void onChainSelectionChanged() {
        composer.selectChainFilter(chainJList.getSelectedIndex());
    }

    /* 
     * Setters
     *
     * Every method that receives data on the view is as close to a pure “set” method as possible.
     * 
     */
    @Override
    public void setSelectionList(List<Filter> filters) {
        setList(selectionListModel, filters);
    }

    @Override
    public void setChainList(List<Filter> chain) {
        setList(chainListModel, chain);
    }

    private void setList(DefaultListModel<String> listModel, List<Filter> filters) {
        listModel.clear();
        for (Filter filter : filters) {
            listModel.addElement(filter.name());
        }
    }

    @Override
    public void setRemoveButtonEnabled(boolean b) {
        removeButton.setEnabled(b);
    }

    @Override
    public void setMoveUpButtonEnabled(boolean b) {
        moveUpButton.setEnabled(b);
    }

    @Override
    public void setMoveDownButtonEnabled(boolean b) {
        moveDownButton.setEnabled(b);
    }

    @Override
    public void setAddButtonEnabled(boolean b) {
        addButton.setEnabled(b);
    }
}