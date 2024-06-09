package org.fokkittah.coffeeassistant.utils;

import org.fokkittah.coffeeassistant.configuration.grinder.Grinder;

import javax.swing.*;
import java.awt.*;

/**
 * A renderer for displaying Grinder objects in a JComboBox.
 * This renderer shows the name of the Grinder in the list.
 */
public class ComboBoxGrinderRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Grinder) {
            Grinder grinder = (Grinder) value;
            label.setText(grinder.getName());
        }
        return this;
    }


}
