package MenuBar;

import javax.swing.JMenuItem;

import ActionListener.abmeldeListner;
import App_GUI.Gui;

public class abmeldungItem extends JMenuItem {

    public abmeldungItem(Gui p) {
        super(p.getL10NText("out"));
        this.addActionListener(new abmeldeListner(p));
    }
}
