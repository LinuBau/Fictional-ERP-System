package App_GUI;

import ActionListener.WindowEventListener;
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
    FilterListener filterListener;

public Gui(boolean starten) {
        if (starten) {
            // Initialisierung der MusikList und MusikMap
            musikList = new MusikList();
            MusikListDAO mld = new MusikListDAO("setup.data", false);
            try {
                mld.read(musikList);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            musikmap = new MusikMap(musikList);

            // Initialisierung der Listener
            bearbeitenListener = new BearbeitenListener(this);
            filterListener = new FilterListener(this, musikmap);

            // Erstellen des Eingabe-Panels
            JPanel eingabePanel = new JPanel(new FlowLayout());
            eingabePanel.add(filterListener.getFilterPanel());
            eingabePanel.add(bearbeitenListener.getBearbeitenPanel());

            // Initialisierung und Einrichtung der Tabelle
            MusikList sortierteList = new MusikList();
            sortierteList.addAll(musikmap.sortMusikListBySongName(musikList));
            tableModel = new MusikTableModel(sortierteList);
            AtributTabelle = new JTable(tableModel);
            JPanel southPanel = new JPanel(new BorderLayout());
            southPanel.add(eingabePanel, BorderLayout.CENTER);
            southPanel.add(new toolBar(this), BorderLayout.NORTH);

            // Layout einrichten
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(new JScrollPane(AtributTabelle), BorderLayout.CENTER);
            getContentPane().add(southPanel, BorderLayout.NORTH);
            setLocationRelativeTo(null);

            // Maus-Event für die Tabelle hinzufügen
            AtributTabelle.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent mouseEvent) {
                    Point point = mouseEvent.getPoint();
                    int row = AtributTabelle.rowAtPoint(point);
                    bearbeitenListener.fillTextBox(tableModel.getMusikList().get(row));
                    bearbeitenListener.setMusik(tableModel.getMusikList().get(row));
                }
            });

            // Menüleiste und WindowListener hinzufügen
            setJMenuBar(new MenuBar(this));
            addWindowListener(new WindowEventListener(this));
        }
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

    public static void main(String[] args) {

        Gui mainWindow = new Gui(true);
        mainWindow.setTitle("Musik Katalog");
        mainWindow.setSize(1200, 750);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);

    }
}
