package ActionListener;

import Actions.FileSaveAsAction;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import App_GUI.Gui;
import GeschaftsObejekt.MusikList;
import GeschaftsObejekt.ProfilList;
import SaveData_ReadData.ChangeLogCsvDAO;
import SaveData_ReadData.MusikCsvListDAO;
import SaveData_ReadData.MusikListDAO;
import SaveData_ReadData.ProfilListDAO;
import GeschaftsObejekt.ChangeLogEntry;
import Traversierung.MusikMap;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WindowEventListener implements WindowListener {

    private Gui parent;
    private MusikMap musikMap;
    private ChangeLogCsvDAO changeLogCsvDOA;

    public WindowEventListener(Gui p) {
        super();
        this.parent = p;
        this.changeLogCsvDOA = new ChangeLogCsvDAO();
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
            List<ChangeLogEntry> changeLogs = parent.getMusikMap().getChangeLogs();
            changeLogCsvDOA.write(changeLogs);
            System.out.println(parent.getL10NText("save"));
            System.exit(0);

        }

        if (x == 1) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            changeLogCsvDOA.write(parent.getMusikMap().getChangeLogs());

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
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

}
