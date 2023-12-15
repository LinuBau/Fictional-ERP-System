package App_GUI;

import ActionListener.WindowEventListener;
import GeschaftsObejekt.MusikList;
import ActionListener.BearbeitenListener;
import ActionListener.FilterListener;
import ActionListener.HinzufuegenListener;
import ActionListener.ReinhörenListener;
import MenuBar.MenuBar;
import Modele.MusikTableModel;
import SaveData_ReadData.MusikCsvListDAO;
import SaveData_ReadData.MusikListDAO;
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
    private JButton reinhörenButton;
    private JButton filternButton;
    private JTable AtributTabelle;
    private MusikTableModel tableModel;
    private MusikMap musikmap;
    private MusikList musikList;
    private BearbeitenListener bearbeitenListener;
    private ReinhörenListener reinhörenListener;

    public Gui(boolean starten) {
        if (starten) {
            hinzufuegenButton = new JButton("Hinzufügen");
            reinhörenButton = new JButton("Reinhören");
            filternButton = new JButton("Filtern");
            bearbeitenListener = new BearbeitenListener(this);
            reinhörenListener = new ReinhörenListener();

            JPanel eingabePanel = new JPanel(new FlowLayout());
            eingabePanel.add(hinzufuegenButton);
            eingabePanel.add(reinhörenButton);
            eingabePanel.add(filternButton);
            eingabePanel.add(bearbeitenListener.setJPanel());
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
                    bearbeitenListener.fillTextBox(tableModel.getMusikList().get(row));
                    bearbeitenListener.setMusik(tableModel.getMusikList().get(row));
                    reinhörenListener.setMedium(tableModel.getMusikList().get(row));
                }
            });

            // Setup ActionListner
            FilterListener filterListener = new FilterListener(musikmap, this);
            filternButton.addActionListener(e -> filterListener.setVisible(true));
            hinzufuegenButton.addActionListener(new HinzufuegenListener(this));
            reinhörenButton.addActionListener(reinhörenListener);

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
