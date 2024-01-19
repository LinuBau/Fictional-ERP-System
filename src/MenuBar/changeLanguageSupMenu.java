package MenuBar;

import javax.swing.JMenu;

import App_GUI.Gui;
import App_GUI.loginGUi;

<<<<<<<< Updated upstream:src/MenuBar/changeLanguagePopUp.java
public class changeLanguagePopUp extends JMenu {

    public changeLanguagePopUp(Gui parent, String label, String[] shortlanguage, String[] language) {
========

public class changeLanguageSupMenu extends JMenu {
    private final String[] language = {"Deutsch","English","France","Sverige"};
    private final String[] shortlanguage = {"de","en","fr","sv"};
    public changeLanguageSupMenu(Gui parent,String label){
>>>>>>>> Stashed changes:src/MenuBar/changeLanguageSupMenu.java
        super(label);
        for (int i = 0; i < shortlanguage.length; i++) {
            boolean b = !(shortlanguage[i].equals(parent.getLocale().getLanguage()));
            System.out.println(b);
            if (b) {
                changeLanguageItem changeLanguageItem = new changeLanguageItem(parent, language[i], shortlanguage[i]);
                add(changeLanguageItem);
            }
        }

    }
<<<<<<<< Updated upstream:src/MenuBar/changeLanguagePopUp.java

    public changeLanguagePopUp(loginGUi parent, String label, String[] shortlanguage, String[] language) {
========
    public changeLanguageSupMenu(loginGUi parent,String label){
>>>>>>>> Stashed changes:src/MenuBar/changeLanguageSupMenu.java
        super(label);
        for (int i = 0; i < shortlanguage.length; i++) {
            boolean b = !(shortlanguage[i].equals(parent.getLocale().getLanguage()));
            System.out.println(b);
            if (b) {
                changeLanguageItem changeLanguageItem = new changeLanguageItem(parent, language[i], shortlanguage[i]);
                add(changeLanguageItem);
            }
        }
    }

}
