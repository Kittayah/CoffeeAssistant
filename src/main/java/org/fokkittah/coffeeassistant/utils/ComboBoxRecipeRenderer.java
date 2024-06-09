package org.fokkittah.coffeeassistant.utils;

import org.fokkittah.coffeeassistant.configuration.recipe.Recipe;

import javax.swing.*;
import java.awt.*;

/**
 * A renderer for displaying Recipe objects in a JComboBox.
 * This renderer shows the name of the Recipe in the list.
 */
public class ComboBoxRecipeRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Recipe) {
            Recipe recipe = (Recipe) value;
            label.setText(recipe.getName());
        }
        return this;
    }

}
