package ActionListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import App_GUI.Gui;

public class addToShoppingCart extends JDialog implements ActionListener {
    private TextField platteTextField;
    private TextField cdTextField;
    private JCheckBox mp3CheckBox;
    private JButton hinzufuegeButton;
    private Gui parent;
    public addToShoppingCart(Gui p){
        parent = p;
        platteTextField = new TextField();
        cdTextField = new TextField();
        mp3CheckBox = new JCheckBox();
        hinzufuegeButton = new JButton("Hinzufügen");
        JPanel centerPanel = new JPanel(new GridLayout(1, 3));
        JPanel southPanel = new JPanel(new GridLayout(1,3));
        centerPanel.add(platteTextField);
        centerPanel.add(cdTextField);
        centerPanel.add(mp3CheckBox);

        southPanel.add(new JPanel());
        southPanel.add(hinzufuegeButton);
        southPanel.add(new JPanel());
        hinzufuegeButton.addActionListener(this);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(centerPanel,BorderLayout.CENTER);
        this.getContentPane().add(hinzufuegeButton,BorderLayout.SOUTH);
        this.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
        this.setLocationRelativeTo(parent);
        clearTextBox();
        this.setSize(150, 100);
        this.setVisible(false);
    }
    private void clearTextBox(){
        platteTextField.setText("");
        cdTextField.setText("");
        mp3CheckBox.setSelected(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(true);
        if (e.getSource().equals(hinzufuegeButton)) {
            int index = parent.getProfilList().getIndexofLogin();
            parent.getShoppingCartListner().add(Integer.parseInt(platteTextField.getText().trim()),Integer.parseInt(cdTextField.getText().trim()));
            parent.getProfilList().get(index).addtoArrayList(parent.getShoppingCartListner().getMusik().getMusik_GUID(),Integer.parseInt(platteTextField.getText().trim()),
            Integer.parseInt(cdTextField.getText().trim()),mp3CheckBox.isSelected());
            clearTextBox();
            this.setVisible(false);
        }
    }
}
