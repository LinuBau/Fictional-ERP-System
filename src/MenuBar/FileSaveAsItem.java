/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuBar;

import javax.swing.JMenuItem;

import Actions.FileSaveAsAction;
import App_GUI.Gui;

/**
 *
 * @author ninfr
 */
public class FileSaveAsItem extends JMenuItem {

    FileSaveAsItem(Gui parent) {
        super(parent.getL10NText("saveas"));
        this.addActionListener(new FileSaveAsAction(parent));
    }
}
