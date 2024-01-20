
package MenuBar;

import App_GUI.Gui;
import javax.swing.JMenu;


public class SettingsMenu extends JMenu{
    
    SettingsMenu(Gui p){
        super(p.getL10NText("seting"));
        this.add(new ChangeLanguageSupMenu(p,p.getL10NText("lang")));
        this.addSeparator();
        this.add(new AbmeldungItem(p));
    }
}
