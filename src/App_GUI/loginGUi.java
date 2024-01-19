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
import GeschaftsObejekt.profilList;
import MenuBar.loginMenuBar;
import SaveData_ReadData.ProfilListDOA;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class loginGUi extends JFrame {

    private JPanel centerconetentPanel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JCheckBox showPassword;
    private profilList profilList;
    private JTextField usserNameTextField;
    private JPasswordField passwordField;
    private MusikList musikList;
    private Locale locale;
    private ResourceBundle bundel;
    private JButton loginButton;
    private JButton newUserButton;
    
    public loginGUi(String languageShort) {
        profilList = new profilList();
        try {
            ProfilListDOA profilListDOA = new ProfilListDOA("logindata.data", false);
            profilListDOA.read(profilList);
            profilListDOA.close();
            System.out.println(profilList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        locale = new Locale(languageShort);
        bundel = ResourceBundle.getBundle("I18NPropertiesFiles/Bundel", locale);
        createLoginPanel();
    }

    public loginGUi(profilList profilList, MusikList musikList, String languageShort) {
        this.profilList = profilList;
        locale = new Locale(languageShort);
        bundel = ResourceBundle.getBundle("I18NPropertiesFiles/Bundel", locale);
        createLoginPanel();
    }

    public void createLoginPanel() {
        centerconetentPanel = new JPanel(new GridLayout(5, 4));
        userNameLabel = new JLabel("   " + this.getL10NText("usser") + ": ");
        passwordLabel = new JLabel("   " + this.getL10NText("psw") + ": ");
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

        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick(); // Simuliert einen Klick auf den Login-Button durch enter
            }
        });

        JPanel southPanel = new JPanel(new GridLayout(1, 3));
        loginButton = new JButton(this.getL10NText("log"));
        newUserButton = new JButton(this.getL10NText("nhin"));
        showPassword = new JCheckBox(this.getL10NText("pa"));
        southPanel.add(newUserButton);
        southPanel.add(loginButton);
        southPanel.add(showPassword);
        makeActionListner();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(centerconetentPanel, BorderLayout.CENTER);
        getContentPane().add(southPanel, BorderLayout.SOUTH);
        setJMenuBar(new loginMenuBar(this));

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private  void makeActionListner(){
        if (musikList == null) {
            loginButton.addActionListener(new loginListner(this));
            newUserButton.addActionListener(new newUserListener(this));
            showPassword.addActionListener(new showPasswordListner(showPassword, passwordField));
        } else {
            loginButton.addActionListener(new loginListner(this, musikList));
            newUserButton.addActionListener(new newUserListener(this));
            showPassword.addActionListener(new showPasswordListner(showPassword, passwordField));
        }

    }

    public String getL10NText(String key) {
        return bundel.getString(key);
    }

    public String getUsername() {
        return this.usserNameTextField.getText();
    }

    public Locale getLocale() {
        return locale;
    }

    public profilList getProfilList() {
        return this.profilList;
    }

    public int getPassword() {
        String tmp = new String(passwordField.getPassword());
        return tmp.hashCode();
    }

    public void w() {
        try {
            ProfilListDOA pd = new ProfilListDOA("logindata.data", true);
            pd.write(profilList);
            pd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
