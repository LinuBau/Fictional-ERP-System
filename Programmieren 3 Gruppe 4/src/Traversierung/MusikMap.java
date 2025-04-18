package Traversierung;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.HashMap;

import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;
import SaveData_ReadData.ChangeLogCsvDAO;
import GeschaftsObejekt.ChangeLogEntry;
import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse MusikMap dient der Verwaltung einer Sammlung von Musik-Objekten.
 * Sie stellt verschiedene Hashmaps zur Verfügung, um Musikobjekte nach verschiedenen Attributen
 * zu kategorisieren, was effiziente Abfragen und Operationen ermöglicht.
 * Zusätzlich wird ein Änderungsprotokoll geführt, um alle Änderungen an der Sammlung nachvollziehen zu können.
 */

public class MusikMap {

    private final MusikList musikList;
    
    // Die folgenden Maps dienen der Indizierung von Musikobjekten nach ihren Eigenschaften.
    // Dies ermöglicht einen schnellen Zugriff auf die Objekte basierend auf Attributen wie Titel, Künstler usw.    
    private HashMap<String, List<Musik>> titleMap;
    private HashMap<String, List<Musik>> artistMap;
    private HashMap<String, List<Musik>> genreMap;
    private HashMap<String, List<Musik>> albumMap;
    private HashMap<String, List<Musik>> mediumTypMap;
    private HashMap<Double, List<Musik>> cdListenpreisMap;
    private HashMap<Double, List<Musik>> platteListenpreisMap;
    private HashMap<Double, List<Musik>> mp3ListenpreisMap;
    
    // Set für das Änderungsprotokoll, um alle Modifikationen zu erfassen.
    private Set<ChangeLogEntry> changeLog = new HashSet<>();

    public MusikMap(MusikList musikList) {
        this.musikList = musikList; // Verwendet die übergebene MusikList-Instanz
        initializeMaps();
    }

    private void initializeMaps() {
        titleMap = new HashMap<>();
        artistMap = new HashMap<>();
        genreMap = new HashMap<>();
        albumMap = new HashMap<>();
        mediumTypMap = new HashMap<>();
        cdListenpreisMap = new HashMap<>();
        platteListenpreisMap = new HashMap<>();
        mp3ListenpreisMap = new HashMap<>();

        for (Musik medium : musikList) {
            updateAllMaps(medium);
        }
    }

    public void updateMedienListe(List<Musik> neueMedienListe) {
        for (Musik medium : neueMedienListe) {
            addMedium(medium);
        }
    }

    public void addMedium(Musik neuesMedium) {
        musikList.add(neuesMedium);
        updateAllMaps(neuesMedium);

    }

    public void removeMedium(Musik zuEntfernendesMedium) {
        musikList.remove(zuEntfernendesMedium);
        removeFromAllMaps(zuEntfernendesMedium);
    }

    private void removeMediumFromMap(HashMap<String, List<Musik>> map, String key, Musik medium) {
        List<Musik> medien = map.get(key);
        if (medien != null) {
            medien.remove(medium);
            if (medien.isEmpty()) {
                map.remove(key);
            }
        }
    }

    private void removeFromAllMaps(Musik medium) {
        removeMediumFromMap(artistMap, medium.getMusiker(), medium);
        removeMediumFromMap(genreMap, medium.getGenre(), medium);
        removeMediumFromMap(albumMap, medium.getAlbum(), medium);
        removeMediumFromMap(titleMap, medium.getSongName(), medium);
        removeMediumFromMediumTypMap(medium);
        removeMediumFromPreisMap(cdListenpreisMap, medium.getCDListenpreis(), medium);
        removeMediumFromPreisMap(platteListenpreisMap, medium.getPlatteListenpreis(), medium);
        removeMediumFromPreisMap(mp3ListenpreisMap, medium.getMp3Listenpreis(), medium);
    }

    private void removeMediumFromMediumTypMap(Musik medium) {
        if (medium.getIsCD()) {
            removeMediumFromMap(mediumTypMap, "CD", medium);
        }
        if (medium.getIsPlatte()) {
            removeMediumFromMap(mediumTypMap, "Platte", medium);
        }
        if (medium.getIsMp3()) {
            removeMediumFromMap(mediumTypMap, "MP3", medium);
        }
    }

    private void removeMediumFromPreisMap(HashMap<Double, List<Musik>> map, Double key, Musik medium) {
        List<Musik> medienListe = map.get(key);
        if (medienListe != null) {
            medienListe.remove(medium);
            if (medienListe.isEmpty()) {
                map.remove(key);
            }
        }
    }

