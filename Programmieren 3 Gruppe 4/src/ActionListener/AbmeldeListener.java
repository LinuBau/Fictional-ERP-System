package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import App_GUI.Gui;
import App_GUI.LoginGUi;

public class AbmeldeListener implements ActionListener {

    Gui parent;

    public AbmeldeListener(Gui p) {
        this.parent = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LoginGUi newLogin = new LoginGUi(parent.getProfilList(), parent.getMusikMap().getMusikList(), parent.getLocale().getLanguage());
        newLogin.setTitle("Login");
        newLogin.setSize(500, 150);
        newLogin.setLocationRelativeTo(null);
        parent.setVisible(false);
        newLogin.setVisible(true);
    }

}
