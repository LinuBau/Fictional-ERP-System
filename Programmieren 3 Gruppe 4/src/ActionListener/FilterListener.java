package ActionListener;

import App_GUI.Gui;
import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;
import Traversierung.MusikMap;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

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
    private JComboBox<String> genreComboBox;
    private JComboBox<String> albumComboBox;

    private JSpinner mindestpreisSpinner;
    private JSpinner höchstpreisSpinner;

    

    public FilterListener(Gui gui, MusikMap musikMap) {
        this.parent = gui;
        this.musikMap = musikMap;

    }

    public JPanel getFilterPanel() {
        // Initialisierung der Schaltflächen und Textfelder
        toggleButtonCD = new JToggleButton("CD", true);
        toggleButtonMP3 = new JToggleButton("MP3", true);
        toggleButtonVinyl = new JToggleButton("Vinyl", true);

        titelTextField = new JTextField(20);
        interpretTextField = new JTextField(20);
        albumTextField = new JTextField(20);
        genreTextField = new JTextField(20);
        
        SpinnerNumberModel minModel = new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.5);
        SpinnerNumberModel maxModel = new SpinnerNumberModel(100, 0.0, Double.MAX_VALUE, 0.5);
        
        mindestpreisSpinner = new JSpinner(minModel);
        höchstpreisSpinner = new JSpinner(maxModel);
        
        mindestpreisSpinner.setPreferredSize(new Dimension(100, 20));
        höchstpreisSpinner.setPreferredSize(new Dimension(100, 20));

        albumComboBox = new JComboBox<>();
        albumComboBox.setEditable(true);
        albumComboBox.addItem("Alle");
        for (String album : musikMap.getAlbumMap().keySet()) {
            albumComboBox.addItem(album);
        }
        JTextField albumTextField = (JTextField) albumComboBox.getEditor().getEditorComponent();
        albumTextField.addKeyListener(new KeyAdapter() {
            
            public void keyReleased(KeyEvent e) {
                SwingUtilities.invokeLater(() -> {
                    String text = albumTextField.getText();
                    if (text.isEmpty()) {
                        albumComboBox.hidePopup();
                        return;
                    }

                    albumComboBox.removeAllItems();
                    musikMap.getAlbumMap().keySet().stream()
                        .filter(album -> album.toLowerCase().startsWith(text.toLowerCase()))
                        .forEach(albumComboBox::addItem);

                    albumComboBox.showPopup();
                    albumTextField.setText(text);
                    albumTextField.setCaretPosition(text.length());
                });
            }
        });
         
        genreComboBox = new JComboBox<>();
        genreComboBox.addItem("Alle"); // Option für alle Genres
        for (String genre : musikMap.getGenreMap().keySet()) {
            if(genre != null && !genre.trim().isEmpty()) {
            genreComboBox.addItem(genre);
            }
        }
        // Erstellen des Eingabepanels
        JPanel eingabePanel = new JPanel(new GridLayout(8, 2, 10, 10));
        eingabePanel.add(new JLabel("Titel: "));
        eingabePanel.add(titelTextField);
        eingabePanel.add(new JLabel("Album: "));
        eingabePanel.add(albumComboBox);
        eingabePanel.add(new JLabel("Interpret: "));
        eingabePanel.add(interpretTextField);
        eingabePanel.add(new JLabel("Genre: "));
        eingabePanel.add(genreComboBox);
        eingabePanel.add(new JLabel("Medientypen: "));
        eingabePanel.add(createMedientypenPanel());
        eingabePanel.add(new JLabel("Mindestpreis: "));
        eingabePanel.add(mindestpreisSpinner);
        eingabePanel.add(new JLabel("Höchstpreis: "));
        eingabePanel.add(höchstpreisSpinner);
        
        eingabePanel.validate();

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
        List<Musik> gefilterteListe = new ArrayList<>(musikMap.getMusikList());
        
        double minPreis = (double) mindestpreisSpinner.getValue();
        double maxPreis = (double) höchstpreisSpinner.getValue();
        String selectedAlbum = (String) albumComboBox.getSelectedItem();

        String titel = titelTextField.getText().toLowerCase();
        String interpret = interpretTextField.getText().toLowerCase();
        String genre = (String) genreComboBox.getSelectedItem();

        boolean isCDSelected = toggleButtonCD.isSelected();
        boolean isMP3Selected = toggleButtonMP3.isSelected();
        boolean isVinylSelected = toggleButtonVinyl.isSelected();

        if (genre != null && !genre.equals("Alle")) {
        gefilterteListe = new ArrayList<>(musikMap.getGenreMap().getOrDefault(genre, Collections.emptyList()));
            }
        
        if (selectedAlbum != null && !selectedAlbum.equals("Alle")) {
        List<Musik> albumList = musikMap.getAlbumMap().getOrDefault(selectedAlbum, Collections.emptyList());
        gefilterteListe.retainAll(albumList);
        }
        
        Stream<Musik> stream = gefilterteListe.stream();
        
        if (isCDSelected || isMP3Selected || isVinylSelected) {
            stream = stream.filter(m -> {
                boolean matchesCD = isCDSelected && m.getIsCD() && m.getCDListenpreis() >= minPreis && m.getCDListenpreis() <= maxPreis;
                boolean matchesMP3 = isMP3Selected && m.getIsMp3() && m.getMp3Listenpreis() >= minPreis && m.getMp3Listenpreis() <= maxPreis;
                boolean matchesVinyl = isVinylSelected && m.getIsPlatte() && m.getPlatteListenpreis() >= minPreis && m.getPlatteListenpreis() <= maxPreis;
                return matchesCD || matchesMP3 || matchesVinyl;
        });
       
        if (!interpret.isEmpty()) {
            stream = stream.filter(m -> m.getMusiker().toLowerCase().contains(interpret));
        }
        if (!titel.isEmpty()) {
        stream = stream.filter(m -> m.getSongName().toLowerCase().contains(titel));
        }
      
         TreeMap<String, Musik> sortierteErgebnisse = stream.collect(Collectors.toMap(
                Musik::getSongName, 
                m -> m, 
                (existing, replacement) -> existing, 
                TreeMap::new));
   
   
        MusikList gefilterteErgebnisse = new MusikList();
        gefilterteErgebnisse.addAll(sortierteErgebnisse.values());

        parent.updateTableWithMusikListe(gefilterteErgebnisse); 
    }

}
}