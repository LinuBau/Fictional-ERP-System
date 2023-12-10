/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
