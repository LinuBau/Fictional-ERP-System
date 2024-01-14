package MenuBar;

import javax.swing.JMenu;

import App_GUI.Gui;

public class changeLanguagePopUp extends JMenu {
    public changeLanguagePopUp(Gui parent,String label,String[] shortlanguage,String[] language){
        super(label);
        for(int i=0;i<shortlanguage.length;i++){
            boolean b = !(shortlanguage[i].equals(parent.getLocale().getLanguage()));
            if (b) {
                changeLanguageItem changeLanguageItem = new changeLanguageItem(parent,language[i],shortlanguage[i]);
                add(changeLanguageItem);
            }
        }
        
    }
    
}
