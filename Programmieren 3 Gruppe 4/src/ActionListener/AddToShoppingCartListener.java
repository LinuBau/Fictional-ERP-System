package ActionListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import App_GUI.Gui;
import GeschaftsObejekt.Musik;

public class AddToShoppingCartListener extends JDialog implements ActionListener {

    private JSpinner platteTextField;
    private JSpinner cdTextField;
    private JCheckBox mp3CheckBox;
    private JButton hinzufuegeButton;
    private int maxVinyl;
    private int maxCd;
    private Gui parent;

    public AddToShoppingCartListener(Gui p) {
        parent = p;
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 10000,1);
        SpinnerNumberModel spinnerModel1 = new SpinnerNumberModel(0, 0, 10000,1);
        platteTextField = new JSpinner(spinnerModel);
        cdTextField = new JSpinner(spinnerModel1);
        mp3CheckBox = new JCheckBox();
        JLabel cdLabel = new JLabel(parent.getL10NText("cdstue"));
        JLabel platteLabel = new JLabel(parent.getL10NText("plstue"));
        JLabel mp3Label = new JLabel(parent.getL10NText("mp3stue"));
        hinzufuegeButton = new JButton(parent.getL10NText("add"));
        JPanel centerPanel = new JPanel(new GridLayout(1, 6));
        JPanel southPanel = new JPanel(new GridLayout(1, 3));
        centerPanel.add(platteLabel);
        centerPanel.add(platteTextField);
        centerPanel.add(cdLabel);
        centerPanel.add(cdTextField);
        centerPanel.add(mp3Label);
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
        this.setSize(500, 100);
        this.setVisible(false);
    }

    private void setEnabled() {
        Musik m = parent.getShoppingCartListner().getMusik();
        System.out.println(m.toString());
        platteTextField.setEnabled(m.getIsPlatte());
        cdTextField.setEnabled(m.getIsCD());
        mp3CheckBox.setEnabled(m.isIsMp3());
    }
    
     private void setEnabledAndMaxValues() {
    Musik m = parent.getShoppingCartListner().getMusik();
    
    maxVinyl = m.getVinylCount();
    maxCd = m.getCdCount();

        platteTextField.setEnabled(m.getIsPlatte());
        cdTextField.setEnabled(m.getIsCD());
        mp3CheckBox.setEnabled(m.isIsMp3());
    }

    private void clearTextBox() {
        platteTextField.setValue(0);
        cdTextField.setValue(0);
        mp3CheckBox.setSelected(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setEnabled();
        setEnabledAndMaxValues();
        this.setVisible(true);
        if (e.getSource().equals(hinzufuegeButton)) {
            int index = parent.getProfilList().getIndexofLogin();
            if (parent.getShoppingCartListner().isInMusikList()) {
                parent.getShoppingCartListner().add();
                int platteAnzahl = 0;
                int cdAnzahl = 0;
                boolean mp3Seclect = false;
                if (platteTextField.isEnabled()) {
                    System.out.println(maxVinyl);
                    platteAnzahl = (int)platteTextField.getValue();
                    System.out.println(platteAnzahl);

                    if(platteAnzahl > maxVinyl){
                       JOptionPane.showMessageDialog(this, "Hast zu viel ausgewählt.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                       platteTextField.setValue(maxVinyl);
                        return;
                    }
                }
                if (cdTextField.isEnabled()) {
                    System.out.println(maxCd);
                    cdAnzahl = (int)(cdTextField.getValue());
                     System.out.println(cdAnzahl);
                    if(cdAnzahl >maxCd){
                       JOptionPane.showMessageDialog(this, "Hast zu viel ausgewählt." , "Error",
                        JOptionPane.ERROR_MESSAGE);
                       cdTextField.setValue(maxCd);
                       return;
                    }
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
                JOptionPane.showMessageDialog(this, parent.getL10NText("isinmes"), "Error",
                        JOptionPane.ERROR_MESSAGE);
                clearTextBox();
                this.setVisible(false);
            }

        }
    }
}
