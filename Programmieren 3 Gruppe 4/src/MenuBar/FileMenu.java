/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuBar;

import javax.swing.JMenu;



public class FileMenu extends JMenu{
    
    FileMenu(){
    
        super("File");
        this.add(new FileSafeItem());
        this.addSeparator();
        this.add(new FileOpenItem());
        
    }
}
