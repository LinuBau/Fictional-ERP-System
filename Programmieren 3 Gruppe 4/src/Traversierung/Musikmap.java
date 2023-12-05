package Traversierung;
import DataOutput_DataInput.Musik_MediumListDAO;
import GeschaftsObejekt.Musik_Medium;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.stream.Collectors;
import GeschaftsObejekt.Musik_MediumList;
import java.io.IOException;


public class Musikmap {
    
    private Musik_MediumListDAO musikMediumListDAO;
    private Musik_MediumList musikMediumList;
    private HashMap<String, List<Musik_Medium>> songNameMap;
    private HashMap<String, List<Musik_Medium>> musikerMap;
    private HashMap<String, List<Musik_Medium>> genreMap;
    private HashMap<String, List<Musik_Medium>> albumMap;
    private HashMap<String, List<Musik_Medium>> mediumTypMap;
    private TreeMap<Double, List<Musik_Medium>> cdListenpreisMap;
    private TreeMap<Double, List<Musik_Medium>> platteListenpreisMap;
    private TreeMap<Double, List<Musik_Medium>> mp3ListenpreisMap;

    public Musikmap(String filename) {
    this.musikMediumListDAO = new Musik_MediumListDAO(filename, true);
    this.musikMediumList = new Musik_MediumList(); 
    try {
        musikMediumListDAO.read(this.musikMediumList); // Lesen der Liste aus der Datei
    } catch (IOException e) {
        e.printStackTrace(); // gibt fehlermeldung aus
    }
    initializeMaps();
}


        
  
    private void initializeMaps() {
        songNameMap = new HashMap<>();
        musikerMap = new HashMap<>();
        genreMap = new HashMap<>();
        albumMap = new HashMap<>();
        mediumTypMap = new HashMap<>();
        cdListenpreisMap = new TreeMap<>();
        platteListenpreisMap = new TreeMap<>();
        mp3ListenpreisMap = new TreeMap<>();

        for (Musik_Medium medium : musikMediumList) {
            updateAllMaps(medium);
        }
    }
    
    public void updateMedienListe(List<Musik_Medium> neueMedienListe) {
        for (Musik_Medium medium : neueMedienListe) {
            addMedium(medium);
        }
    }
    
    public void addMedium(Musik_Medium neuesMedium) {
        musikMediumList.add(neuesMedium);
        updateAllMaps(neuesMedium);
    }
    
    public void removeMedium(Musik_Medium zuEntfernendesMedium) {
        musikMediumList.remove(zuEntfernendesMedium);
        removeFromAllMaps(zuEntfernendesMedium);
    }
    
    private void removeFromAllMaps(Musik_Medium medium) {
        removeMediumFromMap(musikerMap, medium.getMusiker(), medium);
        removeMediumFromMap(genreMap, medium.getGenre(), medium);
        removeMediumFromMap(albumMap, medium.getAlbum(), medium);

      //  String mediumTyp = getMediumTyp(medium);
       // removeMediumFromMap(mediumTypMap, mediumTyp, medium);

        removeMediumFromMap(cdListenpreisMap, medium.getCDListenpreis(), medium);
        removeMediumFromMap(platteListenpreisMap, medium.getPlatteListenpreis(), medium);
        removeMediumFromMap(mp3ListenpreisMap, medium.getMp3Listenpreis(), medium);
    }
    
    private void removeMediumFromMap(HashMap<String, List<Musik_Medium>> map, String key, Musik_Medium medium) {
        List<Musik_Medium> medien = map.get(key);
        if (medien != null) {
            medien.remove(medium);
            if (medien.isEmpty()) {
                map.remove(key);
            }
        }
    }

    private void removeMediumFromMap(TreeMap<Double, List<Musik_Medium>> map, Double key, Musik_Medium medium) {
        List<Musik_Medium> medienListe = map.get(key);
        if (medienListe != null) {
            medienListe.remove(medium);
            if (medienListe.isEmpty()) {
                map.remove(key);
            }
        }
    }
    
