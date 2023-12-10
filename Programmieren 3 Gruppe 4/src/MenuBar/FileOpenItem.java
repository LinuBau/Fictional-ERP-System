
package MenuBar;


import javax.swing.JMenuItem;

import Actions.FileSaveAction;
import App_GUI.Gui;

public class FileOpenItem extends JMenuItem {

    FileOpenItem(Gui parent) {
        super("Öffnen");
        this.addActionListener(new FileSaveAction(parent));
    }
}
