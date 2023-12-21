package Traversierung;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.stream.Collectors;

import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;

public class MusikMap {

    private MusikList musikList;
    private TreeMap<String, List<Musik>> songNameMap;
    private TreeMap<String, List<Musik>> musikerMap;
    private TreeMap<String, List<Musik>> genreMap;
    private TreeMap<String, List<Musik>> albumMap;
    private TreeMap<String, List<Musik>> mediumTypMap;
    private TreeMap<Double, List<Musik>> cdListenpreisMap;
    private TreeMap<Double, List<Musik>> platteListenpreisMap;
    private TreeMap<Double, List<Musik>> mp3ListenpreisMap;

    public MusikMap(MusikList musikList) {
        this.musikList = musikList; // Verwendet die übergebene MusikList-Instanz
        initializeMaps();
    }

    private void initializeMaps() {
        songNameMap = new TreeMap<>();
        musikerMap = new TreeMap<>();
        genreMap = new TreeMap<>();
        albumMap = new TreeMap<>();
        mediumTypMap = new TreeMap<>();
        cdListenpreisMap = new TreeMap<>();
        platteListenpreisMap = new TreeMap<>();
        mp3ListenpreisMap = new TreeMap<>();

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

    private void removeMediumFromMap(TreeMap<String, List<Musik>> map, String key, Musik medium) {
        List<Musik> medien = map.get(key);
        if (medien != null) {
            medien.remove(medium);
            if (medien.isEmpty()) {
                map.remove(key);
            }
        }
    }

    private void removeFromAllMaps(Musik medium) {
        removeMediumFromMap(musikerMap, medium.getMusiker(), medium);
        removeMediumFromMap(genreMap, medium.getGenre(), medium);
        removeMediumFromMap(albumMap, medium.getAlbum(), medium);
        removeMediumFromMap(songNameMap, medium.getSongName(), medium);

        // Entfernen von Mediumtypen, wenn zutreffend
        if (medium.getIsCD()) {
            removeMediumFromMap(mediumTypMap, "CD", medium);
        }
        if (medium.getIsPlatte()) {
            removeMediumFromMap(mediumTypMap, "Platte", medium);
        }
        if (medium.getIsMp3()) {
            removeMediumFromMap(mediumTypMap, "MP3", medium);
        }

        removeMediumFromMap(cdListenpreisMap, medium.getCDListenpreis(), medium);
        removeMediumFromMap(platteListenpreisMap, medium.getPlatteListenpreis(), medium);
        removeMediumFromMap(mp3ListenpreisMap, medium.getMp3Listenpreis(), medium);
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

    private void removeMediumFromMap(TreeMap<Double, List<Musik>> map, Double key, Musik medium) {
        List<Musik> medienListe = map.get(key);
        if (medienListe != null) {
            medienListe.remove(medium);
            if (medienListe.isEmpty()) {
                map.remove(key);
            }
        }
    }

    public void addToMap(TreeMap<String, List<Musik>> map, String key, Musik value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    private void updateAllMaps(Musik medium) {
        addToMap(musikerMap, medium.getMusiker(), medium);
        addToMap(genreMap, medium.getGenre(), medium);
        addToMap(albumMap, medium.getAlbum(), medium);
        addToMap(songNameMap, medium.getSongName(), medium);
    }
    
    public void addToMap(HashMap<String, List<Musik>> map, String key, Musik value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    private void addToMap(TreeMap<Double, List<Musik>> map, Double key, Musik value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }
    

public List<Musik> getDefaultOrAllMedien() {
        return new ArrayList<>(musikList);
    }

    public List<Musik> getGesamteMusikListe() {
        return new ArrayList<>(musikList);
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

 public void replaceMedium(Musik altesmedium,Musik neuesMedium) {
        int index = musikList.getIndex(altesmedium.getMusik_GUID(), musikList);
        musikList.set(index, neuesMedium);
        replaceFromAllMaps(altesmedium,neuesMedium);
    }

     private void replaceFromAllMaps(Musik medium, Musik newmedium) {
        replaceMediumFromMap(musikerMap, medium.getMusiker(), medium,newmedium);
        replaceMediumFromMap(genreMap, medium.getGenre(), medium,newmedium);
        replaceMediumFromMap(albumMap, medium.getAlbum(), medium,newmedium);
        replaceMediumFromMap(songNameMap, medium.getSongName(), medium,newmedium);

        // Entfernen von Mediumtypen, wenn zutreffend
        if (medium.getIsCD()) {
            replaceMediumFromMap(mediumTypMap, "CD", medium,newmedium);
        }
        if (medium.getIsPlatte()) {
            replaceMediumFromMap(mediumTypMap, "Platte", medium,newmedium);
        }
        if (medium.getIsMp3()) {
            replaceMediumFromMap(mediumTypMap, "MP3", medium,newmedium);
        }

        replaceMediumFromMap(cdListenpreisMap, medium.getCDListenpreis(), medium,newmedium);
        replaceMediumFromMap(platteListenpreisMap, medium.getPlatteListenpreis(), medium,newmedium);
        replaceMediumFromMap(mp3ListenpreisMap, medium.getMp3Listenpreis(), medium,newmedium);
    }

    private void replaceMediumFromMap(TreeMap<String, List<Musik>> map, String key, Musik medium, Musik newmedium) {
        List<Musik> medien = map.get(key);
        if (medien != null) {
            int index = medien.lastIndexOf(medium);
            if (index != -1) {
                medien.set(index, newmedium);
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
     private void replaceMediumFromMap(TreeMap<Double, List<Musik>> map, Double key, Musik medium,Musik newmedium) {
        List<Musik> medienListe = map.get(key);
        if (medienListe != null) {
           int index = medienListe.lastIndexOf(medium);
            if (index != -1) {
                medienListe.set(index, newmedium);
            }
        }
    }

}