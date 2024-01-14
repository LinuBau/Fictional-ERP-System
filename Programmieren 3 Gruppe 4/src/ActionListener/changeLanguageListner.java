package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JFrame;

import App_GUI.Gui;

public class changeLanguageListner implements ActionListener {

    Gui parent;
    String language;

    public changeLanguageListner(Gui p,String language){
        parent =p;
        this.language = language;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = parent.getProfilList().getIndexofLogin();
        Gui mainWindow = new Gui(parent.getProfilList().get(index).getIsmitarbeiter(),parent.getProfilList(),parent.getMusikMap().getMusikList(),new Locale(language));
        mainWindow.setTitle(mainWindow.getL10NText("mk"));
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setLocationRelativeTo(null);
        parent.setVisible(false);
        mainWindow.setVisible(true);
    }
    
}
