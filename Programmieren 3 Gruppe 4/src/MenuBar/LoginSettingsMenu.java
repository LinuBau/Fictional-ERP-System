package MenuBar;

import javax.swing.JMenu;

import App_GUI.LoginGUi;

public class LoginSettingsMenu extends JMenu {

    public LoginSettingsMenu(LoginGUi p) {
        super(p.getL10NText("seting"));
        this.add(new ChangeLanguageSupMenu(p, p.getL10NText("lang")));
    }
}
