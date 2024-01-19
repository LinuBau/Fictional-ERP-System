package ToolBar;

import javax.swing.JButton;
import javax.swing.JToolBar;

import ActionListener.HinzufuegenListener;
import App_GUI.Gui;

public class mitarbeiterToolBar extends JToolBar {
    JButton hinzufügeButton;
    Gui parent;

    public mitarbeiterToolBar(Gui p) {
        parent = p;
        setSize(200, 10);
        hinzufügeButton = new JButton(p.getL10NText("add"));
        hinzufügeButton.addActionListener(new HinzufuegenListener(p));
        add(hinzufügeButton);

    }
}
