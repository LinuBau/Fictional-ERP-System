
package MenuBar;

import javax.swing.JMenuBar;

import App_GUI.Gui;

public class MenuBar extends JMenuBar {
     
    public MenuBar(Gui parent){
       super();
       this.add(new FileMenu(parent));
       this.add(new SettingsMenu());
    }
}
