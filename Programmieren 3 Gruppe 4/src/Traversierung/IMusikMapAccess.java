
package Traversierung;

import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;
import java.util.List;


public interface IMusikMapAccess {
    void addMusik(Musik musik);
 //   void updateMusik(Musik original, Musik updated);
    void removeMusik(Musik musik);
    List<Musik> searchByTitel(String titel);
    List<Musik> searchByInterpret(String interpret);
    List<Musik> searchByGenre(String genre);
    List<Musik> searchByAlbum(String album);
    MusikList getAllMusik();
    List<ChangeLogEntry> getChangeLog();
}
