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
    private final MusikMap musikMap;
    private JToggleButton toggleButtonCD;
    private JToggleButton toggleButtonMP3;
    private JToggleButton toggleButtonVinyl;

    private final Gui parent;
    private JComboBox<String> genreComboBox;
    private JComboBox<String> albumComboBox;
    private JComboBox<String> titleComboBox;
    private JComboBox<String> artistComboBox;
    
    private JSpinner mindestpreisSpinner;
    private JSpinner höchstpreisSpinner;

    private JLabel trefferAnzeige;

    public FilterListener(Gui gui, MusikMap musikMap) {
        this.parent = gui;
        this.musikMap = musikMap;

    }

    public JPanel getFilterPanel() {
        // Initialisierung der Schaltflächen und Textfelder
        toggleButtonCD = new JToggleButton(parent.getL10NText("cd"), true);
        toggleButtonMP3 = new JToggleButton(parent.getL10NText("mp3"), true);
        toggleButtonVinyl = new JToggleButton(parent.getL10NText("v"), true);

        
        SpinnerNumberModel minModel = new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.5);
        SpinnerNumberModel maxModel = new SpinnerNumberModel(100, 0.0, Double.MAX_VALUE, 0.5);
        
        mindestpreisSpinner = new JSpinner(minModel);
        höchstpreisSpinner = new JSpinner(maxModel);
        
        mindestpreisSpinner.setPreferredSize(new Dimension(100, 20));
        höchstpreisSpinner.setPreferredSize(new Dimension(100, 20));
        
        titleComboBox = new JComboBox<>();
        titleComboBox.setEditable(true);
        titleComboBox.addItem(parent.getL10NText("all"));
        musikMap.getTitleMap().keySet().stream().sorted().forEach(titleComboBox::addItem);
        
        
        JTextField titleTextField = (JTextField) titleComboBox.getEditor().getEditorComponent();
        titleTextField.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e) {
                SwingUtilities.invokeLater(() -> {
                    String text = titleTextField.getText();
                    if (text.isEmpty()) {
                        titleComboBox.hidePopup();
                        return;
                    }

                    titleComboBox.removeAllItems();
                    musikMap.getTitleMap().keySet().stream()
                        .filter(title -> title.toLowerCase().startsWith(text.toLowerCase()))
                        .forEach(titleComboBox::addItem);

                    titleComboBox.showPopup();
                    titleTextField.setText(text);
                    titleTextField.setCaretPosition(text.length());
                    });
             }
        });
        
        
        artistComboBox = new JComboBox<>();
        artistComboBox.setEditable(true);
        artistComboBox.addItem(parent.getL10NText("all"));
        JTextField artistTextField = (JTextField) artistComboBox.getEditor().getEditorComponent();
        artistTextField.addKeyListener(new KeyAdapter(){
         public void keyReleased(KeyEvent e) {
                SwingUtilities.invokeLater(() -> {
                    String text = artistTextField.getText();
                    if (text.isEmpty()) {
                        artistComboBox.hidePopup();
                        return;
                    }

                    artistComboBox.removeAllItems();
                    musikMap.getArtistMap().keySet().stream()
                        .filter(artist -> artist.toLowerCase().startsWith(text.toLowerCase()))
                        .forEach(artistComboBox::addItem);

                    artistComboBox.showPopup();
                    artistTextField.setText(text);
                    artistTextField.setCaretPosition(text.length());
                    });
             }
        });

        albumComboBox = new JComboBox<>();
        albumComboBox.setEditable(true);
        albumComboBox.addItem(parent.getL10NText("all"));
        musikMap.getAlbumMap().keySet().stream().sorted().forEach(albumComboBox::addItem);
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
        genreComboBox.addItem(parent.getL10NText("all")); // Option für alle Genres
        List<String> sortedGenres = musikMap.getGenreMap().keySet().stream()
                .filter(genre -> genre != null && !genre.trim().isEmpty())
                .sorted()
                .collect(Collectors.toList());
        sortedGenres.forEach(genreComboBox::addItem);
    
        
        // Erstellen des Eingabepanels
        JPanel eingabePanel = new JPanel(new GridLayout(8, 2, 10, 10));
        eingabePanel.add(new JLabel(parent.getL10NText("t")+": "));
        eingabePanel.add(titleComboBox);
        eingabePanel.add(new JLabel(parent.getL10NText("a")+": "));
        eingabePanel.add(albumComboBox);
        eingabePanel.add(new JLabel(parent.getL10NText("i")+ ": "));
        eingabePanel.add(artistComboBox);
        eingabePanel.add(new JLabel(parent.getL10NText("g")+": "));
        eingabePanel.add(genreComboBox);
        eingabePanel.add(new JLabel(parent.getL10NText("mtyp")+": "));
        eingabePanel.add(createMedientypenPanel());
        eingabePanel.add(new JLabel(parent.getL10NText("mp")+": "));
        eingabePanel.add(mindestpreisSpinner);
        eingabePanel.add(new JLabel(parent.getL10NText("hp")+": "));
        eingabePanel.add(höchstpreisSpinner);
        
        eingabePanel.validate();
        // Erstellen des Filterknopfes
        trefferAnzeige = new JLabel(parent.getL10NText("tr")+": 0");

        JButton filternButton = new JButton(parent.getL10NText("f"));
        filternButton.addActionListener(this);
        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(filternButton);
        filterPanel.add(trefferAnzeige);

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
    double minPreis = (double) mindestpreisSpinner.getValue();
    double maxPreis = (double) höchstpreisSpinner.getValue();
    String originalTitel = (String) titleComboBox.getSelectedItem();
    String originalArtist = (String) artistComboBox.getSelectedItem();
    String originalAlbum = (String) albumComboBox.getSelectedItem();
    String originalGenre = (String) genreComboBox.getSelectedItem();

    final String titel = (originalTitel == null || originalTitel.trim().isEmpty()) ? parent.getL10NText("all") : originalTitel.trim();
    final String artist = (originalArtist == null || originalArtist.trim().isEmpty()) ? parent.getL10NText("all") : originalArtist.trim();
    final String album = (originalAlbum == null || originalAlbum.trim().isEmpty()) ? parent.getL10NText("all") : originalAlbum.trim();
    final String genre = (originalGenre == null || originalGenre.trim().isEmpty()) ? parent.getL10NText("all") : originalGenre.trim();
    
    boolean isCDSelected = toggleButtonCD.isSelected();
    boolean isMP3Selected = toggleButtonMP3.isSelected();
    boolean isVinylSelected = toggleButtonVinyl.isSelected();

  
    List<Musik> gefilterteListe = musikMap.getMusikList().stream()
            .filter(m -> {
                if (genre != null && !genre.equals(parent.getL10NText("all"))) {
                    List<Musik> genreList = musikMap.getGenreMap().getOrDefault(genre, Collections.emptyList());
                    if (!genreList.contains(m)) {
                        return false;
                    }
                }
                
                if (artist != null && !artist.equals(parent.getL10NText("all"))) {
                    List<Musik> artistList = musikMap.getArtistMap().getOrDefault(artist, Collections.emptyList());
                    if (!artistList.contains(m)) {
                        return false;
                    }
                }
                
                if (titel != null && !titel.equals(parent.getL10NText("all"))) {
                    List<Musik> titelList = musikMap.getTitleMap().getOrDefault(titel, Collections.emptyList());
                    if (!titelList.contains(m)) {
                        return false;
                    }
                }
                
                
                if (album != null && !album.equals(parent.getL10NText("all"))) {
                    List<Musik> albumList = musikMap.getAlbumMap().getOrDefault(album, Collections.emptyList());
                    if (!albumList.contains(m)) {
                        return false;
                    }
                }
                
                
                if ((isCDSelected && m.getIsCD() && m.getCDListenpreis() >= minPreis && m.getCDListenpreis() <= maxPreis) ||
                    (isMP3Selected && m.getIsMp3() && m.getMp3Listenpreis() >= minPreis && m.getMp3Listenpreis() <= maxPreis) ||
                    (isVinylSelected && m.getIsPlatte() && m.getPlatteListenpreis() >= minPreis && m.getPlatteListenpreis() <= maxPreis)) {
                    return true;
                }
                return false;
            })
            .collect(Collectors.toList());

    TreeMap<String, Musik> sortierteErgebnisse = gefilterteListe.stream()
            .collect(Collectors.toMap(
                    Musik::getSongName,
                    m -> m,
                    (existing, replacement) -> existing,
                    TreeMap::new));

    MusikList gefilterteErgebnisse = new MusikList();
    gefilterteErgebnisse.addAll(sortierteErgebnisse.values());
    
    long anzahlTreffer = gefilterteErgebnisse.size();
    trefferAnzeige.setText(parent.getL10NText("tr") + ": " + anzahlTreffer);

    parent.updateTableWithMusikListe(gefilterteErgebnisse);
}
}