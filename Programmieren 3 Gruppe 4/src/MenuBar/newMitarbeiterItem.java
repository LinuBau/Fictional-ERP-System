
package MenuBar;

import ActionListener.newMitarbeiterListener;
import App_GUI.Gui;
import javax.swing.JMenuItem;


public class newMitarbeiterItem extends JMenuItem {
    public newMitarbeiterItem(Gui p){
        super(p.getL10NText("mithinf"));
        this.addActionListener(new newMitarbeiterListener(p));
    }
}
