package App_GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ActionListener.loginListner;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class loginGUi extends JFrame {
    private JPanel centerconetentPanel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JTextField usserNameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public loginGUi() {
        centerconetentPanel = new JPanel(new GridLayout(4, 4));
        userNameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password");
        usserNameTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(userNameLabel);
        centerconetentPanel.add(usserNameTextField);
        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(new JPanel());
        centerconetentPanel.add(passwordLabel);
        centerconetentPanel.add(passwordField);
        loginButton.addActionListener(new loginListner(this));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(centerconetentPanel, BorderLayout.CENTER);
        getContentPane().add(loginButton, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

    }

    public String getUsername() {
        return this.usserNameTextField.getText();
    }

    public String getPassword() {
        String tmp = new String(passwordField.getPassword());
        return tmp;
    }
}
