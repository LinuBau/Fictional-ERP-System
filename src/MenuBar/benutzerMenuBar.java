package MenuBar;

import javax.swing.JMenuBar;

import App_GUI.Gui;

public class benutzerMenuBar extends JMenuBar {

    public benutzerMenuBar(Gui p) {
        super();
        this.add(new SettingsMenu(p));
    }

}
