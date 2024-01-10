package ActionListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import App_GUI.Gui;
import GeschaftsObejekt.Musik;

public class addToShoppingCart extends JDialog implements ActionListener {
    private TextField platteTextField;
    private TextField cdTextField;
    private JCheckBox mp3CheckBox;
    private JButton hinzufuegeButton;
    private Gui parent;

    public addToShoppingCart(Gui p) {
        parent = p;
        platteTextField = new TextField();
        cdTextField = new TextField();
        mp3CheckBox = new JCheckBox();
        hinzufuegeButton = new JButton("Hinzufügen");
        JPanel centerPanel = new JPanel(new GridLayout(1, 3));
        JPanel southPanel = new JPanel(new GridLayout(1, 3));
        centerPanel.add(platteTextField);
        centerPanel.add(cdTextField);
        centerPanel.add(mp3CheckBox);

        southPanel.add(new JPanel());
        southPanel.add(hinzufuegeButton);
        southPanel.add(new JPanel());
        hinzufuegeButton.addActionListener(this);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(hinzufuegeButton, BorderLayout.SOUTH);
        this.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
        this.setLocationRelativeTo(parent);
        clearTextBox();
        this.setSize(150, 100);
        this.setVisible(false);
    }

    private void setEnabled() {
        Musik m = parent.getShoppingCartListner().getMusik();
        System.out.println(m.toString());
        platteTextField.setEnabled(m.getIsPlatte());
        cdTextField.setEnabled(m.getIsCD());
        mp3CheckBox.setEnabled(m.isIsMp3());
    }

    private void clearTextBox() {
        platteTextField.setText("");
        cdTextField.setText("");
        mp3CheckBox.setSelected(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setEnabled();
        this.setVisible(true);
        if (e.getSource().equals(hinzufuegeButton)) {
            int index = parent.getProfilList().getIndexofLogin();
            if (parent.getShoppingCartListner().isInMusikList()) {
                parent.getShoppingCartListner().add();
                int platteAnzahl = 0;
                int cdAnzahl = 0;
                boolean mp3Seclect = false;
                if (platteTextField.isEnabled()) {
                    platteAnzahl = Integer.parseInt(platteTextField.getText().trim());
                }
                if (cdTextField.isEnabled()) {
                    cdAnzahl = Integer.parseInt(cdTextField.getText().trim());
                }
                if (mp3CheckBox.isEnabled()) {
                    mp3Seclect = mp3CheckBox.isSelected();
                }

                parent.getProfilList().get(index).addtoArrayList(
                        parent.getShoppingCartListner().getMusik().getMusik_GUID(),
                        platteAnzahl,
                        cdAnzahl, mp3Seclect);
                clearTextBox();

                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Musik ist schon in der Liste vorhanden.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                clearTextBox();
                this.setVisible(false);
            }

        }
    }
}
