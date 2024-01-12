
package MenuBar;



import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

import App_GUI.Gui;



public class FileMenu extends JMenu{
    Action FileOpenAction,FileSaveAction;
    Gui p;
    FileMenu(Gui parent){
        super("File");
        p = parent;
        createActions();
        this.add(new FileSaveAsItem(parent));
        this.addSeparator();
        this.add(new FileSaveItem(parent));
        this.addSeparator();
        this.add(FileOpenAction);
        
    }
    private void createActions(){
        FileOpenAction = new Actions.FileOpenAction(p, "Öffen", createIcon("/icons/53.gif"), "Öffent eine Datei");
    }

    private ImageIcon createIcon(String filepath){
        java.net.URL imgUrl = getClass().getResource(filepath);

        if (imgUrl == null) {
            System.err.println("Resource not found: " + imgUrl);
            return null;
        }else{
            //return new ImageIcon(new ImageIcon(imgUrl).getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
            return new ImageIcon(imgUrl);
        }
    }

}
