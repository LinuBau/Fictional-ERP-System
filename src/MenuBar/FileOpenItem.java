package MenuBar;

import javax.swing.Action;
import javax.swing.JMenuItem;

public class FileOpenItem extends JMenuItem {

    FileOpenItem(Action FileOpenAction) {
        super();
        this.addActionListener(FileOpenAction);
    }
}
