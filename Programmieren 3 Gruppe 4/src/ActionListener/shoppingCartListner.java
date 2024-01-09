package ActionListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import App_GUI.Gui;
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
    private Musik musik;
    private Gui parent;

    public shoppingCartListner(Gui p) {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        musikList = new MusikList();
        parent = p;
        tableModel = new MusikTableModel(musikList);
        selectTabele = new JTable(tableModel);
        orderButton = new JButton("Bestellen");
        loeschenButton = new JButton("Löschen");
        buttonPanel.add(orderButton);
        buttonPanel.add(loeschenButton);
        fillMusikListFromProfil();

        loeschenButton.addActionListener(this);

        selectTabele.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                Point point = mouseEvent.getPoint();
                index = selectTabele.rowAtPoint(point);
            }
        });
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

    public void setMusik(Musik m) {
        this.musik = m;
    }

    public Musik getMusik() {
        return this.musik;
    }

    public void add() {
        musikList.add(musik);
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
            int indexOfLogin = parent.getProfilList().getIndexofLogin();
            parent.getProfilList().get(indexOfLogin).removeArray(index);
            updateTableWithMusikListe(musikList);
            index = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Objekt muss ausgewählt sein", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillMusikListFromProfil() {
        int indexOfLogin = parent.getProfilList().getIndexofLogin();
        ArrayList<Integer> IdList =  parent.getProfilList().get(indexOfLogin).getMusikId();
        int size = IdList.size();
        int indexInParentMusikList =-1;
        for(int i=0;i<size;i++){
            if (!(parent.getMusikList().unique(IdList.get(i)))) {
                indexInParentMusikList = parent.getMusikList().getIndex(IdList.get(i));
                musikList.add(parent.getMusikList().get(indexInParentMusikList));
                updateTableWithMusikListe(musikList);
            }else{
                JOptionPane.showMessageDialog(this, "Musik mit der Id "+IdList.get(i)+" nicht vorhanden.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
