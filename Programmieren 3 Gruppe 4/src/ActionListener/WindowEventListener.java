
package ActionListener;

import Actions.FileSaveAsAction;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import App_GUI.Gui;
import GeschaftsObejekt.MusikList;
import SaveData_ReadData.MusikCsvListDAO;
import SaveData_ReadData.MusikListDAO;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class WindowEventListener implements WindowListener {
    private Gui parent;
    private MusikList musikList;
    public WindowEventListener(Gui p){
        super();
        this.parent = p;
        musikList = parent.getMusikMap().getMusikList();
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
       
        System.out.println("Fenster geöffnet");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
        String[] options = {"Speichern", "Speichern als", "Beenden"};      
        int x = JOptionPane.showOptionDialog(null, "Möchten sie ihren Frortschritt speichern?",
                "Click a button", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        
        if (x==1){
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int returnval = chooser.showSaveDialog(parent);
            if (returnval == chooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                if (!path.endsWith(".csv")) {
                    path = path + ".csv";
                }
                MusikCsvListDAO mmd = new MusikCsvListDAO(path, true);
                try {
                    mmd.write(parent.getMusikMap().getMusikList());
                    mmd.close();
                } 
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        
        MusikListDAO mld = new MusikListDAO("setup.data", true);
        try {
            mld.write(musikList);
            mld.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
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
   
    
}
