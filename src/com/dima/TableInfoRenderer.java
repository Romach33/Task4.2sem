package com.dima;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableInfoRenderer extends DefaultTableCellRenderer {
    @Override


    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, false, row, column);

        GuiForm local = com.dima.Main.getGui();

        int max = local.getMax();
        int current = local.getCurrentStep();


        if(column == max - (current-1)) c.setBackground(Color.blue);
        else if(column > max - (current-1)) c.setBackground(Color.green); else c.setBackground(new JLabel().getBackground());
        return c;
    }
}
