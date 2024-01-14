
package MenuBar;

import App_GUI.Gui;
import javax.swing.JMenu;


public class SettingsMenu extends JMenu{
    
    SettingsMenu(Gui p,String[] language,String[] shortlanguage){
        super(p.getL10NText("seting"));
        this.add(new changeLanguagePopUp(p,"Language",shortlanguage,language));
        this.addSeparator();
        this.add(new abmeldungItem(p));
    }
}
