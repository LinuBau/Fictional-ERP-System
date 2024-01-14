package ToolBar;

import javax.swing.JButton;
import javax.swing.JToolBar;

import App_GUI.Gui;

public class benutzerToolBar extends JToolBar {
    JButton shoppingKartButton;
    Gui parent;

    public benutzerToolBar(Gui p) {
        parent = p;
        setSize(200, 10);
        shoppingKartButton = new JButton(p.getL10NText("wk"));
        shoppingKartButton.addActionListener(parent.getShoppingCartListner());
        add(shoppingKartButton);

    }
}
