package Modele;

import javax.swing.table.AbstractTableModel;

import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;

public class MusikTableModel extends AbstractTableModel {

    private MusikList musikList;

    private final String[] columnNames = {
            "MusikID",
            "Künstler",
            "AlbumTitel",
            "SongTitel",
            "RegalPlatzCD",
            "RegalPlatzSchallplatte",
            "CDVerkaufspreis",
            "SchallplatteVerkaufspreis",
            "Mp3Verkaufspreis",
            "CDEinkaufspreis",
            "SchallplatteEinkaufspreis",
            "Mp3Einkaufspreis",
            "Genre",
            "CD",
            "Schallplatte",
            "Mp3"
    };

    private final Class<?>[] columnClass = new Class[] {
            Integer.class, String.class, String.class, String.class, String.class,
            String.class, Double.class, Double.class, Double.class, Double.class,
            Double.class, Double.class, String.class, Boolean.class, Boolean.class, Boolean.class
    };

    public MusikTableModel(MusikList musikList) {
        this.musikList = musikList;
    }

    @Override
    public int getRowCount() {
        return this.musikList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Musik row = musikList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return row.getMusik_GUID();
            case 1:
                return row.getMusiker();
            case 2:
                return row.getAlbum();
            case 3:
                return row.getSongName();
            case 4:
                return row.getRegal_PlatzCD();
            case 5:
                return row.getRegal_PlatzPlatte();
            case 6:
                return row.getCDListenpreis();
            case 7:
                return row.getPlatteListenpreis();
            case 8:
                return row.getMp3Listenpreis();
            case 9:
                return row.getCDEinkaufpreis();
            case 10:
                return row.getPlatteEinkaufpreis();
            case 11:
                return row.getMp3Einkaufpreis();
            case 12:
                return row.getGenre();
            case 13:
                return row.getIsCD();
            case 14:
                return row.getIsPlatte();
            case 15:
                return row.getIsMp3();
            default:
                return null;
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Musik row = musikList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                row.setMusik_GUID((Integer) aValue);
                break;
            case 1:
                row.setMusiker((String) aValue);
                break;
            case 2:
                row.setAlbum((String) aValue);
                break;
            case 3:
                row.setSongName((String) aValue);
                break;
            case 4:
                row.setRegal_PlatzCD((String) aValue);
                break;
            case 5:
                row.setRegal_PlatzPlatte((String) aValue);
                break;
            case 6:
                row.setCDListenpreis((Double) aValue);
                break;
            case 7:
                row.setPlatteListenpreis((Double) aValue);
                break;
            case 8:
                row.setMp3Listenpreis((Double) aValue);
                break;
            case 9:
                row.setCDEinkaufpreis((Double) aValue);
                break;
            case 10:
                row.setPlatteEinkaufpreis((Double) aValue);
                break;
            case 11:
                row.setMp3Einkaufpreis((Double) aValue);
                break;
            case 12:
                row.setGenre((String) aValue);
                break;
            case 13:
                row.setIsCD((Boolean) aValue);
                break;
            case 14:
                row.setIsPlatte((Boolean) aValue);
                break;
            case 15:
                row.setIsMp3((Boolean) aValue);
                break;
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
       // Methode zum Aktualisieren der MusikList
    public void setMusikList(MusikList neueMusikList) {
        this.musikList = neueMusikList;
    }
}
