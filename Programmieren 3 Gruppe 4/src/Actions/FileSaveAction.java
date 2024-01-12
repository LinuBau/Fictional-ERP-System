
package Actions;

package Actions;

import App_GUI.Gui;
import SaveData_ReadData.MusikCsvListDAO;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

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
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
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
        }
    }
}
    

