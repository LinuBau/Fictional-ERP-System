package ToolBar;

import javax.swing.JButton;
import javax.swing.JToolBar;

import ActionListener.HinzufuegenListener;
import App_GUI.Gui;

public class toolBar extends JToolBar {
    JButton hinzufügeButton;
    Gui parent;

    public toolBar(Gui p) {
        parent = p;
        setSize(200, 10);
        if (parent.starten) {
            hinzufügeButton = new JButton("Hinzufügen");
            hinzufügeButton.addActionListener(new HinzufuegenListener(p));
        }else{
             hinzufügeButton = new JButton("Warenkorb");
             hinzufügeButton.addActionListener(parent.getShoppingCartListner());
        }
        add(hinzufügeButton);

    }
}
