package ActionListener;

import App_GUI.Gui;
import GeschaftsObejekt.profil;
import GeschaftsObejekt.profilList;
import SaveData_ReadData.ProfilListDOA;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author linus
 */
public class newMitarbeiterListener extends JDialog implements ActionListener {

    private JPanel centerconetentPanel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JButton hinzufügenButton;
    private JCheckBox showPasswordCheckBox;
    private JTextField usserNameTextField;
    private JPasswordField passwordField;
    private JPanel southPanel;
    private Gui parent;

    public newMitarbeiterListener(Gui lg) {
        centerconetentPanel = new JPanel(new GridLayout(5, 4));
        southPanel = new JPanel(new FlowLayout());
        parent = lg;

        showPasswordCheckBox = new JCheckBox(parent.getL10NText("pa"));
        hinzufügenButton = new JButton(parent.getL10NText("add"));
        userNameLabel = new JLabel(parent.getL10NText("usser"));
        passwordLabel = new JLabel(parent.getL10NText("psw"));

        usserNameTextField = new JTextField();
        passwordField = new JPasswordField();

        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(userNameLabel);
        centerconetentPanel.add(usserNameTextField);
        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(passwordLabel);
        centerconetentPanel.add(passwordField);
        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(new JPanel());
        this.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
        this.setLocationRelativeTo(parent);

        southPanel.add(showPasswordCheckBox);
        southPanel.add(hinzufügenButton);
        showPasswordCheckBox.addActionListener(new showPasswordListner(showPasswordCheckBox, passwordField));
        hinzufügenButton.addActionListener(this);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(centerconetentPanel, BorderLayout.CENTER);
        this.getContentPane().add(southPanel, BorderLayout.SOUTH);
        this.setSize(500, 150);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }

    public int getPassword() {
        String tmp = new String(passwordField.getPassword());
        return tmp.hashCode();
    }

    public static void saveProfil(profilList profilList) {
        ProfilListDOA pld = new ProfilListDOA("logindata.data", true);
        try {
            pld.write(profilList);
            pld.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(true);
        if (e.getSource().equals(hinzufügenButton)) {
            String ussername = usserNameTextField.getText().trim();
            profil p = new profil(ussername, getPassword(), true);
            if (parent.getProfilList().unique(ussername)) {
                parent.getProfilList().add(p);
                newMitarbeiterListener.saveProfil(parent.getProfilList());
                JOptionPane.showMessageDialog(parent, parent.getL10NText("mithin"));
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Ussername ist vergeben", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
