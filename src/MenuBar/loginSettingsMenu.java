package MenuBar;

import javax.swing.JMenu;

import App_GUI.loginGUi;

public class loginSettingsMenu extends JMenu {

    public loginSettingsMenu(loginGUi p) {
        super(p.getL10NText("seting"));
        this.add(new changeLanguageSupMenu(p, "Language"));
    }
}
