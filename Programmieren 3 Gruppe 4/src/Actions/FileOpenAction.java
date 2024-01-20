package Actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import App_GUI.Gui;
import GeschaftsObejekt.MusikList;
import SaveData_ReadData.MusikCsvListDAO;

public class FileOpenAction extends AbstractAction {

    private Gui parent;

    public FileOpenAction(Gui parent, String text, ImageIcon icon, String desc) {
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        MusikList musikList = new MusikList();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Datein", "csv");
        chooser.addChoosableFileFilter(filter);
        int returnval = chooser.showOpenDialog(null);
        if (returnval == JFileChooser.APPROVE_OPTION) {
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
