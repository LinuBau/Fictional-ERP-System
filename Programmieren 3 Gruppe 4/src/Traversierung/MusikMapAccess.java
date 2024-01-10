
package Traversierung;

import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Traversierung.ChangeLogEntry;

public class MusikMapAccess implements IMusikMapAccess {
    private MusikMap musikMap;
    private List<ChangeLogEntry> changeLog;

    public MusikMapAccess(MusikMap musikMap) {
        this.musikMap = musikMap;
        this.changeLog = new ArrayList<>();
    }
    
    @Override
    public MusikList getAllMusik() {
 //       musikMap.getGesamteMusikListe();
    return musikMap.getMusikList();
}

    @Override
    public void addMusik(Musik musik) {
        musikMap.addMedium(musik);
        changeLog.add(new ChangeLogEntry("ADD", null, musik));
    }

  /*  @Override
    public void updateMusik(Musik original, Musik updated) {
        musikMap.updateMedium(original, updated);
        changeLog.add(new ChangeLogEntry("UPDATE", original, updated));
    }   */

    @Override
    public void removeMusik(Musik musik) {
        musikMap.removeMedium(musik);
        changeLog.add(new ChangeLogEntry("REMOVE", musik, null));
    }

    @Override
    public List<Musik> searchByTitel(String titel) {
        return musikMap.getMusikList().stream()
            .filter(m -> m.getSongName().toLowerCase().contains(titel.toLowerCase()))
            .collect(Collectors.toList());
    }

 @Override
    public List<Musik> searchByInterpret(String interpret) {
        return musikMap.getMusikList().stream()
            .filter(m -> m.getMusiker().toLowerCase().contains(interpret.toLowerCase()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Musik> searchByGenre(String genre) {
        return musikMap.getMusikList().stream()
            .filter(m -> m.getGenre().toLowerCase().contains(genre.toLowerCase()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Musik> searchByAlbum(String album) {
        return musikMap.getMusikList().stream()
            .filter(m -> m.getAlbum().toLowerCase().contains(album.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    @Override
    public List<ChangeLogEntry> getChangeLog() {
        return new ArrayList<>(changeLog); // Gibt eine Kopie des Change Logs zurück
    }

    private void trackChanges(Musik original, Musik updated) {
        // Logik zum Vergleichen der Eigenschaften von 'original' und 'updated' und 
        // zum Hinzufügen von Einträgen zum ChangeLog, wenn Änderungen festgestellt werden.
    }

    // Optionale Methode, um das ChangeLog für eine bestimmte Musik-Entität zu erhalten
    public List<ChangeLogEntry> getChangeLogForMusik(int musikId) {
        return changeLog.stream()
            .filter(entry -> entry.getOriginalState().getMusik_GUID() == musikId ||
                             entry.getNewState().getMusik_GUID() == musikId)
            .collect(Collectors.toList());
    }
}
