package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import App_GUI.Gui;
import App_GUI.LoginGUi;
import GeschaftsObejekt.MusikList;
import GeschaftsObejekt.ProfilList;

public class LoginListener implements ActionListener {

    private LoginGUi parent;
    private MusikList musikList;

    public LoginListener(LoginGUi p) {
        parent = p;
    }

    public LoginListener(LoginGUi p, MusikList ml) {
        this.parent = p;
        musikList = ml;
    }

    public static void login(boolean ismitarbeiter, ProfilList profilList, LoginGUi parent) {
        Gui mainWindow = new Gui(ismitarbeiter, profilList, parent.getLocale());
        mainWindow.setTitle(mainWindow.getL10NText("mk"));
        mainWindow.setSize(1000,800);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setLocationRelativeTo(null);
        parent.setVisible(false);
        mainWindow.setVisible(true);
    }

    public static void login(boolean ismitarbeiter, ProfilList profilList, LoginGUi parent, MusikList musikList) {
        Gui mainWindow = new Gui(ismitarbeiter, profilList, musikList, parent.getLocale());
        mainWindow.setTitle(mainWindow.getL10NText("mk"));
        mainWindow.setSize(1000,800);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setLocationRelativeTo(null);
        parent.setVisible(false);
        mainWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (musikList == null) {
            int index = parent.getProfilList().indexOfLogin(parent.getUsername().trim(), parent.getPassword());
            if (index != -1) {
                login(parent.getProfilList().get(index).getIsmitarbeiter(), parent.getProfilList(), parent);
            }
        } else {
            int index = parent.getProfilList().indexOfLogin(parent.getUsername().trim(), parent.getPassword());
            if (index != -1) {
                login(parent.getProfilList().get(index).getIsmitarbeiter(), parent.getProfilList(), parent, musikList);
            }
        }

    }

}
