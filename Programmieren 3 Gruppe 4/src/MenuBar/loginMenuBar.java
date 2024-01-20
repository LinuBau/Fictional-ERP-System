package MenuBar;

import javax.swing.JMenuBar;

import App_GUI.LoginGUi;

public class LoginMenuBar extends JMenuBar {

    public LoginMenuBar(LoginGUi parent) {
        this.add(new LoginSettingsMenu(parent));
    }
}
