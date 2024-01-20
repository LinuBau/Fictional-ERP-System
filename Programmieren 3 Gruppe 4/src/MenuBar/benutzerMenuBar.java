package MenuBar;

import javax.swing.JMenuBar;

import App_GUI.Gui;

public class BenutzerMenuBar extends JMenuBar {

    public BenutzerMenuBar(Gui p) {
        super();
        this.add(new SettingsMenu(p));
    }

}
