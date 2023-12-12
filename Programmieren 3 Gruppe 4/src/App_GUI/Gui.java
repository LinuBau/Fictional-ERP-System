package App_GUI;

import ActionListener.WindowEventListener;
import GeschaftsObejekt.MusikList;
import ActionListener.BearbeitenListener;
import ActionListener.FilterListener;
import ActionListener.HinzufuegenListener;
import MenuBar.MenuBar;
import Modele.MusikTableModel;
import SaveData_ReadData.MusikCsvListDAO;
import Traversierung.MusikMap;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Gui extends JFrame {

    private JButton hinzufuegenButton;
    private JButton loeschenButton;
    private JButton filternButton;
    private JTable AtributTabelle;
    private MusikTableModel tableModel;
    private MusikMap musikmap;
    private MusikList musikList;
    private BearbeitenListener bearbeitenListener;

    public Gui(boolean starten) {
        if (starten) {
            hinzufuegenButton = new JButton("Hinzufügen");
            loeschenButton = new JButton("Löschen");
            filternButton = new JButton("Filtern");
            bearbeitenListener = new BearbeitenListener(this);

            JPanel eingabePanel = new JPanel(new FlowLayout());
            eingabePanel.add(hinzufuegenButton);
            eingabePanel.add(loeschenButton);
            eingabePanel.add(filternButton);
            eingabePanel.add(bearbeitenListener.setJPanel());
            // Importing Data
            musikList = new MusikList();
            MusikCsvListDAO mld = new MusikCsvListDAO("Song.csv", false);
            try {
                mld.read(musikList);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // Initializing the MusikMap
            musikmap = new MusikMap(musikList);

            // Initializing the JTable
            tableModel = new MusikTableModel(musikList);
            AtributTabelle = new JTable(tableModel);

            // Setting up the layout
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(new JScrollPane(AtributTabelle), BorderLayout.CENTER);
            getContentPane().add(eingabePanel, BorderLayout.NORTH);
            setLocationRelativeTo(null);
            // Add Mouse Pressed Event
            AtributTabelle.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent mouseEvent) {
                    Point point = mouseEvent.getPoint();
                    int row = AtributTabelle.rowAtPoint(point);
                    bearbeitenListener.fillTextBox(tableModel.geMusikList().get(row));
                    bearbeitenListener.setMusik(tableModel.geMusikList().get(row));
                    update(getGraphics());
                }
            });

            // Setup FilterListener
            FilterListener filterListener = new FilterListener(musikmap, this);
            filternButton.addActionListener(e -> filterListener.setVisible(true));
            hinzufuegenButton.addActionListener(new HinzufuegenListener("c", this));

            // Create MenuBar
            setJMenuBar(new MenuBar(this));
            // add WindowEventListner
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
        mainWindow.setSize(1000, 500);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }
}
