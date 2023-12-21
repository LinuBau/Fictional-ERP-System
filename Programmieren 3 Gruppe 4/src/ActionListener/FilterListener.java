package ActionListener;

import App_GUI.Gui;
import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;
import Traversierung.MusikMap;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FilterListener implements ActionListener {
    private MusikMap musikMap;
    private JToggleButton toggleButtonCD;
    private JToggleButton toggleButtonMP3;
    private JToggleButton toggleButtonVinyl;

    private JTextField titelTextField;
    private JTextField interpretTextField;
    private JTextField albumTextField;
    private JTextField genreTextField;
    private Gui parent;

    public FilterListener(Gui gui, MusikMap musikMap) {
        this.parent = gui;
        this.musikMap = musikMap;

    }

    public JPanel getFilterPanel() {
        // Initialisierung der Schaltflächen und Textfelder
        toggleButtonCD = new JToggleButton("CD", false);
        toggleButtonMP3 = new JToggleButton("MP3", false);
        toggleButtonVinyl = new JToggleButton("Vinyl", false);

        titelTextField = new JTextField(20);
        interpretTextField = new JTextField(20);
        albumTextField = new JTextField(20);
        genreTextField = new JTextField(20);

        // Erstellen des Eingabepanels
        JPanel eingabePanel = new JPanel(new GridLayout(5, 2));
        eingabePanel.add(new JLabel("Titel: "));
        eingabePanel.add(titelTextField);
        eingabePanel.add(new JLabel("Interpret: "));
        eingabePanel.add(interpretTextField);
        eingabePanel.add(new JLabel("Album: "));
        eingabePanel.add(albumTextField);
        eingabePanel.add(new JLabel("Genre: "));
        eingabePanel.add(genreTextField);
        eingabePanel.add(new JLabel("Medientypen: "));
        eingabePanel.add(createMedientypenPanel());

        // Erstellen des Filterknopfes
        JButton filternButton = new JButton("Filtern");
        filternButton.addActionListener(this);
        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(filternButton);

        // Zusammenführen der Panels
        JPanel returbPanel = new JPanel();
        returbPanel.setLayout(new BorderLayout());
        returbPanel.add(eingabePanel, BorderLayout.NORTH);
        returbPanel.add(filterPanel, BorderLayout.SOUTH);

        return returbPanel;
    }

    private JPanel createMedientypenPanel() {
        JPanel medientypenPanel = new JPanel();
        medientypenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        medientypenPanel.add(toggleButtonCD);
        medientypenPanel.add(toggleButtonMP3);
        medientypenPanel.add(toggleButtonVinyl);

        return medientypenPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String titel = titelTextField.getText().toLowerCase();
        String interpret = interpretTextField.getText().toLowerCase();
        String album = albumTextField.getText().toLowerCase();
        String genre = genreTextField.getText().toLowerCase();

        boolean isCDSelected = toggleButtonCD.isSelected();
        boolean isMP3Selected = toggleButtonMP3.isSelected();
        boolean isVinylSelected = toggleButtonVinyl.isSelected();

         Stream<Musik> stream = musikMap.getMusikList().stream();

         if (!titel.isEmpty()) {
        stream = stream.filter(m -> m.getSongName().toLowerCase().contains(titel));
        }
        if (!interpret.isEmpty()) {
            stream = stream.filter(m -> m.getMusiker().toLowerCase().contains(interpret));
        }
        if (!album.isEmpty()) {
            stream = stream.filter(m -> m.getAlbum().toLowerCase().contains(album));
        }
        if (!genre.isEmpty()) {
            stream = stream.filter(m -> m.getGenre().toLowerCase().contains(genre));
        }

        // Filtern basierend auf Medientyp
         stream = stream.filter(m -> (!isCDSelected || m.getIsCD()) &&
                                (!isMP3Selected || m.getIsMp3()) &&
                                (!isVinylSelected || m.getIsPlatte()));
            
        TreeMap<String, Musik> sortierteErgebnisse = stream
        .collect(Collectors.toMap(
            Musik::getSongName, 
            m -> m, 
            (existing, replacement) -> existing, 
            TreeMap::new));
        
   
   
        MusikList gefilterteErgebnisse = new MusikList();
        gefilterteErgebnisse.addAll(sortierteErgebnisse.values());

        parent.updateTableWithMusikListe(gefilterteErgebnisse); 
    }

}