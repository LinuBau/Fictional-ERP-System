
package MenuBar;

import javax.swing.JMenuItem;

import Actions.FileSaveAction;
import App_GUI.Gui;


public class FileSaveItem extends JMenuItem {
    FileSaveItem(Gui parent) {
        super(parent.getL10NText("save"));
        this.addActionListener(new FileSaveAction(parent));
    }
}