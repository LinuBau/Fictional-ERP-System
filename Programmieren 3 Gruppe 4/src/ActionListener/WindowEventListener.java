
package ActionListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import App_GUI.Gui;
import GeschaftsObejekt.MusikList;
import GeschaftsObejekt.profilList;
import SaveData_ReadData.MusikListDAO;
import SaveData_ReadData.ProfilListDOA;


public class WindowEventListener implements WindowListener{
    private Gui parent;
    public WindowEventListener(Gui p){
        super();
        this.parent = p;
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
       
        System.out.println("Fenster geöffnet");
    }

    @Override
    public void windowClosing(WindowEvent e)    {
        WindowEventListener.saveListe(parent.getMusikMap().getMusikList(), parent.getProfilList());
        System.out.println("Fenster schließen");
        System.exit(0);    
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Fenster geschlossen");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("Fenster verkleinert");    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("Fenster vergrößert");    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("Fenster öffnen");    
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("Fenster beenden");
    }

    public static void saveListe(MusikList musikList,profilList profilList){
             MusikListDAO mld = new MusikListDAO("setup.data", true);
        ProfilListDOA pld = new ProfilListDOA("logindata.data", true);
        try {
            mld.write(musikList);
            pld.write(profilList);
            mld.close();
            pld.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
   
    
}
