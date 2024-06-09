package org.fokkittah.coffeeassistant.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * A renderer for displaying tooltips in a JTable for recipe steps.
 * This renderer shows tooltips for each cell in the table.
 */
public class RecipeStepTableRenderer extends DefaultTableCellRenderer {

    private String[] tooltips;

    public RecipeStepTableRenderer(JTable table, String[] tooltips){
        this.tooltips = tooltips;
        setOpaque(true);
        setBackground(table.getTableHeader().getBackground());
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (column < tooltips.length) {
            ((JComponent) component).setToolTipText(tooltips[column]);
        }
        return component;
    }

}
