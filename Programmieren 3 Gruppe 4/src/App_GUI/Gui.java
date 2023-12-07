package App_GUI;

import ActionListener.WindowEventListener;
import ActionListener.FilterListener;
import ActionListener.HinzufuegenListener;
import GeschaftsObejekt.MusikList;
import MenuBar.MenuBar;
import Modele.MusikTableModel;
import SaveData_ReadData.MusikCsvListDAO;
import Traversierung.MusikMap;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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

    public Gui(boolean starten) {
        if (starten) {
            hinzufuegenButton = new JButton("Hinzufügen");
            loeschenButton = new JButton("Löschen");
            filternButton = new JButton("Filtern");
            
            JPanel eingabePanel = new JPanel(new GridLayout(3, 1));
            eingabePanel.add(hinzufuegenButton);
            eingabePanel.add(loeschenButton);
            eingabePanel.add(filternButton);

            // Importing Data
            musikList = new MusikList();
            MusikCsvListDAO mld = new MusikCsvListDAO("C:/Users/Mo/Documents/GitHub/P3-G4/Song.csv", false);
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

            // Setup FilterListener
            FilterListener filterListener = new FilterListener(musikmap, this);
            filternButton.addActionListener(e -> filterListener.setVisible(true));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
    }

    public void updateTableWithFilterResults(MusikList gefilterteErgebnisse) {
        this.tableModel.setMusikList(gefilterteErgebnisse);
        this.tableModel.fireTableDataChanged();
    }
    
     public MusikList getMusikList() {
        return musikList;
    }

    public static void main(String[] args) {
        Gui mainWindow = new Gui(true);
        mainWindow.addWindowListener(new WindowEventListener());
        mainWindow.setTitle("Musik Katalog");
        mainWindow.setSize(1000, 500);
        mainWindow.setJMenuBar(new MenuBar());
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }
}
