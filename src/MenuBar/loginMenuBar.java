package MenuBar;

import javax.swing.JMenuBar;

import App_GUI.loginGUi;

public class loginMenuBar extends JMenuBar  {
    public loginMenuBar(loginGUi parent,String[] language, String[] shortlanguage){
        this.add(new loginSettingsMenu(parent,language,shortlanguage));
    }
}
