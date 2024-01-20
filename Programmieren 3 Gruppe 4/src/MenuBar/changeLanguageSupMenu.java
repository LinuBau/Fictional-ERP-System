package MenuBar;

import javax.swing.JMenu;

import App_GUI.Gui;
import App_GUI.loginGUi;
  

public class changeLanguageSupMenu extends JMenu {
      private String[] language = {"Deutsch","English","France","Sverige"};
    private String[] shortlanguage = {"de","en","fr","sv"};
    public changeLanguageSupMenu(Gui parent,String label){
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
    public changeLanguageSupMenu(loginGUi parent,String label){
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
