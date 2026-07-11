package util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class AutoCompletadoCB {

    private JComboBox<String> comboBox;
    private List<String> items;
    private List<String> filteredItems;
    private boolean isPopupVisible = false;

    public AutoCompletadoCB(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
        this.items = new ArrayList<>();
        this.filteredItems = new ArrayList<>();
        
        JTextField editor = (JTextField) comboBox.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_UP && 
                    e.getKeyCode() != KeyEvent.VK_DOWN &&
                    e.getKeyCode() != KeyEvent.VK_ENTER) {
                    autoComplete();
                }
            }
        });

        // Ocultar popup cuando pierde foco
        comboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
                isPopupVisible = true;
            }

            @Override
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {
                isPopupVisible = false;
            }

            @Override
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {
                isPopupVisible = false;
            }
        });
    }

    public void setItems(List<String> items) {
        this.items = items;
        this.filteredItems = new ArrayList<>(items);
        actualizarComboBox(filteredItems);
    }

    private void autoComplete() {
        JTextField editor = (JTextField) comboBox.getEditor().getEditorComponent();
        String texto = editor.getText().trim().toLowerCase();

        if (texto.isEmpty()) {
            actualizarComboBox(items);
            return;
        }

        filteredItems.clear();
        for (String item : items) {
            if (item.toLowerCase().contains(texto)) {
                filteredItems.add(item);
            }
        }

        if (!filteredItems.isEmpty()) {
            actualizarComboBox(filteredItems);
            comboBox.showPopup();
            // Mantener el texto que el usuario escribió
            editor.setText(texto);
            editor.setCaretPosition(editor.getText().length());
        } else {
            comboBox.hidePopup();
        }
    }

    private void actualizarComboBox(List<String> lista) {
        String textoActual = ((JTextField) comboBox.getEditor().getEditorComponent()).getText();
        comboBox.removeAllItems();
        for (String item : lista) {
            comboBox.addItem(item);
        }
        // Restaurar el texto que estaba escrito
        if (textoActual != null && !textoActual.isEmpty()) {
            ((JTextField) comboBox.getEditor().getEditorComponent()).setText(textoActual);
        }
        comboBox.setSelectedIndex(-1);
    }

    public String getSelectedItem() {
        Object selected = comboBox.getSelectedItem();
        return selected != null ? selected.toString() : "";
    }

    public void setSelectedItem(String item) {
        comboBox.setSelectedItem(item);
    }
}