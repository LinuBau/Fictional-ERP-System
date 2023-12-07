package ActionListener;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class HinzufuegenListener extends JDialog implements ActionListener {

    private JTextField titelTextField;
    private JTextField interpretTextField;

    private JComboBox<String> genreComboBox;
    private JTextField musikGUIDTextField;
    private JTextField musikerTextField;
    private JTextField albumTextField;
    private JTextField songNameTextField;
    private JTextField regalPlatzCDTextField;
    private JTextField regalPlatzPlatteTextField;
    private JTextField cdListenpreisTextField;
    private JTextField platteListenpreisTextField;
    private JTextField mp3ListenpreisTextField;
    private JTextField cdEinkaufspreisTextField;
    private JTextField platteEinkaufspreisTextField;
    private JTextField mp3EinkaufspreisTextField;
    private JCheckBox cdCheckBox;
    private JCheckBox platteCheckBox;
    private JCheckBox mp3CheckBox;

    public HinzufuegenListener() {
        super();
        this.setSize(400, 150);

        String[] genreOptionen = { "Alle", "Pop", "Rock", "Hip-Hop", "Klassik", "Andere" };
        genreComboBox = new JComboBox<>(genreOptionen);
        musikGUIDTextField = new JTextField();
        musikerTextField = new JTextField();
        albumTextField = new JTextField();
        songNameTextField = new JTextField();
        regalPlatzCDTextField = new JTextField();
        regalPlatzPlatteTextField = new JTextField();
        cdListenpreisTextField = new JTextField();
        platteListenpreisTextField = new JTextField();
        mp3ListenpreisTextField = new JTextField();
        cdEinkaufspreisTextField = new JTextField();
        platteEinkaufspreisTextField = new JTextField();
        mp3EinkaufspreisTextField = new JTextField();
        cdCheckBox = new JCheckBox();
        platteCheckBox = new JCheckBox();
        mp3CheckBox = new JCheckBox();

        this.setLayout(new FlowLayout());
        // Textflied vor all Compents
        JPanel eingabePanel = new JPanel(new GridLayout(8, 2));

        eingabePanel.add(new JLabel("Musik_GUID: "));
        eingabePanel.add(musikGUIDTextField);

        eingabePanel.add(new JLabel("Musiker: "));
        eingabePanel.add(musikerTextField);

        eingabePanel.add(new JLabel("Album: "));
        eingabePanel.add(albumTextField);

        eingabePanel.add(new JLabel("Song Name: "));
        eingabePanel.add(songNameTextField);

        eingabePanel.add(new JLabel("Regal Platz CD: "));
        eingabePanel.add(regalPlatzCDTextField);

        eingabePanel.add(new JLabel("Regal Platz Platte: "));
        eingabePanel.add(regalPlatzPlatteTextField);

        eingabePanel.add(new JLabel("Listenpreis CD: "));
        eingabePanel.add(cdListenpreisTextField);

        eingabePanel.add(new JLabel("Listenpreis Platte: "));
        eingabePanel.add(platteListenpreisTextField);

        eingabePanel.add(new JLabel("Listenpreis MP3: "));
        eingabePanel.add(mp3ListenpreisTextField);

        eingabePanel.add(new JLabel("Einkaufspreis CD: "));
        eingabePanel.add(cdEinkaufspreisTextField);

        eingabePanel.add(new JLabel("Einkaufspreis Platte: "));
        eingabePanel.add(platteEinkaufspreisTextField);

        eingabePanel.add(new JLabel("Einkaufspreis MP3: "));
        eingabePanel.add(mp3EinkaufspreisTextField);

        eingabePanel.add(new JLabel("Genre: "));
        eingabePanel.add(genreComboBox);

        eingabePanel.add(new JLabel("CD: "));
        eingabePanel.add(cdCheckBox);

        eingabePanel.add(new JLabel("Platte: "));
        eingabePanel.add(platteCheckBox);

        eingabePanel.add(new JLabel("MP3: "));
        eingabePanel.add(mp3CheckBox);

        JButton Hinzufuegen = new JButton("Hinzufuegen");

        JPanel hinzufuegenPanel = new JPanel(new FlowLayout());
        hinzufuegenPanel.add(Hinzufuegen);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(eingabePanel, BorderLayout.NORTH);
        this.getContentPane().add(hinzufuegenPanel, BorderLayout.CENTER);

        this.setModal(true);
        this.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(true);
    }

}
