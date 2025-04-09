package org.gui;

import org.logic.Controller;
import org.logic.Pet;
import static org.gui.utils.Messages.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ModifyData extends JFrame {

    private JLabel lblName;
    private JTextField txtName;
    private JLabel lblBreed;
    private JTextField txtBreed;
    private JLabel lblColor;
    private JTextField txtColor;
    private JLabel lblAllergic;
    private JComboBox<String> cmbAllergic;
    private JLabel lblSpecialAttention;
    private JComboBox<String> cmbSpecialAttention;
    private JLabel lblObservations;
    private JTextArea txtObservations;
    private JButton btnSaveModify;
    private JButton btnBack;
    private JPanel panelModify;
    private JComboBox<Pet> cmbPetList;

    private Controller controller = new Controller();
    private List<Pet> petList = new ArrayList<>();
    Pet pet = new Pet();


    public ModifyData(int numOwner) {
        setTitle("Modificar registro");
        setVisible(true);
        setSize(600, 400);
        setContentPane(panelModify);

        btnBack.addActionListener(e -> dispose());{}
        btnSaveModify.addActionListener(e -> saveModifyData());{}

        loadData(numOwner);
    }

    private void loadData(int numOwner) {
        this.pet = controller.getSelectedPet(numOwner);
        List<Pet> petList = controller.getPetList();
        cmbPetList.removeAllItems();
        for (Pet pets : petList) cmbPetList.addItem(pets);

        txtName.setText(pet.getPetName());
        txtBreed.setText(pet.getBreed());
        txtColor.setText(pet.getPetColor());
        txtObservations.setText(pet.getObservations());

        if  (pet.getAllergic().equals("Si")) {cmbAllergic.setSelectedIndex(1);}
        else if (pet.getAllergic().equals("No")){cmbAllergic.setSelectedIndex(0);}
        else cmbAllergic.setSelectedIndex(2);
        if  (pet.getSpecialAttention().equals("Si")) {cmbSpecialAttention.setSelectedIndex(1);}
        else if (pet.getSpecialAttention().equals("No")){cmbSpecialAttention.setSelectedIndex(0);}
        else cmbSpecialAttention.setSelectedIndex(2);
    }

    private void saveModifyData() {
        String petName = txtName.getText();
        String petBreed = txtBreed.getText();
        String petColor = txtColor.getText();
        String petObservations = txtObservations.getText();
        String allergic = (String) cmbAllergic.getSelectedItem();
        String specialAttention = (String) cmbSpecialAttention.getSelectedItem();

        if (petName.isEmpty() || petBreed.isEmpty() || petColor.isEmpty()) {
            showMessage("Completa los campos necesarios", "Error", "Formulario incompleto");
            return;
        }

        controller.modifyPet(pet, petName, petBreed, petColor, petObservations, allergic, specialAttention);
        showMessage("Valores modificados con exito", "Info", "Datos modificados");
        dispose();

    }
}
