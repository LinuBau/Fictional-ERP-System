
package MenuBar;

import javax.swing.JMenuBar;

import App_GUI.Gui;

public class mitarbeiterMenuBar extends JMenuBar {
     
    public mitarbeiterMenuBar(Gui parent){
       super();
       this.add(new FileMenu(parent));
       this.add(new SettingsMenu(parent));
    }
}
