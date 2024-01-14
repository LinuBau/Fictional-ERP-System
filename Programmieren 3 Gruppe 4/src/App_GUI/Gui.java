package App_GUI;

import ActionListener.WindowEventListener;
import ActionListener.shoppingCartListner;
import GeschaftsObejekt.MusikList;
import GeschaftsObejekt.profilList;
import MenuBar.benutzerMenuBar;
import MenuBar.mitarbeiterMenuBar;
import ActionListener.BearbeitenListener;
import ActionListener.FilterListener;
import Modele.MusikTableModel;
import SaveData_ReadData.MusikListDAO;
import ToolBar.benutzerToolBar;
import ToolBar.mitarbeiterToolBar;
import Traversierung.Musikmap;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Gui extends JFrame {

    private JTable AtributTabelle;
    private MusikTableModel tableModel;
    private Musikmap musikmap;
    private MusikList musikList;
    private profilList profilList;
    private BearbeitenListener bearbeitenListener;
    private shoppingCartListner shoppingCartListner;
    private FilterListener filterListener;
    private Locale locale;
    private ResourceBundle bundle;
    private String[] language = {"Deutsch","English","France","Sverige"};
    private String[] shortlanguage = {"de","en","fr","sv"};



    public Gui(boolean starten, profilList pl,Locale languageLocale) {
        if (starten) {
            initialiseMitarbeiterFarme(pl,languageLocale);
        } else {
            initialiseUsserFrame(pl,languageLocale);
        }
    }

    public Gui(boolean starten, profilList pl, MusikList ml,Locale languageLocale) {
        if (starten) {
            initialiseMitarbeiterFarme(pl, ml,languageLocale);
        } else {
            initialiseUsserFrame(pl, ml,languageLocale);
        }
    }

    public void updateTableWithMusikListe(MusikList musiklist) {
        this.tableModel.setMusikList(musiklist);
        this.tableModel.fireTableDataChanged();
    }

    public MusikList getMusikList() {
        return this.musikList;
    }

    public Musikmap getMusikMap() {
        return this.musikmap;
    }
    public String getL10NText(String key){
        return bundle.getString(key);
    }

    public MusikTableModel getTableModel() {
        return this.tableModel;
    }

    public shoppingCartListner getShoppingCartListner() {
        return this.shoppingCartListner;
    }
    public Locale getLocale(){
        return this.locale;
    }

    public profilList getProfilList() {
        return this.profilList;
    }

    private void initialiseUsserFrame(profilList pl,Locale languageLocale) {
        this.profilList = pl;

        //Create Locale
        locale = languageLocale;
        bundle = ResourceBundle.getBundle("I18NPropertiesFiles/Bundel", locale);
    

        // Importing Data
        musikList = new MusikList();
        MusikListDAO mld = new MusikListDAO("setup.data", false);
        try {
            mld.read(musikList);
            mld.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // Initializing the MusikMap
        musikmap = new Musikmap(musikList);

        // Initializing the ActionListner
        filterListener = new FilterListener(this, musikmap);
        bearbeitenListener = new BearbeitenListener(this);
        shoppingCartListner = new shoppingCartListner(this);

        // add FilterPanel and EditPanel
        JPanel eingabePanel = new JPanel(new FlowLayout());
        eingabePanel.add(filterListener.getFilterPanel());
        eingabePanel.add(bearbeitenListener.getUsserBearbeitenPanel());

        // Initializing sortetd JTable
        String[] tableKeys = {"mid", "k", "at", "st", "rc", "rs", "cvp", "svp", "mvp", "cep", "sep", "mep", "g", "cd", "sp", "mp3"};
        String[] tableValue = new String[16];
        for(int i=0;i<tableValue.length;i++){
            tableValue[i] = getL10NText(tableKeys[i]);
        }
        MusikList sotierteList = new MusikList();
        sotierteList.addAll(musikmap.sortMusikListBySongName(musikList));
        tableModel = new MusikTableModel(sotierteList,tableValue);
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
        setJMenuBar(new benutzerMenuBar(this,language,shortlanguage));

        // add WindowEventListner
        addWindowListener(new WindowEventListener(this));

    }

    private void initialiseUsserFrame(profilList pl, MusikList ml,Locale languageLocale) {
        this.profilList = pl;
        this.musikList = ml;

        //Create Locale
        locale = languageLocale;
        bundle = ResourceBundle.getBundle("I18NPropertiesFiles/Bundel", locale);

        // Initializing the MusikMap
        musikmap = new Musikmap(musikList);

        // Initializing the ActionListner
        filterListener = new FilterListener(this, musikmap);
        bearbeitenListener = new BearbeitenListener(this);
        shoppingCartListner = new shoppingCartListner(this);

        // add FilterPanel and EditPanel
        JPanel eingabePanel = new JPanel(new FlowLayout());
        eingabePanel.add(filterListener.getFilterPanel());
        eingabePanel.add(bearbeitenListener.getUsserBearbeitenPanel());

        // Initializing sortetd JTable
        MusikList sotierteList = new MusikList();
        String[] tableKeys = {"mid", "k", "at", "st", "rc", "rs", "cvp", "svp", "mvp", "cep", "sep", "mep", "g", "cd", "sp", "mp3"};
        String[] tableValue = new String[16];
        for(int i=0;i<tableValue.length;i++){
            tableValue[i] = getL10NText(tableKeys[i]);
        }
        sotierteList.addAll(musikmap.sortMusikListBySongName(musikList));
        tableModel = new MusikTableModel(sotierteList,tableValue);
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
        setJMenuBar(new benutzerMenuBar(this,language,shortlanguage));

        // add WindowEventListner
        addWindowListener(new WindowEventListener(this));

    }

    /**
     * 
     * @param pl
     */

    private void initialiseMitarbeiterFarme(profilList pl,Locale languageLocale) {
        this.profilList = pl;
         //Create Locale
         locale = languageLocale;
         bundle = ResourceBundle.getBundle("I18NPropertiesFiles/Bundel", locale);

        // Importing Data
        musikList = new MusikList();
        MusikListDAO mld = new MusikListDAO("setup.data", false);
        try {
            mld.read(musikList);
            mld.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // Initializing the MusikMap
        musikmap = new Musikmap(musikList);

        // Initializing the ActionListner
        filterListener = new FilterListener(this, musikmap);
        bearbeitenListener = new BearbeitenListener(this);

        // add FilterPanel and EditPanel#
        JPanel eingabePanel = new JPanel(new FlowLayout());
        eingabePanel.add(filterListener.getFilterPanel());
        eingabePanel.add(bearbeitenListener.getMitarbeiterBearbeitenPanel());

        String[] tableKeys = {"mid", "k", "at", "st", "rc", "rs", "cvp", "svp", "mvp", "cep", "sep", "mep", "g", "cd", "sp", "mp3"};
        String[] tableValue = new String[16];
        for(int i=0;i<tableValue.length;i++){
            tableValue[i] = getL10NText(tableKeys[i]);
        }
        // Initializing sortetd JTable
        MusikList sotierteList = new MusikList();
        sotierteList.addAll(musikmap.sortMusikListBySongName(musikList));
        tableModel = new MusikTableModel(sotierteList,tableValue);
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
        setJMenuBar(new mitarbeiterMenuBar(this,language,shortlanguage));

        // add WindowEventListner
        addWindowListener(new WindowEventListener(this));
    }

    private void initialiseMitarbeiterFarme(profilList pl, MusikList ml,Locale languageLocale) {
        this.profilList = pl;
        this.musikList = ml;
         //Create Locale
         locale = languageLocale;
         bundle = ResourceBundle.getBundle("/I18NPropertiesFiles/Bundel", locale);
        // Initializing the MusikMap
        musikmap = new Musikmap(musikList);

        // Initializing the ActionListner
        filterListener = new FilterListener(this, musikmap);
        bearbeitenListener = new BearbeitenListener(this);

        // add FilterPanel and EditPanel#
        JPanel eingabePanel = new JPanel(new FlowLayout());
        eingabePanel.add(filterListener.getFilterPanel());
        eingabePanel.add(bearbeitenListener.getMitarbeiterBearbeitenPanel());

        // Initializing sortetd JTable
        String[] tableKeys = {"mid", "k", "at", "st", "rc", "rs", "cvp", "svp", "mvp", "cep", "sep", "mep", "g", "cd", "sp", "mp3"};
        String[] tableValue = new String[16];
        for(int i=0;i<tableValue.length;i++){
            tableValue[i] = getL10NText(tableKeys[i]);
        }
        MusikList sotierteList = new MusikList();
        sotierteList.addAll(musikmap.sortMusikListBySongName(musikList));
        tableModel = new MusikTableModel(sotierteList,tableValue);
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
        setJMenuBar(new mitarbeiterMenuBar(this,language,shortlanguage));

        // add WindowEventListner
        addWindowListener(new WindowEventListener(this));
    }

    public static void main(String[] args) {

        
        loginGUi loginWindow = new loginGUi("de");
         loginWindow.setTitle("Login");
         loginWindow.setSize(500, 500);
         loginWindow.setVisible(true);
         // loginWindow.w();

    

         
    }
}
