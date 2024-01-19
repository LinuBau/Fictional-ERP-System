package MenuBar;

import javax.swing.JMenuItem;

import ActionListener.newMitarbeiterListener;
import App_GUI.Gui;

public class mitarbeiterHinzufügenItem extends JMenuItem {

    public mitarbeiterHinzufügenItem(Gui p){
        super(p.getL10NText("mithinf"));
        this.addActionListener(new newMitarbeiterListener(p));
    }
}
