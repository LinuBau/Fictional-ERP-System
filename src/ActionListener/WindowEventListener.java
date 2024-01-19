package ActionListener;

import Actions.FileSaveAsAction;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import App_GUI.Gui;
import GeschaftsObejekt.MusikList;
import GeschaftsObejekt.profilList;
import SaveData_ReadData.MusikCsvListDAO;
import SaveData_ReadData.MusikListDAO;
import SaveData_ReadData.ProfilListDOA;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WindowEventListener implements WindowListener {

    private Gui parent;

    public WindowEventListener(Gui p) {
        super();
        this.parent = p;
    }

    @Override
    public void windowOpened(WindowEvent e) {

        System.out.println("Fenster geöffnet");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String[] options = {parent.getL10NText("save"), parent.getL10NText("saveas"), parent.getL10NText("end")};

        int x = JOptionPane.showOptionDialog(
                null,
                "" + parent.getL10NText("wantToSave"),
                "Click a button",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
        
         if (x == 0) {
            System.out.println(parent.getClass().getName());
            WindowEventListener.saveListe(parent.getMusikMap().getMusikList(), parent.getProfilList());
            System.out.println(parent.getL10NText("save"));
            System.out.println("Fenster schließen");
            System.exit(0);
        }

        if (x == 1) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            WindowEventListener.saveProfilListe(parent.getProfilList());

            int returnval = chooser.showSaveDialog(parent);

            if (returnval == JFileChooser.APPROVE_OPTION) {

                String path = chooser.getSelectedFile().getAbsolutePath();
                if (!path.endsWith(".csv")) {
                    path = path + ".csv";
                }
                MusikCsvListDAO mmd = new MusikCsvListDAO(path, true);
                try {
                    mmd.write(parent.getMusikMap().getMusikList());
                    mmd.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.out.println(parent.getL10NText("save"));
                System.out.println("Fenster schließen");
                System.exit(0);
            } else if (returnval == JFileChooser.CANCEL_OPTION || returnval == JFileChooser.ERROR_OPTION) {
                parent.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                return;
            }
        }
        if (x == 2) {
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            parent.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            return;
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
        System.out.println("Fenster verkleinert");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("Fenster vergrößert");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("Fenster öffnen");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("Fenster beenden");
    }

    public static void saveListe(MusikList musikList, profilList profilList) {
        MusikCsvListDAO mld = new MusikCsvListDAO("Songs.csv", true);
        ProfilListDOA pld = new ProfilListDOA("logindata.data", true);
        try {
            mld.write(musikList);
            
            mld.close();
            
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
    public static void saveProfilListe(profilList profilList){
        ProfilListDOA pld = new ProfilListDOA("logindata.data", true);
        try {
            pld.write(profilList);
            pld.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
