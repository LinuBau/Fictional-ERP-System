package MenuBar;

import javax.swing.JMenuBar;

import App_GUI.Gui;

public class MitarbeiterMenuBar extends JMenuBar {

    public MitarbeiterMenuBar(Gui parent) {
        super();
        this.add(new FileMenu(parent));
        this.add(new MitarbeiterSettingsMenu(parent));
    }
}
