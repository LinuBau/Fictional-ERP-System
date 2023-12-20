package Actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import App_GUI.Gui;
import SaveData_ReadData.MusikCsvListDAO;



public class FileSaveAction extends AbstractAction {
    private Gui parent;

    public FileSaveAction(Gui parent){
        super();
        this.parent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
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
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
}
