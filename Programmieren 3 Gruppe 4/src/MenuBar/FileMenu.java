
package MenuBar;

import javax.swing.JMenu;

import App_GUI.Gui;



public class FileMenu extends JMenu{
    
    FileMenu(Gui parent){
    
        super("File");
        this.add(new FileOpenItem(parent));
        this.addSeparator();
        this.add(new FileSaveAsItem(parent));
        this.addSeparator();
        this.add(new FileSaveItem(parent));
        this.addSeparator();
    }
    
}