    private void addToMap(HashMap<String, List<Musik>> map, String key, Musik value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    private void addToMap(HashMap<Double, List<Musik>> map, Double key, Musik value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    private void updateAllMaps(Musik medium) {
        addToMap(artistMap, medium.getMusiker(), medium);
        addToMap(genreMap, medium.getGenre(), medium);
        addToMap(albumMap, medium.getAlbum(), medium);
        addToMap(titleMap, medium.getSongName(), medium);
        addToMediumTypMap(medium);
        addToMap(cdListenpreisMap, medium.getCDListenpreis(), medium);
        addToMap(platteListenpreisMap, medium.getPlatteListenpreis(), medium);
        addToMap(mp3ListenpreisMap, medium.getMp3Listenpreis(), medium);
    }

    private void addToMediumTypMap(Musik medium) {
        if (medium.getIsCD()) {
            addToMap(mediumTypMap, "CD", medium);
        }
        if (medium.getIsPlatte()) {
            addToMap(mediumTypMap, "Platte", medium);
        }
        if (medium.getIsMp3()) {
            addToMap(mediumTypMap, "MP3", medium);
        }
    }

    public List<Musik> sortMusikListBySongName(List<Musik> unsortedList) {
        TreeMap<String, Musik> sortedMap = new TreeMap<>();
        for (Musik m : unsortedList) {
            sortedMap.put(m.getSongName(), m);
        }
        return new ArrayList<>(sortedMap.values());
    }

    public MusikList getMusikList() {
        return this.musikList;
    }

    public void replaceMedium(Musik altesmedium, Musik neuesMedium) {
        musikList.replaceMusik(neuesMedium, musikList);
        replaceFromAllMaps(altesmedium, neuesMedium);
    }

    private void replaceFromAllMaps(Musik altesMedium, Musik neuesMedium) {
        replaceMediumFromMap(artistMap, altesMedium.getMusiker(), altesMedium, neuesMedium);
        replaceMediumFromMap(genreMap, altesMedium.getGenre(), altesMedium, neuesMedium);
        replaceMediumFromMap(albumMap, altesMedium.getAlbum(), altesMedium, neuesMedium);
        replaceMediumFromMap(titleMap, altesMedium.getSongName(), altesMedium, neuesMedium);
        replaceMediumInMediumTypMap(altesMedium, neuesMedium);
        replaceMediumInPreisMap(cdListenpreisMap, altesMedium.getCDListenpreis(), altesMedium, neuesMedium);
        replaceMediumInPreisMap(platteListenpreisMap, altesMedium.getPlatteListenpreis(), altesMedium, neuesMedium);
        replaceMediumInPreisMap(mp3ListenpreisMap, altesMedium.getMp3Listenpreis(), altesMedium, neuesMedium);
    }

    private void replaceMediumInMediumTypMap(Musik altesMedium, Musik neuesMedium) {
        if (altesMedium.getIsCD()) {
            replaceMediumFromMap(mediumTypMap, "CD", altesMedium, neuesMedium);
        }
        if (altesMedium.getIsPlatte()) {
            replaceMediumFromMap(mediumTypMap, "Platte", altesMedium, neuesMedium);
        }
        if (altesMedium.getIsMp3()) {
            replaceMediumFromMap(mediumTypMap, "MP3", altesMedium, neuesMedium);
        }
    }

    private void replaceMediumInPreisMap(HashMap<Double, List<Musik>> map, Double preis, Musik altesMedium, Musik neuesMedium) {
        List<Musik> medienListe = map.get(preis);
        if (medienListe != null) {
            int index = medienListe.indexOf(altesMedium);
            if (index != -1) {
                medienListe.set(index, neuesMedium);
            }
        }
    }

    private void replaceMediumFromMap(HashMap<String, List<Musik>> map, String key, Musik medium, Musik newmedium) {
        List<Musik> medien = map.get(key);
        if (medien != null) {
            int index = medien.lastIndexOf(medium);
            if (index != -1) {
                medien.set(index, newmedium);
            }
        }
    }

    public HashMap<String, List<Musik>> getGenreMap() {
        return genreMap;
    }

    public HashMap<String, List<Musik>> getAlbumMap() {
        return albumMap;
    }

    public HashMap<String, List<Musik>> getArtistMap() {
        return artistMap;
    }

    public HashMap<String, List<Musik>> getTitleMap() {
        return titleMap;
    }

    public void logChange(String action, Musik original, Musik updated) {
        ChangeLogEntry logEntry = new ChangeLogEntry(action, original, updated);
        changeLog.add(logEntry);
        System.out.println("Change Log: Action=" + action + ", Old Musik=" + (original != null ? original.toString() : "null") + ", New Musik=" + (updated != null ? updated.toString() : "null"));

    }

    public List<ChangeLogEntry> getChangeLogs() {
        return new ArrayList<>(changeLog);
    }

    public void loadChangeLogsFromCsv() {
        ChangeLogCsvDAO changeLogCsvDOA = new ChangeLogCsvDAO();
        List<ChangeLogEntry> loadedLogs = changeLogCsvDOA.read();
        changeLog.addAll(loadedLogs);
    }

    public void saveChangeLogsToCsv() {
        ChangeLogCsvDAO changeLogCsvDOA = new ChangeLogCsvDAO();
        changeLogCsvDOA.write(new ArrayList<>(changeLog));
    }

    public void setChangeLogs(List<ChangeLogEntry> changeLogList) {
        this.changeLog = new HashSet<>(changeLogList);
    }

}
