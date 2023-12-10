package ActionListener;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import App_GUI.Gui;
import GeschaftsObejekt.Musik;

public class HinzufuegenListener extends JDialog implements ActionListener {

    private JComboBox<String> genreComboBox;
    private JTextField musikGUIDTextField;
    private JTextField musikerTextField;
    private JTextField albumTextField;
    private JTextField songNameTextField;
    private JTextField regalPlatzCDTextField;
    private JTextField regalPlatzPlatteTextField;
    private JFormattedTextField cdListenpreisTextField;
    private JFormattedTextField platteListenpreisTextField;
    private JFormattedTextField mp3ListenpreisTextField;
    private JFormattedTextField cdEinkaufspreisTextField;
    private JFormattedTextField platteEinkaufspreisTextField;
    private JFormattedTextField mp3EinkaufspreisTextField;
    private JCheckBox cdCheckBox;
    private JCheckBox platteCheckBox;
    private JCheckBox mp3CheckBox;
    private JButton beenden;
    private JButton Hinzufuegen;
    Gui parent;
    
    public HinzufuegenListener(String wasmachen, Gui p) {
        super();
        parent = p;
        NumberFormat format = new DecimalFormat("#.00");
        this.setSize(300, 500);
        String[] genreOptionen = { "Alle", "Pop", "Rock", "Hip-Hop", "Klassik", "Andere" };
        genreComboBox = new JComboBox<>(genreOptionen);
        musikGUIDTextField = new JTextField();
        musikerTextField = new JTextField();
        albumTextField = new JTextField();
        songNameTextField = new JTextField();
        regalPlatzCDTextField = new JTextField();
        regalPlatzPlatteTextField = new JTextField();
        cdListenpreisTextField = new JFormattedTextField(format);
        platteListenpreisTextField = new JFormattedTextField(format);
        mp3ListenpreisTextField = new JFormattedTextField(format);
        cdEinkaufspreisTextField = new JFormattedTextField(format);
        platteEinkaufspreisTextField = new JFormattedTextField(format);
        mp3EinkaufspreisTextField = new JFormattedTextField(format);
        cdCheckBox = new JCheckBox();
        platteCheckBox = new JCheckBox();
        mp3CheckBox = new JCheckBox();

        this.setLayout(new FlowLayout());
        // Textflied vor all Compents
        JPanel eingabePanel = new JPanel(new GridLayout(16, 1));

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

        Hinzufuegen = new JButton("Hinzufuegen");
        beenden = new JButton("Beenden");
        JPanel hinzufuegenPanel = new JPanel(new FlowLayout());
        hinzufuegenPanel.add(Hinzufuegen);
        hinzufuegenPanel.add(beenden);
        beenden.addActionListener(this);
        Hinzufuegen.addActionListener(this);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(eingabePanel, BorderLayout.NORTH);
        this.getContentPane().add(hinzufuegenPanel, BorderLayout.CENTER);

        this.setModal(true);
        this.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(true);
        if (e.getSource().equals(beenden)) {
            this.setVisible(false);
        }
        if (e.getSource().equals(Hinzufuegen)) {
            Musik m = new Musik();
            m.setMusik_GUID(Integer.parseInt(musikGUIDTextField.getText()));
            m.setMusiker(musikerTextField.getText());
            m.setAlbum(albumTextField.getText());
            m.setSongName(songNameTextField.getText());
            m.setRegal_PlatzCD(regalPlatzCDTextField.getText());
            m.setRegal_PlatzPlatte(regalPlatzPlatteTextField.getText().replace(",","."));
            m.setCDListenpreis(Double.parseDouble(cdListenpreisTextField.getText().replace(",",".")));
            m.setPlatteListenpreis(Double.parseDouble(platteListenpreisTextField.getText().replace(",",".")));
            m.setMp3Listenpreis(Double.parseDouble(mp3ListenpreisTextField.getText().replace(",",".")));
            m.setCDEinkaufpreis(Double.parseDouble(cdEinkaufspreisTextField.getText().replace(",",".")));
            m.setPlatteEinkaufpreis(Double.parseDouble(platteEinkaufspreisTextField.getText().replace(",",".")));
            m.setMp3Einkaufpreis(Double.parseDouble(mp3EinkaufspreisTextField.getText().replace(",",".")));
            m.setGenre((String) genreComboBox.getSelectedItem());
            m.setIsCD(cdCheckBox.isSelected());
            m.setIsPlatte(platteCheckBox.isSelected());
            m.setIsMp3(mp3CheckBox.isSelected());
            if (parent.getMusikMap().getMusikList().unique(m.getMusik_GUID(), parent.getMusikMap().getMusikList())) {
                parent.getMusikMap().addMedium(m);
                parent.updateTableWithMusikListe(parent.getMusikMap().getMusikList());
            }
            this.setVisible(false);
        }
    }

}
