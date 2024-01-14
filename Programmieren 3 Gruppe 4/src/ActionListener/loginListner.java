package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import App_GUI.Gui;
import App_GUI.loginGUi;
import GeschaftsObejekt.profilList;

public class loginListner implements ActionListener {
    private loginGUi parent;

    public loginListner(loginGUi p) {
        parent = p;
    }

    public static void login(boolean ismitarbeiter, profilList profilList, loginGUi parent) {
        Gui mainWindow = new Gui(ismitarbeiter, profilList);
        mainWindow.setTitle("Musik Katalog");
        mainWindow.setExtendedState(mainWindow.MAXIMIZED_BOTH);
        mainWindow.setLocationRelativeTo(null);
        parent.setVisible(false);
        mainWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = parent.getProfilList().indexOfLogin(parent.getUsername().trim(), parent.getPassword());
        if (index != -1) {
            login(parent.getProfilList().get(index).getIsmitarbeiter(), parent.getProfilList(), parent);
        }
    }

}
