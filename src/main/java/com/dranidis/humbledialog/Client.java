package com.dranidis.humbledialog;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.dranidis.humbledialog.model.FilterRepository;
import com.dranidis.humbledialog.view.ChainComposerDialog;

public class Client {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                new ChainComposerDialog(frame, new FilterRepository());
            }
        });
    }

}