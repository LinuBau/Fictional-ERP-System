package Modele;

import Traversierung.ChangeLogEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ChangeLogTableModel extends AbstractTableModel {

    private final String[] columnNames;
    private List<ChangeLogEntry> changeLogs;

    public ChangeLogTableModel(String[] columnNames) {
        changeLogs = new ArrayList<>();
        this.columnNames = columnNames;
    }

    public void setChangeLogs(List<ChangeLogEntry> changeLogs) {
        this.changeLogs = changeLogs;
        sortChangeLogs();
    }

    public void addChangeLog(ChangeLogEntry entry) {
        changeLogs.add(entry);
    }

    public List<ChangeLogEntry> getChangeLogs() {
        return changeLogs;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return changeLogs.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ChangeLogEntry entry = changeLogs.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return entry.getTimestamp();
            case 1:
                return entry.getAction();
            case 2:
                return entry.getNewState();
            case 3:
                return entry.getOriginalState();
            default:
                return null;
        }
    }

    public void sortChangeLogs() {
        Collections.sort(changeLogs, new Comparator<ChangeLogEntry>() {
            @Override
            public int compare(ChangeLogEntry entry1, ChangeLogEntry entry2) {
                return entry1.getTimestamp().compareTo(entry2.getTimestamp());
            }
        });
        fireTableDataChanged();
    }
}
