package org.gui;

import org.logic.Controller;
import org.logic.Pet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UploadingData extends JFrame {
    private JPanel panelData;
    private JTextField txtName, txtBreed, txtColor, txtOwnerName, txtOwnerPhone;
    private JComboBox<String> cmbAllergic, cmbSpecialAttention;
    private JTextArea txtObservations, txtPetList;
    private JButton btnClear, btnSave, btnBack, btnAddPet;
    private List<Pet> petList = new ArrayList<>();
    private Controller controller = new Controller();

    public UploadingData() {
        setTitle("Carga de datos");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        panelData = new JPanel(new GridBagLayout());

        // Labels y Campos de Texto
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelData.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField(15);
        panelData.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelData.add(new JLabel("Raza:"), gbc);
        gbc.gridx = 1;
        txtBreed = new JTextField(15);
        panelData.add(txtBreed, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelData.add(new JLabel("Color:"), gbc);
        gbc.gridx = 1;
        txtColor = new JTextField(15);
        panelData.add(txtColor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelData.add(new JLabel("Alérgico:"), gbc);
        gbc.gridx = 1;
        cmbAllergic = new JComboBox<>(new String[]{"No", "Sí"});
        panelData.add(cmbAllergic, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelData.add(new JLabel("Atención Especial:"), gbc);
        gbc.gridx = 1;
        cmbSpecialAttention = new JComboBox<>(new String[]{"No", "Sí"});
        panelData.add(cmbSpecialAttention, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelData.add(new JLabel("Nombre Dueño:"), gbc);
        gbc.gridx = 1;
        txtOwnerName = new JTextField(15);
        panelData.add(txtOwnerName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panelData.add(new JLabel("Celular Dueño:"), gbc);
        gbc.gridx = 1;
        txtOwnerPhone = new JTextField(15);
        panelData.add(txtOwnerPhone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panelData.add(new JLabel("Observaciones:"), gbc);
        gbc.gridx = 1;
        txtObservations = new JTextArea(3, 15);
        JScrollPane scrollObs = new JScrollPane(txtObservations);
        panelData.add(scrollObs, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panelData.add(new JLabel("Mascotas Agregadas:"), gbc);
        gbc.gridx = 1;
        txtPetList = new JTextArea(5, 15);
        txtPetList.setEditable(false);
        JScrollPane scrollPets = new JScrollPane(txtPetList);
        panelData.add(scrollPets, gbc);

        // Botones
        gbc.gridx = 0;
        gbc.gridy = 9;
        btnClear = new JButton("Limpiar");
        panelData.add(btnClear, gbc);
        gbc.gridx = 1;
        btnSave = new JButton("Guardar");
        panelData.add(btnSave, gbc);
        gbc.gridx = 2;
        btnBack = new JButton("Volver");
        panelData.add(btnBack, gbc);
        gbc.gridx = 3;
        btnAddPet = new JButton("Añadir Mascota");
        panelData.add(btnAddPet, gbc);

        add(panelData);
        setVisible(true);

        // Eventos de los Botones
        btnClear.addActionListener(e -> clearFields());
        btnBack.addActionListener(e -> dispose());
        btnAddPet.addActionListener(e -> addPet());
        btnSave.addActionListener(e -> saveData());
    }

    private void clearFields() {
        txtName.setText("");
        txtBreed.setText("");
        txtColor.setText("");
        txtObservations.setText("");
        txtOwnerName.setText("");
        txtOwnerPhone.setText("");
        cmbAllergic.setSelectedIndex(0);
        cmbSpecialAttention.setSelectedIndex(0);
        petList.clear();
        txtPetList.setText("");
    }

    private void addPet() {
        String petName = txtName.getText();
        String petBreed = txtBreed.getText();
        String petColor = txtColor.getText();
        String petObservations = txtObservations.getText();
        String allergic = (String) cmbAllergic.getSelectedItem();
        String specialAttention = (String) cmbSpecialAttention.getSelectedItem();

        if (petName.isEmpty() || petBreed.isEmpty() || petColor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa los datos de la mascota");
            return;
        }

        Pet pet = new Pet();
        pet.setPetName(petName);
        pet.setBreed(petBreed);
        pet.setPetColor(petColor);
        pet.setObservations(petObservations);
        pet.setAllergic(allergic);
        pet.setSpecialAttention(specialAttention);

        petList.add(pet);
        txtPetList.append(petName + " (" + petBreed + ")\n");
        clearPetFields();
    }

    private void clearPetFields() {
        txtName.setText("");
        txtBreed.setText("");
        txtColor.setText("");
        txtObservations.setText("");
    }

    private void saveData() {
        String ownerName = txtOwnerName.getText();
        String ownerPhone = txtOwnerPhone.getText();

        if (ownerName.isEmpty() || ownerPhone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa los datos del dueño");
            return;
        }

        if (petList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agrega al menos una mascota antes de guardar");
            return;
        }

        controller.saveOwnerWithPets(ownerName, ownerPhone, petList);
        JOptionPane.showMessageDialog(this, "Dueño y mascotas guardados con éxito");
        clearFields();
    }
}