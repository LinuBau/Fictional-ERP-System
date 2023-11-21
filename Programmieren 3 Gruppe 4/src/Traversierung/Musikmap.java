package Traversierung;
import GeschaftsObejekt.Musik;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Musikmap {
    
    private List<Musik> medienListe;
    private HashMap<String, List<Musik>> songNameMap;
    private HashMap<String, List<Musik>> musikerMap;
    private HashMap<String, List<Musik>> genreMap;
    private HashMap<String, List<Musik>> albumMap;
    private HashMap<String, List<Musik>> mediumTypMap;
    private TreeMap<Double, List<Musik>> cdListenpreisMap;
    private TreeMap<Double, List<Musik>> platteListenpreisMap;
    private TreeMap<Double, List<Musik>> mp3ListenpreisMap;

    public Musikmap(List<Musik> medienListe) {
        this.medienListe = new ArrayList<>(medienListe);
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

        for (Musik medium : medienListe) {
            updateAllMaps(medium);
        }
    }
    
    public void updateMedienListe(List<Musik> neueMedienListe) {
        for (Musik medium : neueMedienListe) {
            addMedium(medium);
        }
    }
    
    public void addMedium(Musik neuesMedium) {
        medienListe.add(neuesMedium);
        updateAllMaps(neuesMedium);
    }
    
    public void removeMedium(Musik zuEntfernendesMedium) {
        medienListe.remove(zuEntfernendesMedium);
        removeFromAllMaps(zuEntfernendesMedium);
    }
    
    private void removeFromAllMaps(Musik medium) {
        removeMediumFromMap(musikerMap, medium.getMusiker(), medium);
        removeMediumFromMap(genreMap, medium.getGenre(), medium);
        removeMediumFromMap(albumMap, medium.getAlbum(), medium);

      //  String mediumTyp = getMediumTyp(medium);
       // removeMediumFromMap(mediumTypMap, mediumTyp, medium);

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
    
    private void updateAllMaps(Musik medium) {
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

    private List<String> getMediumTyp(Musik medium) {
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

    public void addToMap(HashMap<String, List<Musik>> map, String key, Musik value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }
    
    private void addToMap(TreeMap<Double, List<Musik>> map, Double key, Musik value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }
    
    public List<Musik> getMedienBySongName(String songName) {
        return songNameMap.getOrDefault(songName, new ArrayList<>());
    }

    public List<Musik> getMedienByMusiker(String musiker) {
        return musikerMap.getOrDefault(musiker, new ArrayList<>());
    }

    public List<Musik> getMedienByGenre(String genre) {
        return genreMap.getOrDefault(genre, new ArrayList<>());
    }

    public List<Musik> getMedienByAlbum(String album) {
        return albumMap.getOrDefault(album, new ArrayList<>());
    }

    public List<Musik> getMedienByMediumTyp(String typ) {
        return mediumTypMap.getOrDefault(typ, new ArrayList<>());
    }

    public List<Musik> getMedienByCDListenpreis(Double preis) {
        return cdListenpreisMap.getOrDefault(preis, new ArrayList<>());
    }

    public List<Musik> getMedienByPlatteListenpreis(Double preis) {
        return platteListenpreisMap.getOrDefault(preis, new ArrayList<>());
    }

    public List<Musik> getMedienByMp3Listenpreis(Double preis) {
        return mp3ListenpreisMap.getOrDefault(preis, new ArrayList<>());
    }
    
    public List<Musik> filterMedienByMusikerGenreIsCD(String musiker, String genre, boolean isCD) {
        return medienListe.stream()
            .filter(medium -> medium.getMusiker().equals(musiker))
            .filter(medium -> medium.getGenre().equals(genre))
            .filter(medium -> (isCD && medium.getIsCD()) || (!isCD && !medium.getIsCD()))
            .collect(Collectors.toList());
    }
    
    public List<Musik> filterMedienByMusikerGenre(String musiker, String genre) {
    List<Musik> byMusiker = musikerMap.getOrDefault(musiker, new ArrayList<>());
    List<Musik> byGenre = genreMap.getOrDefault(genre, new ArrayList<>());

        return byMusiker.stream()
            .filter(byGenre::contains)
            .collect(Collectors.toList());
    }

    public List<Musik> filterMedienByMusikerAlbums(String musiker, List<String> albums) {
    List<Musik> byMusiker = musikerMap.getOrDefault(musiker, new ArrayList<>());

        return byMusiker.stream()
            .filter(medium -> albums.isEmpty() || albums.contains(medium.getAlbum()))
            .collect(Collectors.toList());
    }

    public List<Musik> filterMedienByGenreAlbums(String genre, List<String> albums) {
    List<Musik> byGenre = genreMap.getOrDefault(genre, new ArrayList<>());

        return byGenre.stream()
            .filter(medium -> albums.isEmpty() || albums.contains(medium.getAlbum()))
            .collect(Collectors.toList());
    }

}