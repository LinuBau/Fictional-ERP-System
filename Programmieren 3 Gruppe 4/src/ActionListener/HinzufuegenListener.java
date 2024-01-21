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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import App_GUI.Gui;
import GeschaftsObejekt.Musik;
import javax.swing.JSpinner;

public class HinzufuegenListener extends JDialog implements ActionListener {

    private JTextField genreTextField;
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
    private JButton beenden;
    private JButton Hinzufuegen;
    public int maxGuid;
    Gui parent;

    public HinzufuegenListener(Gui p) {
        super();
        parent = p;
        NumberFormat format = new DecimalFormat("#.00");
        this.setSize(300, 500);
        genreTextField = new JTextField();
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
        maxGuid= parent.getMusikMap().getMusikList().getMaxGUID();

        this.setLayout(new FlowLayout());
        // Textflied vor all Compents
        JPanel eingabePanel = new JPanel(new GridLayout(19, 2));

        eingabePanel.add(new JLabel(parent.getL10NText("mid") + ": "));
        eingabePanel.add(musikGUIDTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("mbid") + ": "));
        eingabePanel.add(mbidTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("m") + ": "));
        eingabePanel.add(musikerTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("a") + ": "));
        eingabePanel.add(albumTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("sn") + ": "));
        eingabePanel.add(songNameTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("rpcd") + ": "));
        eingabePanel.add(regalPlatzCDTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("rpp") + ": "));
        eingabePanel.add(regalPlatzPlatteTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("lpcd") + ": "));
        eingabePanel.add(cdListenpreisTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("lpp") + ": "));
        eingabePanel.add(platteListenpreisTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("lpmp3") + ": "));
        eingabePanel.add(mp3ListenpreisTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("epcd") + ": "));
        eingabePanel.add(cdEinkaufspreisTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("epp") + ": "));
        eingabePanel.add(platteEinkaufspreisTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("epmp3") + ": "));
        eingabePanel.add(mp3EinkaufspreisTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("CdCount") + ": "));
        eingabePanel.add(cdCountSpinner);

        eingabePanel.add(new JLabel(parent.getL10NText("VinylCount") + ": "));
        eingabePanel.add(vinylCountSpinner);

        eingabePanel.add(new JLabel(parent.getL10NText("g") + ": "));
        eingabePanel.add(genreTextField);

        eingabePanel.add(new JLabel(parent.getL10NText("cd") + ": "));
        eingabePanel.add(cdCheckBox);

        eingabePanel.add(new JLabel(parent.getL10NText("p") + ": "));
        eingabePanel.add(platteCheckBox);

        eingabePanel.add(new JLabel(parent.getL10NText("mp3") + ": "));
        eingabePanel.add(mp3CheckBox);
        fillTextBox();

        Hinzufuegen = new JButton(parent.getL10NText("add"));
        beenden = new JButton(parent.getL10NText("end"));
        JPanel hinzufuegenPanel = new JPanel(new FlowLayout());
        hinzufuegenPanel.add(Hinzufuegen);
        hinzufuegenPanel.add(beenden);
        beenden.addActionListener(this);
        Hinzufuegen.addActionListener(this);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(eingabePanel, BorderLayout.NORTH);
        this.getContentPane().add(hinzufuegenPanel, BorderLayout.CENTER);
        musikGUIDTextField.setEnabled(false);
        cdCheckBox.setEnabled(false);
        platteCheckBox.setEnabled(false);

        this.setModal(true);
        this.setVisible(false);

    }

    public void clearTextBox() {
        musikGUIDTextField.setText(String.valueOf(maxGuid++));
        mbidTextField.setText("");
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
        genreTextField.setText("");
        cdCountSpinner.setValue(0);
        vinylCountSpinner.setValue(0);
    }

    public void fillTextBox() {

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
        genreTextField.setText("");
        cdCountSpinner.setValue(0);
        vinylCountSpinner.setValue(0);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(true);
        clearTextBox();
        if (e.getSource().equals(beenden)) {
            this.setVisible(false);
        }
        if (e.getSource().equals(Hinzufuegen)) {
            boolean visibility = musikHinzufügen();
            this.setVisible(visibility);

        }
    }

    private boolean musikHinzufügen() {
        Musik m = new Musik();
        boolean IdisOk = true;

        m.setMusik_GUID(maxGuid);

        try {
            String mbidText = mbidTextField.getText().trim();
            if (parent.getMusikMap().getMusikList().mbidunique(mbidText) && mbidText.length() == 36) {
                m.setMBID(mbidText);
            } else {
                IdisOk = true;
                JOptionPane.showMessageDialog(this, "ich bin asdguia", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, parent.getL10NText("idError"), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        m.setGenre(genreTextField.getText());
        m.setIsCD(cdCheckBox.isSelected());
        m.setIsPlatte(platteCheckBox.isSelected());
        m.setIsMp3(mp3CheckBox.isSelected());

        if (!IdisOk) {
            addToMusikList(m);
        }else{
            JOptionPane.showMessageDialog(this, "fuckoff", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return IdisOk;

    }

    public boolean addToMusikList(Musik m) {
        if (parent.getMusikMap().getMusikList().unique(m.getMusik_GUID())) {
            parent.getMusikMap().addMedium(m);
            parent.updateTableWithMusikListe(parent.getMusikMap().getMusikList());
            parent.getMusikMap().logChange("ADD", null, m);
            parent.loadChangeLogsForTable();
            return false;
        } else {
            JOptionPane.showMessageDialog(this, parent.getL10NText("idUsedError"), "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
    }

}