    private void updateAllMaps(Musik_Medium medium) {
        addToMap(musikerMap, medium.getMusiker(), medium);
        addToMap(genreMap, medium.getGenre(), medium);
        addToMap(albumMap, medium.getAlbum(), medium);
        addToMap(songNameMap, medium.getSongName(), medium);
        // mediumTyp = getMediumTyp(medium);
        // addToMap(mediumTypMap, mediumTyp, medium);

        addToMap(cdListenpreisMap, medium.getCDListenpreis(), medium);
        addToMap(platteListenpreisMap, medium.getPlatteListenpreis(), medium);
        addToMap(mp3ListenpreisMap, medium.getMp3Listenpreis(), medium);
    }

    private List<String> getMediumTyp(Musik_Medium medium) {
        List<String> types = new ArrayList<>();
        if (medium.getIsCD()) {
            types.add("CD");
        }
        if (medium.getIsPlatte()) {
            types.add("Platte");
        }
        if (medium.getIsMp3()) {
            types.add("MP3");
        }
        return types;
    }

    public void addToMap(HashMap<String, List<Musik_Medium>> map, String key, Musik_Medium value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }
    
    private void addToMap(TreeMap<Double, List<Musik_Medium>> map, Double key, Musik_Medium value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }
    
    public List<Musik_Medium> getMedienBySongName(String songName) {
        return songNameMap.getOrDefault(songName, new ArrayList<>());
    }

    public List<Musik_Medium> getMedienByMusiker(String musiker) {
        return musikerMap.getOrDefault(musiker, new ArrayList<>());
    }

    public List<Musik_Medium> getMedienByGenre(String genre) {
        return genreMap.getOrDefault(genre, new ArrayList<>());
    }

    public List<Musik_Medium> getMedienByAlbum(String album) {
        return albumMap.getOrDefault(album, new ArrayList<>());
    }

    public List<Musik_Medium> getMedienByMediumTyp(String typ) {
        return mediumTypMap.getOrDefault(typ, new ArrayList<>());
    }

    public List<Musik_Medium> getMedienByCDListenpreis(Double preis) {
        return cdListenpreisMap.getOrDefault(preis, new ArrayList<>());
    }

    public List<Musik_Medium> getMedienByPlatteListenpreis(Double preis) {
        return platteListenpreisMap.getOrDefault(preis, new ArrayList<>());
    }

    public List<Musik_Medium> getMedienByMp3Listenpreis(Double preis) {
        return mp3ListenpreisMap.getOrDefault(preis, new ArrayList<>());
    }
    
    public List<Musik_Medium> filterMedienByMusikerGenreIsCD(String musiker, String genre, boolean isCD) {
        return musikMediumList.stream()
            .filter(medium -> medium.getMusiker().equals(musiker))
            .filter(medium -> medium.getGenre().equals(genre))
            .filter(medium -> (isCD && medium.getIsCD()) || (!isCD && !medium.getIsCD()))
            .collect(Collectors.toList());
    }
    
    public List<Musik_Medium> filterMedienByMusikerGenre(String musiker, String genre) {
    List<Musik_Medium> byMusiker = musikerMap.getOrDefault(musiker, new ArrayList<>());
    List<Musik_Medium> byGenre = genreMap.getOrDefault(genre, new ArrayList<>());

        return byMusiker.stream()
            .filter(byGenre::contains)
            .collect(Collectors.toList());
    }

    public List<Musik_Medium> filterMedienByMusikerAlbums(String musiker, List<String> albums) {
    List<Musik_Medium> byMusiker = musikerMap.getOrDefault(musiker, new ArrayList<>());

        return byMusiker.stream()
            .filter(medium -> albums.isEmpty() || albums.contains(medium.getAlbum()))
            .collect(Collectors.toList());
    }

    public List<Musik_Medium> filterMedienByGenreAlbums(String genre, List<String> albums) {
    List<Musik_Medium> byGenre = genreMap.getOrDefault(genre, new ArrayList<>());

        return byGenre.stream()
            .filter(medium -> albums.isEmpty() || albums.contains(medium.getAlbum()))
            .collect(Collectors.toList());
    }

}