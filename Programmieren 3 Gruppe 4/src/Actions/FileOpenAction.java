package Actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import App_GUI.Gui;
import GeschaftsObejekt.MusikList;
import SaveData_ReadData.MusikCsvListDAO;

public class FileOpenAction extends AbstractAction {
    private Gui parent;
    public FileOpenAction(Gui parent){
        super();
        this.parent = parent;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        MusikList musikList = new MusikList();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CVS Datein", "cvs");
        chooser.addChoosableFileFilter(filter);
        int returnval = chooser.showOpenDialog(parent);
        if (returnval == chooser.APPROVE_OPTION) {
            MusikCsvListDAO mmd = new MusikCsvListDAO(chooser.getSelectedFile().getAbsolutePath(), false);
            try {
                mmd.read(musikList);
                parent.getMusikMap().updateMedienListe(musikList);
                parent.updateTableWithMusikListe(parent.getMusikMap().getMusikList());
                mmd.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

}
