
package MenuBar;

import App_GUI.Gui;
import javax.swing.JMenu;


public class SettingsMenu extends JMenu{
    
    SettingsMenu(Gui p){
        super("Settings");
        this.add(new abmeldungItem(p));
    }
}
