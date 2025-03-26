package org.gui;

import org.logic.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.spec.KeySpec;

public class UploadingData extends JFrame{
    private JPanel panelData;
    private JTextField txtName;
    private JTextField txtBreed;
    private JTextField txtColor;
    private JTextField txtOwnerName;
    private JTextField txtOwnerPhone;
    private JComboBox cmbAllergic;
    private JComboBox cmbSpecialAttention;
    private JTextArea txtObservations;
    private JButton btnClear;
    private JButton btnSave;
    private JButton btnBack;

    Controller controller = new Controller();

    public UploadingData(){
        //Controller controller = new Controller();
        setContentPane(panelData);
        setTitle("Carga de datos");
        setSize(450,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtBreed.setText("");
                txtColor.setText("");
                txtObservations.setText("");
                txtOwnerName.setText("");
                txtOwnerPhone.setText("");
                cmbAllergic.setSelectedIndex(0);
                cmbSpecialAttention.setSelectedIndex(0);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String petName = txtName.getText();
                String petBreed = txtBreed.getText();
                String petColor = txtColor.getText();
                String petObservations = txtObservations.getText();
                String ownerName = txtOwnerName.getText();
                String ownerPhone = txtOwnerPhone.getText();
                String allergic = (String) cmbAllergic.getSelectedItem();
                String specialAttention = (String) cmbSpecialAttention.getSelectedItem();

                controller.save(petName, petBreed, petColor, petObservations,
                        ownerName, ownerPhone, allergic, specialAttention);
            }
        });
    }
}
