package App_GUI;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ActionListener.loginListner;
import ActionListener.newUserListener;
import ActionListener.showPasswordListner;
import GeschaftsObejekt.MusikList;
import GeschaftsObejekt.profil;
import GeschaftsObejekt.profilList;
import SaveData_ReadData.ProfilListDOA;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class loginGUi extends JFrame {
    private JPanel centerconetentPanel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JCheckBox showPassword;
    private profilList profilList;
    private JTextField usserNameTextField;
    private JPasswordField passwordField;
    private MusikList musikList;
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
        String mit = "mitarbeiter";
        profil m = new profil(mit, mit.hashCode(), true);
        profilList.add(m);
        createLoginPlane();
    }

    public loginGUi(profilList profilList,MusikList musikList){
        this.profilList = profilList;
        createLoginPlane();
    }
    
    public void createLoginPlane(){
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
        showPassword = new JCheckBox("Passwort anzeigen");
        JPanel southPanel = new JPanel(new GridLayout(1,3));
        southPanel.add(newUserButton);
        southPanel.add(loginButton);
        southPanel.add(showPassword);
        makeActionListner();
     

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(centerconetentPanel, BorderLayout.CENTER);
        getContentPane().add(southPanel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private  void makeActionListner(){
        if (musikList == null) {
            loginButton.addActionListener(new loginListner(this));
            newUserButton.addActionListener(new newUserListener(this));
            showPassword.addActionListener(new showPasswordListner(showPassword, passwordField));
        }else{
            loginButton.addActionListener(new loginListner(this,musikList));
            newUserButton.addActionListener(new newUserListener(this));
            showPassword.addActionListener(new showPasswordListner(showPassword, passwordField));
        }
       
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
