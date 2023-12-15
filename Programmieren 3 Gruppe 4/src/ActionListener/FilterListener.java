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
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FilterListener extends JDialog implements ActionListener {

    private JToggleButton toggleButtonCD;
    private JToggleButton toggleButtonMP3;
    private JToggleButton toggleButtonVinyl;

    private final JTextField titelTextField;
    private final JTextField interpretTextField;
    private final JTextField albumTextField;
    private final JTextField genreTextField;
    private final MusikMap musikMap;
    private final Gui gui;

    public FilterListener(MusikMap musikMap, Gui gui) {
        super();
        this.setSize(400, 300);
        this.musikMap = musikMap;
        this.gui = gui;

        toggleButtonCD = new JToggleButton("CD", false);
        toggleButtonMP3 = new JToggleButton("MP3", false);
        toggleButtonVinyl = new JToggleButton("Vinyl", false);

        titelTextField = new JTextField(20);
        interpretTextField = new JTextField(20);
        albumTextField = new JTextField(20);
        genreTextField = new JTextField(20);

        JPanel eingabePanel = new JPanel(new GridLayout(2, 5));
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

        JButton filternButton = new JButton("Filtern");
        filternButton.addActionListener(this);

        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(filternButton);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(eingabePanel, BorderLayout.NORTH);
        this.getContentPane().add(filterPanel, BorderLayout.SOUTH);

        this.setModal(true);
        this.setVisible(false);
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
        String titel = titelTextField.getText();
        String interpret = interpretTextField.getText();
        String album = albumTextField.getText();
        String genre = genreTextField.getText();

        boolean isCDSelected = toggleButtonCD.isSelected();
        boolean isMP3Selected = toggleButtonMP3.isSelected();
        boolean isVinylSelected = toggleButtonVinyl.isSelected();

        List<Musik> gesamteMusikListe = musikMap.getGesamteMusikListe();
        List<Musik> ergebnisse = new ArrayList<>(gesamteMusikListe);

        if (!titel.isEmpty()) {
            ergebnisse.retainAll(musikMap.getMedienBySongName(titel));
        }
        if (!interpret.isEmpty()) {
            ergebnisse.retainAll(musikMap.getMedienByMusiker(interpret));
        }
        if (!album.isEmpty()) {
            ergebnisse.retainAll(musikMap.getMedienByAlbum(album));
        }
        if (!genre.isEmpty()) {
            ergebnisse.retainAll(musikMap.getMedienByGenre(genre));
        }
        if (isCDSelected) {
            ergebnisse.retainAll(musikMap.getMedienByCD());
        }
        if (isMP3Selected) {
            ergebnisse.retainAll(musikMap.getMedienByMP3());
        }
        if (isVinylSelected) {
            ergebnisse.retainAll(musikMap.getMedienByVinyl());
        }

        List<Musik> sortierteErgebnisse = musikMap.sortMusikListBySongName(ergebnisse);

        MusikList gefilterteErgebnisse = new MusikList();
        gefilterteErgebnisse.addAll(sortierteErgebnisse);

        gui.updateTableWithMusikListe(gefilterteErgebnisse);
        this.setVisible(false); 
    }

}