/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuBar;

import javax.swing.JMenuBar;

import App_GUI.Gui;
import javax.accessibility.AccessibleContext;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;

public class MenuBar extends JMenuBar {
     
    public MenuBar(Gui parent){
       super();
       this.add(new FileMenu(parent));
       this.add(new SettingsMenu());
    }

    public MenuBar() {
        
    }

  

  
    
}
