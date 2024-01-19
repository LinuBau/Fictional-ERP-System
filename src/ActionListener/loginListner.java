package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import App_GUI.Gui;
import App_GUI.loginGUi;
import GeschaftsObejekt.MusikList;
import GeschaftsObejekt.profilList;

public class loginListner implements ActionListener {

    private loginGUi parent;
    private MusikList musikList;

    public loginListner(loginGUi p) {
        parent = p;
    }

    public loginListner(loginGUi p, MusikList ml) {
        this.parent = p;
        musikList = ml;
    }

    public static void login(boolean ismitarbeiter, profilList profilList, loginGUi parent) {
        Gui mainWindow = new Gui(ismitarbeiter, profilList, parent.getLocale());
        mainWindow.setTitle(mainWindow.getL10NText("mk"));
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setLocationRelativeTo(null);
        parent.setVisible(false);
        mainWindow.setVisible(true);
    }

    public static void login(boolean ismitarbeiter, profilList profilList, loginGUi parent, MusikList musikList) {
        Gui mainWindow = new Gui(ismitarbeiter, profilList, musikList, parent.getLocale());
        mainWindow.setTitle(mainWindow.getL10NText("mk"));
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
