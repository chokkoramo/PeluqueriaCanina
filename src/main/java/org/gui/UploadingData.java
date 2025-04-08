package org.gui;

import org.logic.Controller;
import org.logic.Owner;
import org.logic.Pet;
import javax.swing.*;
import javax.swing.plaf.synth.Region;
import java.awt.event.ComponentAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class UploadingData extends JFrame {
    private JPanel panelData;
    private JTextField txtName, txtBreed, txtColor, txtOwnerName, txtOwnerPhone;
    private JComboBox<String> cmbAllergic, cmbSpecialAttention;
    private JComboBox<Owner> cmbOwnerList;
    private JTextArea txtObservations, txtPetList;
    private JButton btnClear, btnSave, btnBack, btnAddPet;
    private JLabel lblnameOwner;
    private JLabel lblOwnerPhone;
    private JLabel lblName;
    private JLabel lblBreed;
    private JLabel lblColor;
    private JLabel lblAllergic;
    private JLabel lblSpecialAttention;
    private JLabel lblObservations;
    private JLabel lblPetList;
    private List<Pet> petList = new ArrayList<>();
    private List<Owner> ownerList = new ArrayList<>();
    private Controller controller = new Controller();

    public UploadingData() {
        setTitle("Carga de datos");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setContentPane(panelData);

        btnClear.addActionListener(e -> clearFields());
        btnBack.addActionListener(e -> dispose());
        btnAddPet.addActionListener(e -> addPet());
        btnSave.addActionListener(e -> saveData());
        cmbOwnerList.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Owner selectedOwner = (Owner) cmbOwnerList.getSelectedItem();

                if (selectedOwner != null && selectedOwner.getIdOwner() != 0) {
                    // Es un dueño existente
                    txtOwnerName.setText(selectedOwner.getOwnerName());
                    txtOwnerPhone.setText(selectedOwner.getOwnerPhone());
                    txtOwnerName.setEnabled(false);
                    txtOwnerPhone.setEnabled(false);
                } else {
                    // Nuevo dueño
                    txtOwnerName.setText("");
                    txtOwnerPhone.setText("");
                    txtOwnerName.setEnabled(true);
                    txtOwnerPhone.setEnabled(true);
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                loadOwners();
            }
        });
    }

    // Clear data
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

    private void clearPetFields() {
        txtName.setText("");
        txtBreed.setText("");
        txtColor.setText("");
        txtObservations.setText("");
        cmbAllergic.setSelectedIndex(0);
        cmbSpecialAttention.setSelectedIndex(0);
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
        txtOwnerName.setEnabled(false);
        txtOwnerPhone.setEnabled(false);
        clearPetFields();
    }

    private void saveData() {
        Owner selectedOwner = (Owner) cmbOwnerList.getSelectedItem();

        if (petList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agrega al menos una mascota antes de guardar");
            return;
        }

        // Verificamos si es un dueño existente
        if (selectedOwner != null && selectedOwner.getIdOwner() != 0) {
            controller.addPetsToExistingOwner(selectedOwner.getIdOwner(), petList);
            JOptionPane.showMessageDialog(this, "Mascotas añadidas al dueño existente");
        } else {
            String ownerName = txtOwnerName.getText();
            String ownerPhone = txtOwnerPhone.getText();

            if (ownerName.isEmpty() || ownerPhone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completa los datos del dueño");
                return;
            }

            controller.saveOwnerWithPets(ownerName, ownerPhone, petList);
            JOptionPane.showMessageDialog(this, "Dueño y mascotas guardados con éxito");
        }

        clearFields();
        loadOwners();
    }

    private void loadOwners() {
        ownerList = controller.getAllOwners();
        cmbOwnerList.removeAllItems();
        cmbOwnerList.addItem(new Owner(0, "Nuevo Dueño", "Numero celular"));
        for (Owner owner : ownerList) {
            cmbOwnerList.addItem(owner);
        }
    }

}