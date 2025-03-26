package org.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame{
    private JPanel principalPanel;
    private JButton btnUploadingData;
    private JButton btnSeeData;
    private JButton btnExit;

    public Principal(){
        setContentPane(principalPanel);
        setTitle("Bienvenido");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnUploadingData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UploadingData windowData = new UploadingData();
                windowData.setVisible(true);
                windowData.setLocationRelativeTo(null);
            }
        });
    }
}
