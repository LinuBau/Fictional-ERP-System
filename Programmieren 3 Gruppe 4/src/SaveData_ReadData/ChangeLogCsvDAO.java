package SaveData_ReadData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Traversierung.ChangeLogEntry;
import GeschaftsObejekt.Musik;

public class ChangeLogCsvDAO {

    private static final String FILENAME = "Changelogs.csv";
    private static final String SPLIT_CONDITION1 = ",";
    private static final String SPLIT_CONDITION2 = ";";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Methode zum Schreiben eines ChangeLogEntry in die CSV-Datei
    public void write(List<ChangeLogEntry> entries) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            for (ChangeLogEntry entry : entries) {
                String csvLine = entryToCsv(entry);
                writer.write(csvLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Lesen von ChangeLogEntries aus der CSV-Datei
    public List<ChangeLogEntry> read() {
        List<ChangeLogEntry> entries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ChangeLogEntry entry = csvToChangeLogEntry(line);
                entries.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    // Hilfsmethode zum Konvertieren eines ChangeLogEntry in einen CSV-String
    private String entryToCsv(ChangeLogEntry entry) {
        return entry.getTimestamp().format(FORMATTER) + SPLIT_CONDITION1
                + entry.getAction() + SPLIT_CONDITION1
                + musikToCsv(entry.getOriginalState()) + SPLIT_CONDITION1
                + musikToCsv(entry.getNewState());
    }

    // Hilfsmethode zum Konvertieren eines CSV-Strings in ein ChangeLogEntry-Objekt
    private ChangeLogEntry csvToChangeLogEntry(String csvLine) {
        String[] parts = csvLine.split(SPLIT_CONDITION1);
        LocalDateTime timestamp = LocalDateTime.parse(parts[0], FORMATTER);
        String action = parts[1];
        Musik originalState = csvToMusik(parts[2]);
        Musik newState = csvToMusik(parts[3]);

        return new ChangeLogEntry(timestamp, action, originalState, newState);
    }

    // Hilfsmethoden zur Formatierung und zum Parsen von Musik-Objekten
    private String musikToCsv(Musik musik) {
        if (musik == null) {
            return "null";
        }
        return toCsv(SPLIT_CONDITION2, musik);
    }

    private Musik csvToMusik(String csv) {
        if ("null".equals(csv)) {
            return null;
        }
        Musik musik = new Musik();
        csvToMusik_Medium(csv, SPLIT_CONDITION2, musik);
        return musik;
    }

    // Hilfsmethoden zur Formatierung und zum Parsen von Musik-Objekten
    private String toCsv(String splitKondiotn,Musik MM) {
        return  
             MM.getMusik_GUID() + splitKondiotn +
             MM.getMusiker() + splitKondiotn +
             MM.getAlbum() + splitKondiotn +
             MM.getSongName() + splitKondiotn +
             MM.getRegal_PlatzCD() + splitKondiotn +
             MM.getRegal_PlatzPlatte() + splitKondiotn+
             MM.getCDListenpreis() + splitKondiotn +
             MM.getPlatteListenpreis() + splitKondiotn +
             MM.getMp3Listenpreis() + splitKondiotn +
             MM.getCDEinkaufpreis() + splitKondiotn +
             MM.getPlatteEinkaufpreis() + splitKondiotn +
             MM.getMp3Einkaufpreis() + splitKondiotn +
             MM.getGenre() + splitKondiotn +
             MM.isIsMp3() + splitKondiotn +
             MM.getCdCount() + splitKondiotn +
             MM.getVinylCount() + splitKondiotn +
             MM.getMBID()
            ;
    }

    protected void csvToMusik_Medium(String csv, String splitCondition, Musik MM) {
        String[] csvStrings = csv.split(splitCondition);
        int erwarteteAnzahlVonFeldern = 17;
        if (csvStrings.length < erwarteteAnzahlVonFeldern) {
            System.err.println("Ungültige Zeile in CSV: " + csv);
            return; // oder werfen Sie eine Ausnahme
        }
        try {
            MM.setMusik_GUID(Integer.parseInt(csvStrings[0].trim())); // trim() entfernt Leerzeichen
        } catch (NumberFormatException e) {
            System.err.println("Fehler beim Parsen von Musik_GUID: " + csvStrings[0]);
        }

        MM.setMusiker(csvStrings[1]);
        MM.setAlbum(csvStrings[2]);
        MM.setSongName(csvStrings[3]);
        MM.setRegal_PlatzCD(csvStrings[4]);
        MM.setRegal_PlatzPlatte(csvStrings[5]);

        try {
            MM.setCDListenpreis(Double.parseDouble(csvStrings[6]));
            MM.setPlatteListenpreis(Double.parseDouble(csvStrings[7]));
            MM.setMp3Listenpreis(Double.parseDouble(csvStrings[8]));
            MM.setCDEinkaufpreis(Double.parseDouble(csvStrings[9]));
            MM.setPlatteEinkaufpreis(Double.parseDouble(csvStrings[10]));
            MM.setMp3Einkaufpreis(Double.parseDouble(csvStrings[11]));

        } catch (NumberFormatException e) {
            System.err.println("Fehler beim Parsen der Preise: " + e.getMessage());
        }
        MM.setGenre(csvStrings[12]);
        MM.setIsMp3(Boolean.parseBoolean(csvStrings[13]));
        MM.setCdCount(Integer.parseInt(csvStrings[14]));
        MM.setVinylCount(Integer.parseInt(csvStrings[15]));
        MM.setMBID(csvStrings[16]);
    }
}
