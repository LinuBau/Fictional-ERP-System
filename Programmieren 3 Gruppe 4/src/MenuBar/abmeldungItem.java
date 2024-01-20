package MenuBar;

import javax.swing.JMenuItem;

import ActionListener.AbmeldeListener;
import App_GUI.Gui;

public class AbmeldungItem extends JMenuItem {

    public AbmeldungItem(Gui p) {
        super(p.getL10NText("out"));
        this.addActionListener(new AbmeldeListener(p));
    }
}
