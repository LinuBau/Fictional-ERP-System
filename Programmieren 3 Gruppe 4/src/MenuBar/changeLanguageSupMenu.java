package MenuBar;

import javax.swing.JMenu;

import App_GUI.Gui;
import App_GUI.LoginGUi;
  

public class ChangeLanguageSupMenu extends JMenu {
      private String[] language = {"Deutsch","English","France","Sverige"};
    private String[] shortlanguage = {"de","en","fr","sv"};
    public ChangeLanguageSupMenu(Gui parent,String label){
        super(label);
        for(int i=0;i<shortlanguage.length;i++){
            boolean b = !(shortlanguage[i].equals(parent.getLocale().getLanguage()));
            System.out.println(b);
            if (b) {
                ChangeLanguageItem changeLanguageItem = new ChangeLanguageItem(parent,language[i],shortlanguage[i]);
                add(changeLanguageItem);
            }
        }
        
    }
    public ChangeLanguageSupMenu(LoginGUi parent,String label){
        super(label);
        for(int i=0;i<shortlanguage.length;i++){
            boolean b = !(shortlanguage[i].equals(parent.getLocale().getLanguage()));
            System.out.println(b);
            if (b) {
                ChangeLanguageItem changeLanguageItem = new ChangeLanguageItem(parent,language[i],shortlanguage[i]);
                add(changeLanguageItem);
            }
        }
    }
    
}
