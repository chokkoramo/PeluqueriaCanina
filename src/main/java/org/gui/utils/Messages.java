package org.gui.utils;

import javax.swing.*;

public class Messages {
    public static void showMessage(String msg, String type, String title) {
        JOptionPane optionPane = new JOptionPane(msg);
        if(type.equals("Info")) {
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        }else if(type.equals("Error")) {
            optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
        }else if(type.equals("Warning")) {
            optionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
        }
        JDialog dialog = optionPane.createDialog(title);
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);
    }
    public static boolean showConfirm(String msg, String title) {
        int option = JOptionPane.showConfirmDialog(
                null,
                msg,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        return option == JOptionPane.YES_OPTION;
    }
}
