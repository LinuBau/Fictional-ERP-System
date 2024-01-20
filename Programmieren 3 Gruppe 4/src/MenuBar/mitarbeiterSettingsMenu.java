
package MenuBar;

import App_GUI.Gui;
import javax.swing.JMenu;


public class MitarbeiterSettingsMenu extends JMenu {
    public MitarbeiterSettingsMenu(Gui p){
        super(p.getL10NText("seting"));
        this.add(new NewMitarbeiterItem(p));
        this.addSeparator();
        this.add(new ChangeLanguageSupMenu(p,p.getL10NText("lang")));
        this.addSeparator();
        this.add(new AbmeldungItem(p));
    }
}
