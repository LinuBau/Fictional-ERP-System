package MenuBar;

import javax.swing.JMenuItem;

import ActionListener.ChangeLanguageListener;
import App_GUI.Gui;
import App_GUI.LoginGUi;

public class ChangeLanguageItem extends JMenuItem {

    public ChangeLanguageItem(Gui parent, String language, String languageshort) {
        super(language);
        addActionListener(new ChangeLanguageListener(parent, languageshort));
    }

    public ChangeLanguageItem(LoginGUi parent, String language, String languageshort) {
        super(language);
        addActionListener(new ChangeLanguageListener(parent, languageshort));
    }
}
