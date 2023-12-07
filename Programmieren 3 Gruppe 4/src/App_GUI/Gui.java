
package App_GUI;

import ActionListener.WindowEventListener;
import GeschaftsObejekt.MusikList;
import MenuBar.MenuBar;
import Modele.MusikTableModel;
import SaveData_ReadData.MusikCsvListDAO;
import SaveData_ReadData.MusikListDAO;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import ActionListener.FilterListener;
import ActionListener.HinzufuegenListener;
import javax.swing.JTable;


public class Gui extends JFrame {

    private JButton hinzufuegenButton;
    private JButton loeschenButton;
    private JButton filternButton;
    private JTable AtributTabelle;
    private MusikList MusikList = new MusikList();

    public MusikList getMusikList() {
        return this.MusikList;
    }

    public void setMusikList() {

    }

    public Gui() {

    }

    public Gui(boolean starten) {
        if(starten){
        hinzufuegenButton = new JButton("Hinzufügen");
        loeschenButton = new JButton("Löschen");
        filternButton = new JButton("Filtern");
        JPanel eingabePanel = new JPanel(new GridLayout(3, 1));
        eingabePanel.add(hinzufuegenButton);
        eingabePanel.add(loeschenButton);
        eingabePanel.add(filternButton);
        

        hinzufuegenButton.addActionListener(new HinzufuegenListener());
        filternButton.addActionListener(new FilterListener());



        // Importing Data 
        MusikCsvListDAO mld = new MusikCsvListDAO("Song.csv", false);
        try {
            mld.read(getMusikList());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

      

        // Initializing the JTable
        MusikTableModel tableModel = new MusikTableModel(MusikList);
        AtributTabelle = new JTable(tableModel);
        //Puting Content into the table 
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(AtributTabelle), BorderLayout.CENTER);
        getContentPane().add(eingabePanel, BorderLayout.NORTH);
        setLocationRelativeTo(null);
        
    }}

    public static void main(String[] args) {

        Gui mainWindow = new Gui(true);

        mainWindow.addWindowListener(new WindowEventListener());
        mainWindow.setTitle("Musik Katalog");
        mainWindow.setSize(1000, 500);

        mainWindow.setJMenuBar(new MenuBar());

        mainWindow.setVisible(true);
    
    }
}
