package org.gui;

import org.logic.Controller;
import org.logic.Pet;
import static org.gui.utils.Messages.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class ViewData extends JFrame {
    Controller controller = null;

    private JPanel panelView;
    private JTable tblData;
    private JButton btnDelete;
    private JButton btnModify;
    private JButton btnBack;
    private JButton btnRefresh;

    public ViewData() {
        controller = new Controller();
        setTitle("Carga de datos");
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setSize(650, 450);
        setVisible(true);
        setContentPane(panelView);

        btnBack.addActionListener(e -> dispose());
        btnDelete.addActionListener(e -> deleteRow());
        btnModify.addActionListener(e -> modifyRow());
        btnRefresh.addActionListener(e -> loadTable());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                loadTable();
            }
        });
    }

    private void deleteRow() {
        if(tblData.getRowCount() > 0) {
            // -1 ning seleccionada
            if(tblData.getSelectedRow() != -1) {
                int numOwner = Integer.parseInt(tblData.getValueAt(tblData.getSelectedRow(), 0).toString());
                boolean confirmed = showConfirm("¿Estas seguro que quieres eliminar este registro?", "Confirmar eliminacion");
                if(confirmed) {
                    controller.deleteRow(numOwner);
                    showMessage("Mascota eliminada correctamente", "Info", "Mascota eliminada");
                    loadTable();
                }else{
                    showMessage("Eliminacion cancelada", "Info", "Eliminar");
                }
            }
            else{
                showMessage("No se selecciono ningun registro", "Error", "Eliminar");
            }
        }else{
            showMessage("No se encontraron registros", "Error", "Eliminar");
        }
    }

    private void loadTable() {
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){return false;}
        };

        String[] titles = {"Num.Mascota", "Nombre", "Color",
                "Raza", "Alergico", "Atencion.Esp.", "Dueño", "Celular", "Num.Dueño"};
        tableModel.setColumnIdentifiers(titles);

        List<Pet> petList = controller.getPetList();

        if(petList !=null){
            for(Pet p : petList){
                Object[] object = {p.getNumClient(), p.getPetName(), p.getBreed(),p.getPetColor(),p.getAllergic(),
                p.getSpecialAttention(), p.getOwner().getOwnerName(), p.getOwner().getOwnerPhone(), p.getOwner().getIdOwner()};
                tableModel.addRow(object);
            }
        }
        tblData.setModel(tableModel);
    }

    private void modifyRow() {
        if(tblData.getRowCount() > 0) {
            // -1 ninguna seleccionada
            if(tblData.getSelectedRow() != -1) {
                int numOwner = Integer.parseInt(tblData.getValueAt(tblData.getSelectedRow(), 0).toString());
                ModifyData windowModify = new ModifyData(numOwner);
                windowModify.setVisible(true);
                windowModify.setLocationRelativeTo(null);

            }
            else{
                showMessage("No se selecciono ningun registro", "Error", "Eliminar");
            }
        }else{
            showMessage("No se encontraron registros", "Error", "Eliminar");
        }
    }
}
