package MenuBar;

import javax.swing.JMenuItem;

import ActionListener.changeLanguageListner;
import App_GUI.Gui;
import App_GUI.loginGUi;

public class changeLanguageItem extends JMenuItem {

    public changeLanguageItem(Gui parent, String language, String languageshort) {
        super(language);
        addActionListener(new changeLanguageListner(parent, languageshort));
    }

    public changeLanguageItem(loginGUi parent, String language, String languageshort) {
        super(language);
        addActionListener(new changeLanguageListner(parent, languageshort));
    }
}
