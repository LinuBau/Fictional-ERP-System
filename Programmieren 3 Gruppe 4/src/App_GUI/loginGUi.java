package App_GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ActionListener.loginListner;
import ActionListener.newUserListener;
import GeschaftsObejekt.profilList;
import SaveData_ReadData.ProfilListDOA;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class loginGUi extends JFrame {
    private JPanel centerconetentPanel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private profilList profilList;
    private JTextField usserNameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton newUserButton;
    
    public loginGUi() {
        profilList = new profilList();
        try {
            ProfilListDOA profilListDOA = new ProfilListDOA("logindata.data", false);
            profilListDOA.read(profilList);
            profilListDOA.close();
            System.out.println(profilList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        centerconetentPanel = new JPanel(new GridLayout(4, 4));
        userNameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password");
        usserNameTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        newUserButton = new JButton("Neu Hinzufügen");
        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(userNameLabel);
        centerconetentPanel.add(usserNameTextField);
        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(passwordLabel);
        centerconetentPanel.add(passwordField);
        JPanel southPanel = new JPanel(new GridLayout(1,2));
        southPanel.add(newUserButton);
        southPanel.add(loginButton);

        loginButton.addActionListener(new loginListner(this));
        newUserButton.addActionListener(new newUserListener(this));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(centerconetentPanel, BorderLayout.CENTER);
        getContentPane().add(southPanel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    public String getUsername() {
        return this.usserNameTextField.getText();
    }
    public profilList getProfilList(){
        return this.profilList;
    }

    public int getPassword() {
        String tmp = new String(passwordField.getPassword());
        return tmp.hashCode();
    }
    public void w(){
        try {
            ProfilListDOA pd = new ProfilListDOA("logindata.data", true);
            pd.write(profilList);
            pd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}
