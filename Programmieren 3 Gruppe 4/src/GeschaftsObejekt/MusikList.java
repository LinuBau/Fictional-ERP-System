
package GeschaftsObejekt;


import java.util.ArrayList;

public class MusikList extends ArrayList<Musik> {
    public boolean unique(int id,MusikList ml){
        for(Musik m : ml){
            if (m.getMusik_GUID() == id) {
                return false;
            }
        }
        return true;
    }
  
}
