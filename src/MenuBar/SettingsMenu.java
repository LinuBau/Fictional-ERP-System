package MenuBar;

import App_GUI.Gui;
import javax.swing.JMenu;

public class SettingsMenu extends JMenu {

    SettingsMenu(Gui p) {
        super(p.getL10NText("seting"));
        this.add(new changeLanguageSupMenu(p, "Language"));
        this.addSeparator();
        this.add(new abmeldungItem(p));
    }
}
