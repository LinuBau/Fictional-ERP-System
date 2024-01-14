package MenuBar;

import javax.swing.JMenuItem;

import ActionListener.changeLanguageListner;
import App_GUI.Gui;

public class changeLanguageItem extends JMenuItem {
    public changeLanguageItem(Gui parent,String language,String languageshort){
        super(language);
        addActionListener(new changeLanguageListner(parent, languageshort));
    }
}
