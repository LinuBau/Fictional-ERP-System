
package MenuBar;

import App_GUI.Gui;
import javax.swing.JMenu;


public class mitarbeiterSettingsMenu extends JMenu {
    public mitarbeiterSettingsMenu(Gui p){
        super(p.getL10NText("seting"));
        this.add(new newMitarbeiterItem(p));
        this.addSeparator();
        this.add(new changeLanguageSupMenu(p,p.getL10NText("lang")));
        this.addSeparator();
        this.add(new abmeldungItem(p));
    }
}
