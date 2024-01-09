package App_GUI;

import ActionListener.WindowEventListener;
import ActionListener.shoppingCartListner;
import GeschaftsObejekt.MusikList;
import GeschaftsObejekt.profilList;
import GeschaftsObejekt.MenuBar.benutzerMenuBar;
import GeschaftsObejekt.MenuBar.mitarbeiterMenuBar;
import ActionListener.BearbeitenListener;
import ActionListener.FilterListener;
import Modele.MusikTableModel;
import SaveData_ReadData.MusikListDAO;
import ToolBar.benutzerToolBar;
import ToolBar.mitarbeiterToolBar;
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
    private profilList profilList;
    private BearbeitenListener bearbeitenListener;
    private shoppingCartListner shoppingCartListner;
    FilterListener filterListener;
    public boolean starten;

    public Gui(boolean starten, profilList pl) {
        if(starten){
            initialiseMitarbeiterFarme(pl);
        }else{
            initialiseUsserFrame(pl);
        }
    }

    public void updateTableWithMusikListe(MusikList musiklist) {
        this.tableModel.setMusikList(musiklist);
        this.tableModel.fireTableDataChanged();
    }

    public MusikList getMusikList() {
        return this.musikList;
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

    public profilList getProfilList() {
        return this.profilList;
    }

    private void initialiseUsserFrame( profilList pl) {
        this.profilList = pl;

        // Importing Data
        musikList = new MusikList();
        MusikListDAO mld = new MusikListDAO("setup.data", false);
        try {
            mld.read(musikList);
            mld.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // Initializing the ActionListner
        filterListener = new FilterListener(this);
        bearbeitenListener = new BearbeitenListener(this);
        shoppingCartListner = new shoppingCartListner(this);

        // add FilterPanel and EditPanel
        JPanel eingabePanel = new JPanel(new FlowLayout());
        eingabePanel.add(filterListener.getFilterPanel());
        eingabePanel.add(bearbeitenListener.getUsserBearbeitenPanel());

        // Initializing the MusikMap
        musikmap = new MusikMap(musikList);

        // Initializing sortetd JTable
        MusikList sotierteList = new MusikList();
        sotierteList.addAll(musikmap.sortMusikListBySongName(musikList));
        tableModel = new MusikTableModel(sotierteList);
        AtributTabelle = new JTable(tableModel);
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(eingabePanel, BorderLayout.CENTER);

        // Remove Collum that user are not allowod to see
        int[] columnsToHide = { 0, 4, 5, 9, 10, 11 };
        int removeCount = 0;
        for (int i : columnsToHide) {
            AtributTabelle.removeColumn(AtributTabelle.getColumnModel().getColumn(i - removeCount));
            removeCount++;
        }

        northPanel.add(new benutzerToolBar(this), BorderLayout.NORTH);

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
                bearbeitenListener.benutzerFillTextBox(tableModel.getMusikList().get(row));
                bearbeitenListener.setMusik(tableModel.getMusikList().get(row));
                shoppingCartListner.setMusik(tableModel.getMusikList().get(row));
            }
        });

        // Create MenuBar
        setJMenuBar(new benutzerMenuBar());

        // add WindowEventListner
        addWindowListener(new WindowEventListener(this));

    }

    private void initialiseMitarbeiterFarme(profilList pl) {
        this.profilList = pl;

        // Importing Data
        musikList = new MusikList();
        MusikListDAO mld = new MusikListDAO("setup.data", false);
        try {
            mld.read(musikList);
            mld.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // Initializing the ActionListner
        filterListener = new FilterListener(this);
        bearbeitenListener = new BearbeitenListener(this);

        // add FilterPanel and EditPanel#
        JPanel eingabePanel = new JPanel(new FlowLayout());
        eingabePanel.add(filterListener.getFilterPanel());
        eingabePanel.add(bearbeitenListener.getMitarbeiterBearbeitenPanel());

        // Initializing the MusikMap
        musikmap = new MusikMap(musikList);

        // Initializing sortetd JTable
        MusikList sotierteList = new MusikList();
        sotierteList.addAll(musikmap.sortMusikListBySongName(musikList));
        tableModel = new MusikTableModel(sotierteList);
        AtributTabelle = new JTable(tableModel);
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(eingabePanel, BorderLayout.CENTER);


        northPanel.add(new mitarbeiterToolBar(this), BorderLayout.NORTH);

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
                bearbeitenListener.mitarbeiterFillTextBox(tableModel.getMusikList().get(row));
                bearbeitenListener.setMusik(tableModel.getMusikList().get(row));
            }
        });

        // Create MenuBar
        setJMenuBar(new mitarbeiterMenuBar(this));

        // add WindowEventListner
        addWindowListener(new WindowEventListener(this));
    }

    public static void main(String[] args) {

        loginGUi loginWindow = new loginGUi();
        loginWindow.setTitle("Login");
        loginWindow.setSize(500, 500);
        loginWindow.setVisible(true);
        // loginWindow.w();

    }
}
