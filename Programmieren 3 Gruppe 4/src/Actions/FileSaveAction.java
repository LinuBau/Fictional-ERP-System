
package Actions;


import App_GUI.Gui;
import GeschaftsObejekt.MusikList;
import GeschaftsObejekt.ProfilList;
import SaveData_ReadData.MusikCsvListDAO;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import SaveData_ReadData.MusikCsvListDAO;
import SaveData_ReadData.MusikListDAO;
import SaveData_ReadData.ProfilListDAO;
import java.io.IOException;



public class FileSaveAction extends AbstractAction {
    private Gui parent;

    public FileSaveAction(Gui parent){
        super();
        this.parent = parent;
    }
        public static void saveListe(MusikList musikList, ProfilList profilList) {
        MusikCsvListDAO mld = new MusikCsvListDAO("Songs.csv", true);
        ProfilListDAO pld = new ProfilListDAO("logindata.data", true);
        try {
            mld.write(musikList);
            pld.write(profilList);
            mld.close();
            pld.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        FileSaveAction.saveListe(parent.getMusikMap().getMusikList(), parent.getProfilList());
        System.out.println("Speichern");
    }
}

    

