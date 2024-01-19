package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import App_GUI.Gui;
import App_GUI.loginGUi;

public class abmeldeListner implements ActionListener {

    Gui parent;

    public abmeldeListner(Gui p) {
        this.parent = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loginGUi newLogin = new loginGUi(parent.getProfilList(), parent.getMusikMap().getMusikList(), parent.getLocale().getLanguage());
        newLogin.setTitle("Login");
        newLogin.setSize(500, 200);
        newLogin.setLocationRelativeTo(null);
        parent.setVisible(false);
        newLogin.setVisible(true);
    }

}
