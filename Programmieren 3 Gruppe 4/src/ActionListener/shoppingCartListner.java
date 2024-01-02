package ActionListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;
import Modele.MusikTableModel;

public class shoppingCartListner extends JDialog implements ActionListener {
    private JTable selectTabele;
    private MusikTableModel tableModel;
    private MusikList musikList;
    private JButton loeschenButton;
    private JButton orderButton;
    private int index;

    public shoppingCartListner() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        musikList = new MusikList();
        tableModel = new MusikTableModel(musikList);
        selectTabele = new JTable(tableModel);
        orderButton = new JButton("Bestellen");
        loeschenButton = new JButton("Löschen");
        buttonPanel.add(orderButton);
        buttonPanel.add(loeschenButton);

        loeschenButton.addActionListener(this);

        selectTabele.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                Point point = mouseEvent.getPoint();
                index = selectTabele.rowAtPoint(point);
            }
        });

        selectTabele.removeColumnSelectionInterval(4,12);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        this.getContentPane().add(new JScrollPane(selectTabele), BorderLayout.CENTER);
        this.setSize(500, 500);


        this.setVisible(false);
    }

    public void updateTableWithMusikListe(MusikList musiklist) {
        this.tableModel.setMusikList(musiklist);
        this.tableModel.fireTableDataChanged();
    }

    public void add(Musik m) {
        musikList.add(m);
        updateTableWithMusikListe(musikList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(true);
        if (e.getSource().equals(loeschenButton)) {
            removeFromMusikList();
        }
    }

    private void removeFromMusikList() {
        if (!(index == -1)) {
            musikList.remove(index);
            updateTableWithMusikListe(musikList);
            index = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Objekt muss ausgewählt sein", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
