package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import App_GUI.Gui;
import App_GUI.loginGUi;

public class loginListner implements ActionListener {
    private final String loginString;
    private final String loginString2;
    private final String password;
    private final String password1;
    private loginGUi parent;

    public loginListner(loginGUi p) {
        loginString = "mitarbeiter";
        password = "mitarbeiter";
        password1 = "benutzer";
        loginString2 = "benutzer";
        parent = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (parent.getPassword().trim().equals(password)) {
            if (parent.getUsername().trim().equals(loginString)) {
                Gui mainWindow = new Gui(true);
                mainWindow.setTitle("Musik Katalog");
                mainWindow.setSize(1000, 500);
                mainWindow.setLocationRelativeTo(null);
                parent.setVisible(false);
                mainWindow.setVisible(true);
            }
        }
        if (parent.getPassword().trim().equals(password1)) {
             if (parent.getUsername().trim().equals(loginString2)) {
                Gui mainWindow = new Gui(false);
                mainWindow.setTitle("Musik Katalog");
                mainWindow.setSize(1000, 500);
                mainWindow.setLocationRelativeTo(null);
                parent.setVisible(false);
                mainWindow.setVisible(true);
            }
        }

    }

}
