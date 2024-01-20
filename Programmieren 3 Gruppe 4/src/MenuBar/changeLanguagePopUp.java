package MenuBar;

import javax.swing.JMenu;

import App_GUI.Gui;
import App_GUI.loginGUi;

public class changeLanguagePopUp extends JMenu {
    public changeLanguagePopUp(Gui parent,String label,String[] shortlanguage,String[] language){
        super(label);
        for(int i=0;i<shortlanguage.length;i++){
            boolean b = !(shortlanguage[i].equals(parent.getLocale().getLanguage()));
            System.out.println(b);
            if (b) {
                changeLanguageItem changeLanguageItem = new changeLanguageItem(parent,language[i],shortlanguage[i]);
                add(changeLanguageItem);
            }
        }
        
    }
    public changeLanguagePopUp(loginGUi parent,String label,String[] shortlanguage,String[] language){
        super(label);
        for(int i=0;i<shortlanguage.length;i++){
            boolean b = !(shortlanguage[i].equals(parent.getLocale().getLanguage()));
            System.out.println(b);
            if (b) {
                changeLanguageItem changeLanguageItem = new changeLanguageItem(parent,language[i],shortlanguage[i]);
                add(changeLanguageItem);
            }
        }
    }
    
}
