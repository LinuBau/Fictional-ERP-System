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
import javax.swing.JSpinner;

public class BearbeitenListener implements ActionListener {
    public JPanel eingabePanel;
    private JTextField genreComboBox;
    private JTextField musikGUIDTextField;
    private JTextField mbidTextField;
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
    private JSpinner cdCountSpinner;
    private JSpinner vinylCountSpinner;
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
        this.parent = p;
    }

    public void setMusik(Musik m) {
        medium = m;
        reinhörenListener.setMedium(m);
    }

    public JPanel getMitarbeiterBearbeitenPanel() {
        NumberFormat format = new DecimalFormat("#.00");
        genreComboBox = new JTextField();
        musikGUIDTextField = new JTextField();
        mbidTextField = new JTextField();
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
        cdCountSpinner = new JSpinner();
        vinylCountSpinner = new JSpinner();
        cdCheckBox = new JCheckBox();
        platteCheckBox = new JCheckBox();
        mp3CheckBox = new JCheckBox();
        delteButton = new JButton(parent.getL10NText("delete"));
        SaveButton = new JButton(parent.getL10NText("save"));
        reinhörenButton = new JButton(parent.getL10NText("hear"));
        reinhörenListener = new ReinhörenListener();

        musikGUIDTextField.setEditable(false);
        mbidTextField.setEditable(false);

        JPanel centerPanel = new JPanel(new GridLayout(16, 2));
        JPanel southPanel = new JPanel(new BorderLayout());

        JPanel southcenterPanel = new JPanel(new GridLayout(1, 3));
        JPanel southsouthPanel = new JPanel(new GridLayout(1, 3));
        // Textflied vor all Compents
        eingabePanel = new JPanel(new BorderLayout());

        centerPanel.add(new JLabel(parent.getL10NText("mid")+": "));
        centerPanel.add(musikGUIDTextField);

        centerPanel.add(new JLabel(parent.getL10NText("mbid")+": "));
        centerPanel.add(mbidTextField);

        centerPanel.add(new JLabel(parent.getL10NText("m")+":" ));
        centerPanel.add(musikerTextField);

        centerPanel.add(new JLabel(parent.getL10NText("a")+": "));
        centerPanel.add(albumTextField);

        centerPanel.add(new JLabel(parent.getL10NText("sn")+": "));
        centerPanel.add(songNameTextField);

        centerPanel.add(new JLabel(parent.getL10NText("rpcd")+": "));
        centerPanel.add(regalPlatzCDTextField);

        centerPanel.add(new JLabel(parent.getL10NText("rpp")+": "));
        centerPanel.add(regalPlatzPlatteTextField);

        centerPanel.add(new JLabel(parent.getL10NText("lpcd")+": "));
        centerPanel.add(cdListenpreisTextField);

        centerPanel.add(new JLabel(parent.getL10NText("lpp")+": "));
        centerPanel.add(platteListenpreisTextField);

        centerPanel.add(new JLabel(parent.getL10NText("lpmp3")+": "));
        centerPanel.add(mp3ListenpreisTextField);

        centerPanel.add(new JLabel(parent.getL10NText("epcd")+": "));
        centerPanel.add(cdEinkaufspreisTextField);

        centerPanel.add(new JLabel(parent.getL10NText("epp")+": "));
        centerPanel.add(platteEinkaufspreisTextField);

        centerPanel.add(new JLabel(parent.getL10NText("epmp3")+": "));
        centerPanel.add(mp3EinkaufspreisTextField);

        centerPanel.add(new JLabel(parent.getL10NText("g")+": "));
        centerPanel.add(genreComboBox);
        
        centerPanel.add(new JLabel("CdCount: "));
        centerPanel.add(cdCountSpinner);
        
        centerPanel.add(new JLabel("VinylCount: "));
        centerPanel.add(vinylCountSpinner);

        southcenterPanel.add(new JLabel(parent.getL10NText("cd")+": "));
        southcenterPanel.add(cdCheckBox);

        southcenterPanel.add(new JLabel(parent.getL10NText("sp")+": "));
        southcenterPanel.add(platteCheckBox);

        southcenterPanel.add(new JLabel(parent.getL10NText("mp3")+": "));
        southcenterPanel.add(mp3CheckBox);

        southsouthPanel.add(SaveButton);
        southsouthPanel.add(delteButton);

        southsouthPanel.add(reinhörenButton);
        eingabePanel.add(centerPanel, BorderLayout.CENTER);
        southPanel.add(southcenterPanel, BorderLayout.CENTER);
        southPanel.add(southsouthPanel, BorderLayout.SOUTH);
        eingabePanel.add(southPanel, BorderLayout.SOUTH);
        // add ActionListner
        SaveButton.addActionListener(this);
        delteButton.addActionListener(this);
        reinhörenButton.addActionListener(reinhörenListener);

        setMitarbeiterEnable(true);

        return eingabePanel;
    }

    public JPanel getUsserBearbeitenPanel() {
        NumberFormat format = new DecimalFormat("#.00");
        genreComboBox = new JTextField();
        musikGUIDTextField = new JTextField();
        musikerTextField = new JTextField();
        albumTextField = new JTextField();
        songNameTextField = new JTextField();
        regalPlatzCDTextField = new JTextField();
        regalPlatzPlatteTextField = new JTextField();
        cdEinkaufspreisTextField = new JFormattedTextField(format);
        platteEinkaufspreisTextField = new JFormattedTextField(format);
        mp3EinkaufspreisTextField = new JFormattedTextField(format);
        cdCountSpinner = new JSpinner();
        vinylCountSpinner = new JSpinner();
        cdCheckBox = new JCheckBox();
        platteCheckBox = new JCheckBox();
        mp3CheckBox = new JCheckBox();
        SaveButton = new JButton(parent.getL10NText("wkh"));

        reinhörenButton = new JButton(parent.getL10NText("hear"));
        reinhörenListener = new ReinhörenListener();

        JPanel centerPanel = new JPanel(new GridLayout(10, 2));
        JPanel southPanel = new JPanel(new BorderLayout());

        JPanel southcenterPanel = new JPanel(new GridLayout(1, 3));
        JPanel southsouthPanel = new JPanel(new GridLayout(1, 3));
        // Textflied vor all Compents
        eingabePanel = new JPanel(new BorderLayout());
        
        centerPanel.add(new JLabel(parent.getL10NText("mid")+": "));
        centerPanel.add(musikGUIDTextField);

        centerPanel.add(new JLabel(parent.getL10NText("m")+": "));
        centerPanel.add(musikerTextField);

        centerPanel.add(new JLabel(parent.getL10NText("a")+": "));
        centerPanel.add(albumTextField);

        centerPanel.add(new JLabel(parent.getL10NText("sn")+": "));
        centerPanel.add(songNameTextField);

        centerPanel.add(new JLabel(parent.getL10NText("epcd")+": "));
        centerPanel.add(cdEinkaufspreisTextField);

        centerPanel.add(new JLabel(parent.getL10NText("epp")+": "));
        centerPanel.add(platteEinkaufspreisTextField);

        centerPanel.add(new JLabel(parent.getL10NText("epmp3")+": "));
        centerPanel.add(mp3EinkaufspreisTextField);

        centerPanel.add(new JLabel(parent.getL10NText("g")));
        centerPanel.add(genreComboBox);
        
        centerPanel.add(new JLabel("CdCount: "));
        centerPanel.add(cdCountSpinner);
        
        centerPanel.add(new JLabel("VinylCount: "));
        centerPanel.add(vinylCountSpinner);

        southcenterPanel.add(new JLabel(parent.getL10NText("cd")+": "));
        southcenterPanel.add(cdCheckBox);

        southcenterPanel.add(new JLabel(parent.getL10NText("sp")+": "));
        southcenterPanel.add(platteCheckBox);

        southcenterPanel.add(new JLabel(parent.getL10NText("mp3")+": "));
        southcenterPanel.add(mp3CheckBox);
        // Add Button to Panel
        southsouthPanel.add(SaveButton);
        southsouthPanel.add(reinhörenButton);
        // make add Panel to return Panel
        eingabePanel.add(centerPanel, BorderLayout.CENTER);
        southPanel.add(southcenterPanel, BorderLayout.CENTER);
        southPanel.add(southsouthPanel, BorderLayout.SOUTH);
        eingabePanel.add(southPanel, BorderLayout.SOUTH);
        // Add ActionListner
        SaveButton.addActionListener(new addToShoppingCart(parent));
        reinhörenButton.addActionListener(reinhörenListener);

        // Defauft
        SaveButton.setEnabled(true);
        reinhörenButton.setEnabled(true);
        genreComboBox.setEnabled(false);
        musikGUIDTextField.setEnabled(false);
        musikerTextField.setEnabled(false);
        albumTextField.setEnabled(false);
        songNameTextField.setEnabled(false);
        cdEinkaufspreisTextField.setEnabled(false);
        platteEinkaufspreisTextField.setEnabled(false);
        mp3EinkaufspreisTextField.setEnabled(false);
        cdCountSpinner.setEnabled(false);
        vinylCountSpinner.setEnabled(false);
        cdCheckBox.setEnabled(false);
        platteCheckBox.setEnabled(false);
        mp3CheckBox.setEnabled(false);

        return eingabePanel;
    }

    private void setMitarbeiterEnable(boolean b) {
        genreComboBox.setEnabled(b);
        musikGUIDTextField.setEnabled(b);
        musikerTextField.setEnabled(b);
        albumTextField.setEnabled(b);
        songNameTextField.setEnabled(b);
        regalPlatzCDTextField.setEnabled(b);
        regalPlatzPlatteTextField.setEnabled(b);
        cdListenpreisTextField.setEnabled(b);
        platteListenpreisTextField.setEnabled(b);
        mp3ListenpreisTextField.setEnabled(b);
        cdEinkaufspreisTextField.setEnabled(b);
        platteEinkaufspreisTextField.setEnabled(b);
        mp3EinkaufspreisTextField.setEnabled(b);
        cdCountSpinner.setEnabled(b);
        vinylCountSpinner.setEnabled(b);
        cdCheckBox.setEnabled(b);
        platteCheckBox.setEnabled(b);
        mp3CheckBox.setEnabled(b);
        delteButton.setEnabled(b);
        SaveButton.setEnabled(b);
        reinhörenButton.setEnabled(b);
    }

    public void mitarbeiterFillTextBox(Musik m) {
        musikGUIDTextField.setText(String.valueOf(m.getMusik_GUID()));
        mbidTextField.setText(m.getMBID());
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
        cdCountSpinner.setValue(m.getCdCount());
        vinylCountSpinner.setValue(m.getVinylCount());
        genreComboBox.setText(m.getGenre());
        cdCheckBox.setSelected(m.getIsCD());
        platteCheckBox.setSelected(m.getIsPlatte());
        mp3CheckBox.setSelected(m.getIsMp3());
        setMitarbeiterEnable(true);
    }

    public void benutzerFillTextBox(Musik m) {
        musikGUIDTextField.setText(String.valueOf(m.getMusik_GUID()));
        musikerTextField.setText(m.getMusiker());
        albumTextField.setText(m.getAlbum());
        songNameTextField.setText(m.getSongName());
        regalPlatzCDTextField.setText(m.getRegal_PlatzCD());
        regalPlatzPlatteTextField.setText(m.getRegal_PlatzPlatte());
        cdEinkaufspreisTextField.setText(String.valueOf(m.getCDEinkaufpreis()));
        platteEinkaufspreisTextField.setText(String.valueOf(m.getPlatteEinkaufpreis()));
        mp3EinkaufspreisTextField.setText(String.valueOf(m.getMp3Einkaufpreis()));
        cdCountSpinner.setValue(m.getCdCount());
        vinylCountSpinner.setValue(m.getVinylCount());
        genreComboBox.setText(m.getGenre());
        cdCheckBox.setSelected(m.getIsCD());
        platteCheckBox.setSelected(m.getIsPlatte());
        mp3CheckBox.setSelected(m.getIsMp3());
        SaveButton.setEnabled(true);
        reinhörenButton.setEnabled(true);
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
        cdCountSpinner.setValue(0);
        vinylCountSpinner.setValue(0);        
        genreComboBox.setText("");
        cdCheckBox.setSelected(false);
        platteCheckBox.setSelected(false);
        mp3CheckBox.setSelected(false);
        setMitarbeiterEnable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(SaveButton)) {          
         bearbeitenButton();          
        }
        if (e.getSource().equals(delteButton)) {
            parent.getMusikMap().removeMedium(medium);
            clearTextBox();
            parent.updateTableWithMusikListe(parent.getMusikMap().getMusikList());
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
        m.setCdCount((int) cdCountSpinner.getValue());
        m.setVinylCount((int) vinylCountSpinner.getValue());
        m.setGenre(genreComboBox.getText());
        m.setIsCD(cdCheckBox.isSelected());
        m.setIsPlatte(platteCheckBox.isSelected());
        m.setIsMp3(mp3CheckBox.isSelected());
        parent.getTableModel().getMusikList().replaceMusik(m, parent.getTableModel().getMusikList());
        parent.updateTableWithMusikListe(parent.getTableModel().getMusikList());
        parent.getMusikMap().replaceMedium(medium, m);

    }
}
