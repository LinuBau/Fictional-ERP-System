
package MenuBar;

<<<<<<< Updated upstream:Programmieren 3 Gruppe 4/src/MenuBar/FileMenu.java


=======
import Actions.FileSaveAsAction;
>>>>>>> Stashed changes:src/MenuBar/FileMenu.java
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

import App_GUI.Gui;


<<<<<<< Updated upstream:Programmieren 3 Gruppe 4/src/MenuBar/FileMenu.java

public class FileMenu extends JMenu{
    Action FileOpenAction,FileSaveAction;
=======
    Action FileOpenAction, FileSaveAction, FileSaveAsAction;
>>>>>>> Stashed changes:src/MenuBar/FileMenu.java
    Gui p;
    FileMenu(Gui parent){
        super(parent.getL10NText("file"));
        p = parent;
        createActions();
        this.add(FileSaveAsAction);
        this.addSeparator();
        this.add(FileSaveAction);
        this.addSeparator();
        this.add(FileOpenAction);
        
    }
    
    private void createActions(){
        FileOpenAction = new Actions.FileOpenAction(p,p.getL10NText("open"), createIcon("/icons/53.gif"), p.getL10NText("opfile"));
    }

<<<<<<< Updated upstream:Programmieren 3 Gruppe 4/src/MenuBar/FileMenu.java
    private ImageIcon createIcon(String filepath){
=======
    private void createActions() {
        FileSaveAsAction = new Actions.FileOpenAction(p, p.getL10NText("saveas"), createIcon("/icons/22.gif"), p.getL10NText("sasfile"));
        FileSaveAction = new Actions.FileOpenAction(p, p.getL10NText("save"), createIcon("/icons/86.gif"), p.getL10NText("safile"));
        FileOpenAction = new Actions.FileOpenAction(p, p.getL10NText("open"), createIcon("/icons/53.gif"), p.getL10NText("opfile"));
    }

    private ImageIcon createIcon(String filepath) {
>>>>>>> Stashed changes:src/MenuBar/FileMenu.java
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
