/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuBar;

import javax.swing.JMenuItem;

import Actions.FileSaveAction;
import App_GUI.Gui;

/**
 *
 * @author ninfr
 */
public class FileSafeItem extends JMenuItem {
    FileSafeItem(Gui parent) {
        super("Speichern");
        this.addActionListener(new FileSaveAction(parent));
    }
}