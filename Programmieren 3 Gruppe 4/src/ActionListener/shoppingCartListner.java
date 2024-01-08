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

import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;
import Modele.MusikTableModel;

public class shoppingCartListner extends JDialog implements ActionListener {
    private JTable selectTabele;
    private MusikTableModel tableModel;
    private MusikList musikList;
    private ArrayList<Integer> pallteStückZahlList;
    private ArrayList<Integer> cdStückZahlList;
    private JButton loeschenButton;
    private JButton orderButton;
    private int index;
    private Musik musik;

    public shoppingCartListner() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        musikList = new MusikList();
        tableModel = new MusikTableModel(musikList);
        selectTabele = new JTable(tableModel);
        pallteStückZahlList = new ArrayList<Integer>();
        cdStückZahlList =  new ArrayList<Integer>();
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
    public void setMusik(Musik m){
        this.musik = m;
    }
    public Musik getMusik(){
        return this.musik;
    }

    public void add(int ps,int cs) {
        musikList.add(musik);
        pallteStückZahlList.add(ps);
        cdStückZahlList.add(cs);
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
            pallteStückZahlList.remove(index);
            cdStückZahlList.remove(index);
            updateTableWithMusikListe(musikList);
            index = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Objekt muss ausgewählt sein", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
