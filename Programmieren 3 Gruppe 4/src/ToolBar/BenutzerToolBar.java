package ToolBar;

import javax.swing.JButton;
import javax.swing.JToolBar;

import App_GUI.Gui;

public class BenutzerToolBar extends JToolBar {

    JButton shoppingKartButton;
    Gui parent;

    public BenutzerToolBar(Gui p) {
        parent = p;
        setSize(200, 10);
        shoppingKartButton = new JButton(p.getL10NText("wk"));
        shoppingKartButton.addActionListener(parent.getShoppingCartListner());
        add(shoppingKartButton);

    }
}
