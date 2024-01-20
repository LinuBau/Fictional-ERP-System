package ActionListener;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import App_GUI.LoginGUi;
import GeschaftsObejekt.Profil;

public class NewUserListener extends JDialog implements ActionListener {
    private JPanel centerconetentPanel;
    private boolean ismitarbeiter = false;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JButton hinzufügenButton;
    private JCheckBox showPasswordCheckBox;
    private JTextField usserNameTextField;
    private JPasswordField passwordField;
    private JPanel southPanel;
    private LoginGUi parent;

    public NewUserListener(LoginGUi lg) {
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
        showPasswordCheckBox.addActionListener(new ShowPasswordListener(showPasswordCheckBox,passwordField));
        hinzufügenButton.addActionListener(this);
        
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(centerconetentPanel, BorderLayout.CENTER);
        this.getContentPane().add(southPanel, BorderLayout.SOUTH);
        this.setSize(500, 150);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(true);
        if (e.getSource().equals(hinzufügenButton)) {
            String ussername = usserNameTextField.getText().trim();
            if(parent.getProfilList().unique(ussername)){
                 Profil p = new Profil(ussername, getPassword(), ismitarbeiter);
            parent.getProfilList().add(p);
            NewMitarbeiterListener.saveProfil(parent.getProfilList());
            }
            int index = parent.getProfilList().indexOfLogin(ussername, getPassword());
            if (index != -1) {
                LoginListener.login(parent.getProfilList().get(index).getIsmitarbeiter(), parent.getProfilList(),parent);
            }
            this.setVisible(false);
        }
    }

    public int getPassword() {
        String tmp = new String(passwordField.getPassword());
        return tmp.hashCode();
    }
}
