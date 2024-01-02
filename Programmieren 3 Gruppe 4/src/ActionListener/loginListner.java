package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import App_GUI.Gui;
import App_GUI.loginGUi;

public class loginListner implements ActionListener {
    private final String loginString;
    private final String password;
    private loginGUi parent;

    public loginListner(loginGUi p) {
        loginString = "mitarbeiter";
        password = "mitarbeiter";
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
    }

}
