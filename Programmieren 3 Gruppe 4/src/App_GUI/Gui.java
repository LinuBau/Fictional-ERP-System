
package App_GUI;

import ActionListener.FilterListener;
import ActionListener.WindowEventListener;
import MenuBar.MenuBar;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import ActionListener.HinzufuegenListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;

/**
 *
 * @author ninfr
 */
public class Gui extends JFrame{
    
    private JButton hinzufuegenButton;
    private JButton loeschenButton;
    private Map<String, String> genreBeispiele;
    private ArrayList<String> musikListe;
    private DefaultListModel<String> listModel;
    private JList<String> musikJList;
    private JButton filternButton;
    private JTable AtributTabelle;
    
    public Gui(){
        hinzufuegenButton = new JButton("Hinzufügen");
        loeschenButton = new JButton("Löschen");
        filternButton = new JButton("Filtern");
        musikListe = new ArrayList<>();
        listModel = new DefaultListModel<>();
        musikJList = new JList<>(listModel);

        // Hier werden die Beispiele in die musikListe aufgenommen
        musikListe.add("Shape of You - Ed Sheeran (Pop)");
        musikListe.add("Stairway to Heaven - Led Zeppelin (Rock)");
        musikListe.add("Sicko Mode - Travis Scott (Hip-Hop)");
        musikListe.add("Eine kleine Nachtmusik - Mozart (Klassik)");
        musikListe.add("Bohemian Rhapsody - Queen (Andere)");
        
        
        JPanel eingabePanel = new JPanel(new GridLayout(3, 1));

        eingabePanel.add(hinzufuegenButton);
        eingabePanel.add(loeschenButton);
        eingabePanel.add(filternButton);
        
        hinzufuegenButton.addActionListener(new HinzufuegenListener()); 
        filternButton.addActionListener(new FilterListener());
        
        String[][] data = {
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            {"Shape of You", "Ed Sheeran", ": Deluxe", "Pop", "null", "null", "null", "null", "null", "null"},
            
        };
 
        // Column Names
        String[] columnNames = {"Title", "Interpret", "Album", "Genre", "RegalplatzCD", "RegalplatzPlatte", "Listenpreis", "ListenpreisMP3", "ListenpreisCD", "ListenpreisPlatte"};
 
        // Initializing the JTable
        AtributTabelle = new JTable(data, columnNames);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(AtributTabelle), BorderLayout.CENTER);
        getContentPane().add(eingabePanel, BorderLayout.NORTH);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        listModel.clear();
        for (String eintrag : musikListe) {
            listModel.addElement(eintrag);
        
        }
    }
    
    public static void main(String[] args) {

        Gui mainWindow = new Gui();
        
        mainWindow.addWindowListener(new WindowEventListener());
        mainWindow.setTitle("Musik Katalog");
        mainWindow.setSize(1000, 500);
        
        mainWindow.setJMenuBar(new MenuBar());

        mainWindow.setVisible(true);
    }
}
