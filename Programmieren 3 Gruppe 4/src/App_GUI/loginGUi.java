package App_GUI;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ActionListener.LoginListener;
import ActionListener.NewUserListener;
import ActionListener.ShowPasswordListener;
import GeschaftsObejekt.MusikList;
import GeschaftsObejekt.Profil;
import GeschaftsObejekt.ProfilList;
import MenuBar.LoginMenuBar;
import SaveData_ReadData.ProfilListDAO;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginGUi extends JFrame {
    private JPanel centerconetentPanel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JCheckBox showPassword;
    private ProfilList profilList;
    private JTextField usserNameTextField;
    private JPasswordField passwordField;
    private MusikList musikList;
    private Locale locale;
    private ResourceBundle bundel;
    private JButton loginButton;
    private JButton newUserButton;
    private String[] language = {"Deutsch","English","France","Sverige"};
    private String[] shortlanguage = {"de","en","fr","sv"};
    
    public LoginGUi(String languageShort) {
        profilList = new ProfilList();
        try {
            ProfilListDAO profilListDOA = new ProfilListDAO("logindata.data", false);
            profilListDOA.read(profilList);
            profilListDOA.close();
            System.out.println(profilList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String mit = "mitarbeiter";
        Profil m = new Profil(mit, mit.hashCode(), true);
        locale = new Locale(languageShort);
        bundel = ResourceBundle.getBundle("I18NPropertiesFiles/Bundel", locale);
        profilList.add(m);
        createLoginPanel();
    }

    public LoginGUi(ProfilList profilList,MusikList musikList,String languageShort){
        this.profilList = profilList;
        locale = new Locale(languageShort);
        bundel = ResourceBundle.getBundle("I18NPropertiesFiles/Bundel", locale);
        createLoginPanel();
    }
    
    public void createLoginPanel(){
        centerconetentPanel = new JPanel(new GridLayout(5, 4));
        userNameLabel = new JLabel(this.getL10NText("usser")+": ");
        passwordLabel = new JLabel(this.getL10NText("psw"));
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
        
        JPanel southPanel = new JPanel(new GridLayout(1,3));
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
        setJMenuBar(new LoginMenuBar(this));

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private  void makeActionListner(){
        if (musikList == null) {
            loginButton.addActionListener(new LoginListener(this));
            newUserButton.addActionListener(new NewUserListener(this));
            showPassword.addActionListener(new ShowPasswordListener(showPassword, passwordField));
        }else{
            loginButton.addActionListener(new LoginListener(this,musikList));
            newUserButton.addActionListener(new NewUserListener(this));
            showPassword.addActionListener(new ShowPasswordListener(showPassword, passwordField));
        }
       
    }

    public String getL10NText(String key){
        return bundel.getString(key);
    }

    public String getUsername() {
        return this.usserNameTextField.getText();
    }
    public Locale getLocale(){
        return locale;
    }

    public ProfilList getProfilList(){
        return this.profilList;
    }
    public int getPassword() {
        String tmp = new String(passwordField.getPassword());
        return tmp.hashCode();
    }
    public void w(){
        try {
            ProfilListDAO pd = new ProfilListDAO("logindata.data", true);
            pd.write(profilList);
            pd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}
