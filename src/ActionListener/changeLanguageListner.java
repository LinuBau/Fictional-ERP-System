package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JFrame;

import App_GUI.Gui;
import App_GUI.loginGUi;

public class changeLanguageListner implements ActionListener {

    Gui parent;
    loginGUi parentgGUi;
    String language;

    public changeLanguageListner(Gui p, String language) {
        parent = p;
        this.language = language;
    }

    public changeLanguageListner(loginGUi parent, String language) {
        this.parentgGUi = parent;
        this.language = language;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (parent != null) {
            int index = parent.getProfilList().getIndexofLogin();
            Gui mainWindow = new Gui(parent.getProfilList().get(index).getIsmitarbeiter(), parent.getProfilList(), parent.getMusikMap().getMusikList(), new Locale(language));
            mainWindow.setTitle(mainWindow.getL10NText("mk"));
            mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
            mainWindow.setLocationRelativeTo(null);
            parent.setVisible(false);
            mainWindow.setVisible(true);
        } else {
            loginGUi loginWindow = new loginGUi(language);
            loginWindow.setTitle("Login");
            loginWindow.setSize(500, 150);
            loginWindow.setLocationRelativeTo(null);
            parentgGUi.setVisible(false);
            loginWindow.setVisible(true);
        }

    }

}
