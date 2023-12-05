/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuBar;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
     
    public MenuBar(){
       super();
       this.add(new FileMenu());
       this.add(new SettingsMenu());
    }
}
