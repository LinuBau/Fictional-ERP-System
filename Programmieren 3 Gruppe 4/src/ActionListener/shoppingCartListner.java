package ActionListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import App_GUI.Gui;
import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;
import Modele.MusikTableModel;

public class shoppingCartListner extends JDialog implements ActionListener {
    private JTable selectTabele;
    private MusikTableModel tableModel;
    private MusikList musikList;
    private JButton loeschenButton;
    private JButton orderButton;
    private JButton speicherButton;
    private TextField platteTextField;
    private TextField cdTextField;
    private JCheckBox mp3CheckBox;
    private int index;
    private Musik musik;
    private Gui parent;

    public shoppingCartListner(Gui p) {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
        musikList = new MusikList();
        parent = p;
        tableModel = new MusikTableModel(musikList);
        selectTabele = new JTable(tableModel);
        speicherButton = new JButton("Speichern");
        orderButton = new JButton("Bestellen");
        loeschenButton = new JButton("Löschen");
        platteTextField = new TextField();
        cdTextField = new TextField();
        mp3CheckBox = new JCheckBox();
        buttonPanel.add(platteTextField);
        buttonPanel.add(cdTextField);
        buttonPanel.add(mp3CheckBox);
        buttonPanel.add(orderButton);
        buttonPanel.add(speicherButton);
        buttonPanel.add(loeschenButton);

        int[] columnsToHide = { 0, 4, 5, 9, 10, 11, 13, 14, 15 };
        int removeCount = 0;
        for (int i : columnsToHide) {
            selectTabele.removeColumn(selectTabele.getColumnModel().getColumn(i - removeCount));
            removeCount++;
        }
        fillMusikListFromProfil();

        loeschenButton.addActionListener(this);
        speicherButton.addActionListener(this);

        selectTabele.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                Point point = mouseEvent.getPoint();
                index = selectTabele.rowAtPoint(point);
                fillTextField(index);
            }
        });
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        this.getContentPane().add(new JScrollPane(selectTabele), BorderLayout.CENTER);
        this.setSize(1000, 1000);
        this.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
        this.setLocationRelativeTo(parent);

        this.setVisible(false);
    }

    private void fillTextField(int indexl) {
        int indexofProfil = parent.getProfilList().getIndexofLogin();
        String StringPlatte = parent.getProfilList().get(indexofProfil).getPallteStückZahlList().get(indexl).toString();
        String StringCD = parent.getProfilList().get(indexofProfil).getCdStückZahlList().get(indexl).toString();
        boolean isMp3 = parent.getProfilList().get(indexofProfil).getMp3Gekauft().get(indexl);
        platteTextField.setEnabled(musikList.get(index).getIsPlatte());
        cdTextField.setEnabled(musikList.get(index).getIsCD());
        mp3CheckBox.setEnabled(musikList.get(index).getIsMp3());
        platteTextField.setText(StringPlatte);
        cdTextField.setText(StringCD);
        mp3CheckBox.setSelected(isMp3);
    }


    public void updateTableWithMusikListe(MusikList musiklist) {
        this.tableModel.setMusikList(musiklist);
        this.tableModel.fireTableDataChanged();
    }

    public void setMusik(Musik m) {
        this.musik = m;
    }

    public Musik getMusik() {
        return this.musik;
    }

    public boolean isInMusikList() {
        return (musikList.unique(musik.getMusik_GUID()));
    }

    public void add() {
        musikList.add(musik);
        updateTableWithMusikListe(musikList);
    }

    private void saveData() {
        int indexofLogin = parent.getProfilList().getIndexofLogin();
        parent.getProfilList().get(indexofLogin).replacetoArrayList(index,
                Integer.parseInt(platteTextField.getText().trim()),
                Integer.parseInt(cdTextField.getText().trim()), mp3CheckBox.isSelected());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(true);
        if (e.getSource().equals(loeschenButton)) {
            removeFromMusikList();
        }
        if(e.getSource().equals(speicherButton)){
            saveData();
        }
    }

    private void removeFromMusikList() {
        if (!(index == -1)) {
            musikList.remove(index);
            int indexOfLogin = parent.getProfilList().getIndexofLogin();
            parent.getProfilList().get(indexOfLogin).removeArray(index);
            updateTableWithMusikListe(musikList);
            index = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Objekt muss ausgewählt sein", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillMusikListFromProfil() {
        int indexOfLogin = parent.getProfilList().getIndexofLogin();
        ArrayList<Integer> IdList = parent.getProfilList().get(indexOfLogin).getMusikId();
        int size = IdList.size();
        int indexInParentMusikList = -1;
        for (int i = 0; i < size; i++) {
            if (!(parent.getMusikList().unique(IdList.get(i)))) {
                indexInParentMusikList = parent.getMusikList().getIndex(IdList.get(i));
                musikList.add(parent.getMusikList().get(indexInParentMusikList));
                updateTableWithMusikListe(musikList);
            } else {
                JOptionPane.showMessageDialog(this, "Musik mit der Id " + IdList.get(i) + " nicht vorhanden.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String JFileChooserPDF() {
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int returnval = chooser.showSaveDialog(parent);
        if (returnval == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            if (!path.endsWith(".pdf")) {
                path = path + ".pdf";
                return path;
            }
        }
        return null;
    }

}
