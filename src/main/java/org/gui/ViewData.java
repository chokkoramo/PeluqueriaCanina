package org.gui;

import org.logic.Controller;
import org.logic.Pet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class ViewData extends JFrame {
    Controller controller = null;

    private JPanel panelView;
    private JTable tblData;
    private JButton btnDelete;
    private JButton btnModify;
    private JButton btnBack;

    public ViewData() {
        controller = new Controller();
        setTitle("Carga de datos");
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setSize(550, 400);
        setVisible(true);
        setContentPane(panelView);

        btnBack.addActionListener(e -> dispose());


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                loadTable();
            }
        });

    }

    private void loadTable() {
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){return false;}
        };

        String[] titles = {"Numero Mascota", "Nombre", "Color",
                "Raza", "Alergico", "Atencion Esp.", "Nombre Dueño", "Celular"};
        tableModel.setColumnIdentifiers(titles);

        List<Pet> petList = controller.getPetList();

        if(petList !=null){
            for(Pet p : petList){
                Object[] object = {p.getNumClient(), p.getPetName(), p.getBreed(),p.getPetColor(),p.getAllergic(),
                p.getSpecialAttention(), p.getOwner().getOwnerName(), p.getOwner().getOwnerPhone()};

                tableModel.addRow(object);
            }
        }
        tblData.setModel(tableModel);
    }

}
