package org.gui;

import org.logic.Controller;
import org.logic.Owner;
import org.logic.Pet;

import javax.swing.*;
import java.awt.event.ItemEvent;
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
    private JButton btnSave;
    private JButton btnBack;
    private JPanel panelModify;
    private JComboBox<Pet> cmbPetList;

    private Controller controller = new Controller();
    private List<Pet> petList = new ArrayList<>();

    public ModifyData(int numOwner) {
        setTitle("Modificar registro");
        setVisible(true);
        setSize(600, 400);
        setContentPane(panelModify);

        btnBack.addActionListener(e -> dispose());{}
//        cmbPetList.addItemListener(e -> {
//            if (e.getStateChange() == ItemEvent.SELECTED) {
//                Pet selectedPet = (Pet) cmbPetList.getSelectedItem();
//
//                if (selectedPet != null && selectedPet.getNumClient() != 0) {
//                    txtName.setText(selectedPet.getPetName());
//                    txtBreed.setText(selectedPet.getBreed());
//                    txtColor.setText(selectedPet.getPetColor());
//                    txtObservations.setText(selectedPet.getObservations());
//
//                    if  (selectedPet.getAllergic().equals("Si")) {cmbAllergic.setSelectedIndex(0);}
//                    else if (selectedPet.getAllergic().equals("No")){cmbAllergic.setSelectedIndex(1);}
//                    else cmbAllergic.setSelectedIndex(2);
//                    if  (selectedPet.getAllergic().equals("Si")) {cmbSpecialAttention.setSelectedIndex(0);}
//                    else if (selectedPet.getAllergic().equals("No")){cmbSpecialAttention.setSelectedIndex(1);}
//                    else cmbSpecialAttention.setSelectedIndex(2);
//                }
//            }
//        });
        loadData(numOwner);
    }

    private void loadData(int numOwner) {
        Pet pet = controller.getAllPets(numOwner);
        List<Pet> petList = controller.getPetList();
        cmbPetList.removeAllItems();
        for (Pet pets : petList) cmbPetList.addItem(pets);

        txtName.setText(pet.getPetName());
        txtBreed.setText(pet.getBreed());
        txtColor.setText(pet.getPetColor());
        txtObservations.setText(pet.getObservations());

        if  (pet.getAllergic().equals("Si")) {cmbAllergic.setSelectedIndex(0);}
        else if (pet.getAllergic().equals("No")){cmbAllergic.setSelectedIndex(1);}
        else cmbAllergic.setSelectedIndex(2);
        if  (pet.getAllergic().equals("Si")) {cmbSpecialAttention.setSelectedIndex(0);}
        else if (pet.getAllergic().equals("No")){cmbSpecialAttention.setSelectedIndex(1);}
        else cmbSpecialAttention.setSelectedIndex(2);
    }
}
