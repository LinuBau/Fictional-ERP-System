package MenuBar;

import javax.swing.JMenuBar;

public class benutzerMenuBar extends JMenuBar {
    public benutzerMenuBar() {
        super();
        this.add(new SettingsMenu());
    }

}
