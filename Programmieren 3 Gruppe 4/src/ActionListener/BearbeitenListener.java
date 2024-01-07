package ActionListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import App_GUI.Gui;
import GeschaftsObejekt.Musik;

public class BearbeitenListener implements ActionListener {
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
    private JButton reinhörenButton;
    private ReinhörenListener reinhörenListener;
    Gui parent;
    private Musik medium;

    public BearbeitenListener(Gui p) {
        super();
        parent = p;
    }

    public void setMusik(Musik m) {
        medium = m;
        reinhörenListener.setMedium(m);
    }

    public JPanel getBearbeitenPanel() {
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

        reinhörenButton = new JButton("Reinhören");
        reinhörenListener = new ReinhörenListener();

        musikGUIDTextField.setEditable(false);

        JPanel centerPanel = new JPanel(new GridLayout(13, 2));
        JPanel southPanel = new JPanel(new BorderLayout());

        JPanel southcenterPanel = new JPanel(new GridLayout(1, 3));
        JPanel southsouthPanel = new JPanel(new GridLayout(1, 3));
        // Textflied vor all Compents
        eingabePanel = new JPanel(new BorderLayout());

        centerPanel.add(new JLabel("Musik_GUID: "));
        centerPanel.add(musikGUIDTextField);

        centerPanel.add(new JLabel("Musiker: "));
        centerPanel.add(musikerTextField);

        centerPanel.add(new JLabel("Album: "));
        centerPanel.add(albumTextField);

        centerPanel.add(new JLabel("Song Name: "));
        centerPanel.add(songNameTextField);

        centerPanel.add(new JLabel("Regal Platz CD: "));
        centerPanel.add(regalPlatzCDTextField);

        centerPanel.add(new JLabel("Regal Platz Platte: "));
        centerPanel.add(regalPlatzPlatteTextField);

        centerPanel.add(new JLabel("Listenpreis CD: "));
        centerPanel.add(cdListenpreisTextField);

        centerPanel.add(new JLabel("Listenpreis Platte: "));
        centerPanel.add(platteListenpreisTextField);

        centerPanel.add(new JLabel("Listenpreis MP3: "));
        centerPanel.add(mp3ListenpreisTextField);

        centerPanel.add(new JLabel("Einkaufspreis CD: "));
        centerPanel.add(cdEinkaufspreisTextField);

        centerPanel.add(new JLabel("Einkaufspreis Platte: "));
        centerPanel.add(platteEinkaufspreisTextField);

        centerPanel.add(new JLabel("Einkaufspreis MP3: "));
        centerPanel.add(mp3EinkaufspreisTextField);

        centerPanel.add(new JLabel("Genre: "));
        centerPanel.add(genreComboBox);

        southcenterPanel.add(new JLabel("CD: "));
        southcenterPanel.add(cdCheckBox);

        southcenterPanel.add(new JLabel("Platte: "));
        southcenterPanel.add(platteCheckBox);

        southcenterPanel.add(new JLabel("MP3: "));
        southcenterPanel.add(mp3CheckBox);
        southsouthPanel.add(SaveButton);
        if (parent.starten) {
            southsouthPanel.add(delteButton);
        }

        southsouthPanel.add(reinhörenButton);
        eingabePanel.add(centerPanel, BorderLayout.CENTER);
        southPanel.add(southcenterPanel, BorderLayout.CENTER);
        southPanel.add(southsouthPanel, BorderLayout.SOUTH);
        eingabePanel.add(southPanel, BorderLayout.SOUTH);
        SaveButton.addActionListener(this);
        delteButton.addActionListener(this);
        reinhörenButton.addActionListener(reinhörenListener);

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
        cdCheckBox.setSelected(false);
        platteCheckBox.setSelected(false);
        mp3CheckBox.setSelected(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(SaveButton)) {
            if (parent.starten) {
             bearbeitenButton();
            } 
        }
        if (e.getSource().equals(delteButton)) {
            parent.getMusikMap().removeMedium(medium);
            clearTextBox();
            parent.update(parent.getGraphics());
        }
    }

    private void bearbeitenButton() {
        Musik m = new Musik();
        m.setMusik_GUID(medium.getMusik_GUID());
        m.setMusiker(musikerTextField.getText());
        m.setAlbum(albumTextField.getText());
        m.setSongName(songNameTextField.getText());
        m.setRegal_PlatzCD(regalPlatzCDTextField.getText());
        m.setRegal_PlatzPlatte(regalPlatzPlatteTextField.getText().replace(",", "."));
        m.setCDListenpreis(Double.parseDouble(cdListenpreisTextField.getText().replace(",", ".")));
        m.setPlatteListenpreis(Double.parseDouble(platteListenpreisTextField.getText().replace(",", ".")));
        m.setMp3Listenpreis(Double.parseDouble(mp3ListenpreisTextField.getText().replace(",", ".")));
        m.setCDEinkaufpreis(Double.parseDouble(cdEinkaufspreisTextField.getText().replace(",", ".")));
        m.setPlatteEinkaufpreis(Double.parseDouble(platteEinkaufspreisTextField.getText().replace(",", ".")));
        m.setMp3Einkaufpreis(Double.parseDouble(mp3EinkaufspreisTextField.getText().replace(",", ".")));
        m.setGenre(genreComboBox.getText());
        m.setIsCD(cdCheckBox.isSelected());
        m.setIsPlatte(platteCheckBox.isSelected());
        m.setIsMp3(mp3CheckBox.isSelected());
        parent.getTableModel().getMusikList().replaceMusik(m, parent.getTableModel().getMusikList());
        parent.updateTableWithMusikListe(parent.getTableModel().getMusikList());
        parent.getMusikMap().replaceMedium(medium, m);

    }
}
