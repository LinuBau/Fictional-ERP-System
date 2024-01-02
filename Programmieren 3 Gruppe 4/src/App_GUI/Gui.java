package App_GUI;

import ActionListener.WindowEventListener;
import ActionListener.shoppingCartListner;
import GeschaftsObejekt.MusikList;
import ActionListener.BearbeitenListener;
import ActionListener.FilterListener;
import MenuBar.MenuBar;
import Modele.MusikTableModel;
import SaveData_ReadData.MusikListDAO;
import ToolBar.toolBar;
import Traversierung.MusikMap;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Gui extends JFrame {

    private JTable AtributTabelle;
    private MusikTableModel tableModel;
    private MusikMap musikmap;
    private MusikList musikList;
    private BearbeitenListener bearbeitenListener;
    private shoppingCartListner shoppingCartListner;
    FilterListener filterListener;
    public boolean starten;

    public Gui(boolean starten) {
        this.starten = starten;
        filterListener = new FilterListener(this);
        JPanel eingabePanel = new JPanel(new FlowLayout());
        shoppingCartListner = new shoppingCartListner();
        eingabePanel.add(filterListener.getFilterPanel());
        bearbeitenListener = new BearbeitenListener(this);

        eingabePanel.add(bearbeitenListener.getBearbeitenPanel());

        // Importing Data
        musikList = new MusikList();
        MusikListDAO mld = new MusikListDAO("setup.data", false);
        try {
            mld.read(musikList);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // Initializing the MusikMap
        musikmap = new MusikMap(musikList);

        // Initializing sortetd JTable
        MusikList sotierteList = new MusikList();
        sotierteList.addAll(musikmap.sortMusikListBySongName(musikList));
        tableModel = new MusikTableModel(sotierteList);
        AtributTabelle = new JTable(tableModel);
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(eingabePanel, BorderLayout.CENTER);

        northPanel.add(new toolBar(this), BorderLayout.NORTH);

        // Setting up the layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(AtributTabelle), BorderLayout.CENTER);
        getContentPane().add(northPanel, BorderLayout.NORTH);
        setLocationRelativeTo(null);

        // Add Mouse Pressed Event
        AtributTabelle.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                Point point = mouseEvent.getPoint();
                int row = AtributTabelle.rowAtPoint(point);
                bearbeitenListener.fillTextBox(tableModel.getMusikList().get(row));
                bearbeitenListener.setMusik(tableModel.getMusikList().get(row));
            }
        });

        // Create MenuBar

        setJMenuBar(new MenuBar(this));

        // add WindowEventListner
        addWindowListener(new WindowEventListener(this));

    }

    public void updateTableWithMusikListe(MusikList musiklist) {
        this.tableModel.setMusikList(musiklist);
        this.tableModel.fireTableDataChanged();
    }

    public MusikList getMusikList() {
        return musikList;
    }

    public MusikMap getMusikMap() {
        return this.musikmap;
    }  

    public MusikTableModel getTableModel() {
        return this.tableModel;
    }

    public shoppingCartListner getShoppingCartListner() {
        return this.shoppingCartListner;
    }

    public static void main(String[] args) {
        
         loginGUi loginWindow = new loginGUi();
         loginWindow.setTitle("Login");
         loginWindow.setSize(500, 500);
         loginWindow.setVisible(true);
    }
}
