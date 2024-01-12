package ActionListener;

import App_GUI.Gui;
import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;
import Traversierung.Musikmap;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
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
    private final Musikmap musikMap;
    private JToggleButton toggleButtonCD;
    private JToggleButton toggleButtonMP3;
    private JToggleButton toggleButtonVinyl;

    private JTextField titelTextField;
    private JTextField interpretTextField;
    private final Gui parent;
    private JComboBox<String> genreComboBox;
    private JComboBox<String> albumComboBox;

    private JSpinner mindestpreisSpinner;
    private JSpinner höchstpreisSpinner;

    private JLabel trefferAnzeige;

    public FilterListener(Gui gui, Musikmap musikMap) {
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
        trefferAnzeige = new JLabel("Treffer: 0");

        JButton filternButton = new JButton("Filtern");
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
    String selectedAlbum = (String) albumComboBox.getSelectedItem();

    String titel = titelTextField.getText().toLowerCase();
    String interpret = interpretTextField.getText().toLowerCase();
    String genre = (String) genreComboBox.getSelectedItem();

    boolean isCDSelected = toggleButtonCD.isSelected();
    boolean isMP3Selected = toggleButtonMP3.isSelected();
    boolean isVinylSelected = toggleButtonVinyl.isSelected();

    // Filtern Sie die Liste einmal und speichern Sie das Ergebnis in einer neuen Liste
    List<Musik> gefilterteListe = musikMap.getMusikList().stream()
            .filter(m -> {
                if (genre != null && !genre.equals("Alle")) {
                    List<Musik> genreList = musikMap.getGenreMap().getOrDefault(genre, Collections.emptyList());
                    if (!genreList.contains(m)) {
                        return false;
                    }
                }

                if (selectedAlbum != null && !selectedAlbum.equals("Alle")) {
                    List<Musik> albumList = musikMap.getAlbumMap().getOrDefault(selectedAlbum, Collections.emptyList());
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
            .filter(m -> {
                if (!interpret.isEmpty() && !m.getMusiker().toLowerCase().contains(interpret)) {
                    return false;
                }
                return true;
            })
            .filter(m -> titel.isEmpty() || m.getSongName().toLowerCase().contains(titel))
            .collect(Collectors.toList());

    long anzahlTreffer = gefilterteListe.size();
    trefferAnzeige.setText("Treffer: " + anzahlTreffer);

    TreeMap<String, Musik> sortierteErgebnisse = gefilterteListe.stream()
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