package MenuBar;

import ActionListener.NewMitarbeiterListener;
import App_GUI.Gui;
import javax.swing.JMenuItem;

public class NewMitarbeiterItem extends JMenuItem {

    public NewMitarbeiterItem(Gui p) {
        super(p.getL10NText("mithinf"));
        this.addActionListener(new NewMitarbeiterListener(p));
    }
}
