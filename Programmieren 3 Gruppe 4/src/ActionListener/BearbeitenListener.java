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
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import App_GUI.Gui;
import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;

public class BearbeitenListener extends JPanel implements ActionListener {
    public JPanel eingabePanel;
    private JTextField genreComboBox;
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
    private JButton delteButton;
    private JButton SaveButton;
    Gui parent;
    private Musik medium;

    public BearbeitenListener(Gui p) {
        super();
        parent = p;
    }
    public void setMusik(Musik m){
        medium = m;
    }

    public JPanel setJPanel() {
        NumberFormat format = new DecimalFormat("#.00");
        genreComboBox = new JTextField();
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
        delteButton = new JButton("Löschen");
        SaveButton = new JButton("Speicher");

        this.setLayout(new FlowLayout());
        // Textflied vor all Compents
        eingabePanel = new JPanel(new GridLayout(18, 1));

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

        eingabePanel.add(SaveButton);
        eingabePanel.add(delteButton);
        SaveButton.addActionListener(this);
        delteButton.addActionListener(this);
        return eingabePanel;
    }

    public void fillTextBox(Musik m) {
        musikGUIDTextField.setText(String.valueOf(m.getMusik_GUID()));
        musikerTextField.setText(m.getMusiker());
        albumTextField.setText(m.getAlbum());
        songNameTextField.setText(m.getSongName());
        regalPlatzCDTextField.setText(m.getRegal_PlatzCD());
        regalPlatzPlatteTextField.setText(m.getRegal_PlatzPlatte());
        cdListenpreisTextField.setText(String.valueOf(m.getCDListenpreis()));
        platteListenpreisTextField.setText(String.valueOf(m.getPlatteListenpreis()));
        mp3ListenpreisTextField.setText(String.valueOf(m.getMp3Listenpreis()));
        cdEinkaufspreisTextField.setText(String.valueOf(m.getCDEinkaufpreis()));
        platteEinkaufspreisTextField.setText(String.valueOf(m.getPlatteEinkaufpreis()));
        mp3EinkaufspreisTextField.setText(String.valueOf(m.getMp3Einkaufpreis()));
        genreComboBox.setText(m.getGenre());
        cdCheckBox.setSelected(m.getIsCD());
        platteCheckBox.setSelected(m.getIsPlatte());
        mp3CheckBox.setSelected(m.getIsMp3());

    }
    public void clearTextBox() {
        musikGUIDTextField.setText("0");
        musikerTextField.setText("");
        albumTextField.setText("");
        songNameTextField.setText("");
        regalPlatzCDTextField.setText("");
        regalPlatzPlatteTextField.setText("");
        cdListenpreisTextField.setText("0");
        platteListenpreisTextField.setText("0");
        mp3ListenpreisTextField.setText("0");
        cdEinkaufspreisTextField.setText("0");
        platteEinkaufspreisTextField.setText("0");
        mp3EinkaufspreisTextField.setText("0");
        genreComboBox.setText("");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(SaveButton)) {
            parent.getMusikMap().removeMedium(medium);
            medium.setMusiker(musikerTextField.getText());
            medium.setAlbum(albumTextField.getText());
            medium.setSongName(songNameTextField.getText());
            medium.setRegal_PlatzCD(regalPlatzCDTextField.getText());
            medium.setRegal_PlatzPlatte(regalPlatzPlatteTextField.getText().replace(",", "."));
            medium.setCDListenpreis(Double.parseDouble(cdListenpreisTextField.getText().replace(",", ".")));
            medium.setPlatteListenpreis(Double.parseDouble(platteListenpreisTextField.getText().replace(",", ".")));
            medium.setMp3Listenpreis(Double.parseDouble(mp3ListenpreisTextField.getText().replace(",", ".")));
            medium.setCDEinkaufpreis(Double.parseDouble(cdEinkaufspreisTextField.getText().replace(",", ".")));
            medium.setPlatteEinkaufpreis(Double.parseDouble(platteEinkaufspreisTextField.getText().replace(",", ".")));
            medium.setMp3Einkaufpreis(Double.parseDouble(mp3EinkaufspreisTextField.getText().replace(",", ".")));
            medium.setGenre(genreComboBox.getText());
            medium.setIsCD(cdCheckBox.isSelected());
            medium.setIsPlatte(platteCheckBox.isSelected());
            medium.setIsMp3(mp3CheckBox.isSelected());
            parent.updateTableWithMusikListe(parent.getTableModel().geMusikList());
            parent.getMusikMap().addMedium(medium);
            clearTextBox();
            this.setVisible(false);
        }
        if (e.getSource().equals(delteButton)) {
            parent.getMusikMap().removeMedium(medium);
        }
    }
}
