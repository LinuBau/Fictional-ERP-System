
package MenuBar;

import javax.swing.JMenuItem;

import Actions.FileSaveAction;
import App_GUI.Gui;

public class FileSafeItem extends JMenuItem {
    FileSafeItem(Gui parent) {
        super("Speichern");
        this.addActionListener(new FileSaveAction(parent));
    }
}