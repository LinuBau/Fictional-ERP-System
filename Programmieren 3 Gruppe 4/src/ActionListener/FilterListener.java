package ActionListener;

import App_GUI.Gui;
import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FilterListener implements ActionListener {

    private JToggleButton toggleButtonCD;
    private JToggleButton toggleButtonMP3;
    private JToggleButton toggleButtonVinyl;

    private JTextField titelTextField;
    private  JTextField interpretTextField;
    private  JTextField albumTextField;
    private  JTextField genreTextField;
    private  Gui parent;

    public FilterListener( Gui gui) {
        super();
        this.parent = gui;

    }

    private JPanel createMedientypenPanel() {
        JPanel medientypenPanel = new JPanel();
        medientypenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        medientypenPanel.add(toggleButtonCD);
        medientypenPanel.add(toggleButtonMP3);
        medientypenPanel.add(toggleButtonVinyl);

        return medientypenPanel;
    }
    public JPanel getFilterPanel(){
        
        toggleButtonCD = new JToggleButton("CD", false);
        toggleButtonMP3 = new JToggleButton("MP3", false);
        toggleButtonVinyl = new JToggleButton("Vinyl", false);

        titelTextField = new JTextField(20);
        interpretTextField = new JTextField(20);
        albumTextField = new JTextField(20);
        genreTextField = new JTextField(20);

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

        JButton filternButton = new JButton("Filtern");
        filternButton.addActionListener(this);

        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(filternButton);
        JPanel returbPanel = new JPanel();
        returbPanel.setLayout(new BorderLayout());
        returbPanel.add(eingabePanel, BorderLayout.NORTH);
        returbPanel.add(filterPanel, BorderLayout.SOUTH);
        return returbPanel;
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

       
        List<Musik> ergebnisse = new ArrayList<>(parent.getMusikMap().getMusikList());

        if (!titel.isEmpty()) {
            ergebnisse.retainAll(parent.getMusikMap().getMedienBySongName(titel));
        }
        if (!interpret.isEmpty()) {
            ergebnisse.retainAll(parent.getMusikMap().getMedienByMusiker(interpret));
        }
        if (!album.isEmpty()) {
            ergebnisse.retainAll(parent.getMusikMap().getMedienByAlbum(album));
        }
        if (!genre.isEmpty()) {
            ergebnisse.retainAll(parent.getMusikMap().getMedienByGenre(genre));
        }
        if (isCDSelected) {
            ergebnisse.retainAll(parent.getMusikMap().getMedienByCD());
        }
        if (isMP3Selected) {
            ergebnisse.retainAll(parent.getMusikMap().getMedienByMP3());
        }
        if (isVinylSelected) {
            ergebnisse.retainAll(parent.getMusikMap().getMedienByVinyl());
        }

        List<Musik> sortierteErgebnisse = parent.getMusikMap().sortMusikListBySongName(ergebnisse);

        MusikList gefilterteErgebnisse = new MusikList();
        gefilterteErgebnisse.addAll(sortierteErgebnisse);

        parent.updateTableWithMusikListe(gefilterteErgebnisse); 
    }

}