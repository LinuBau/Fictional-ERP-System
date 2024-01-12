/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuBar;

import javax.swing.JMenuBar;

import App_GUI.Gui;

public class MenuBar extends JMenuBar {
     
    public MenuBar(Gui parent){
       super();
       this.add(new FileMenu(parent));
       this.add(new SettingsMenu());
    }
}
