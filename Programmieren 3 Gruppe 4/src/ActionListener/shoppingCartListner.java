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

    public shoppingCartListner(){
        JPanel buttonPanel = new JPanel(new GridLayout(2,1));
        musikList = new MusikList();
        tableModel = new MusikTableModel(musikList);
        selectTabele = new JTable(tableModel);
        orderButton = new JButton("Bestellen");
        loeschenButton = new JButton("Löschen");
        buttonPanel.add(orderButton);
        buttonPanel.add(loeschenButton);
        selectTabele.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent){
                Point point = mouseEvent.getPoint();
                index = selectTabele.rowAtPoint(point);
            }
        });
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(buttonPanel,BorderLayout.NORTH);
        this.getContentPane().add(new JScrollPane(selectTabele),BorderLayout.CENTER);
        this.setSize(500, 500);
        this.setVisible(false);
    }
    public void add(Musik m){
        musikList.add(m);
        tableModel.setMusikList(musikList);
        this.tableModel.fireTableDataChanged();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(true);
    }
    
}
