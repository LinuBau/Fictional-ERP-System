
package MenuBar;

import javax.swing.JMenu;

import App_GUI.Gui;



public class FileMenu extends JMenu{
    
    FileMenu(Gui parent){
    
        super("File");
        this.add(new FileSafeItem(parent));
        this.addSeparator();
        this.add(new FileOpenItem(parent));
        
    }
}
