package GeschaftsObejekt;

import GeschaftsObejekt.Musik;
import java.util.ArrayList;
import java.util.ListIterator;

public class MusikList extends ArrayList<Musik> {

    /**
     *
     * @param id
     * @return false if int id in List else return true
     */
    public boolean unique(int id) {
        ListIterator<Musik> it = listIterator();
        if (id != 0) {
            while (it.hasNext()) {
                if (it.next().getMusik_GUID() == id) {
                    return false;
                }
            }
        } else {
            return true;
        }
        return true;
    }

    public boolean mbidunique(String mbid) {
        ListIterator<Musik> it = listIterator();
        if (mbid == null) {
            while (it.hasNext()) {
                if (it.next().getMBID().equals(mbid)) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    public int getIndex(int id) {
        ListIterator<Musik> it = listIterator();
        if (id != 0) {
            while (it.hasNext()) {
                if (it.next().getMusik_GUID() == id) {
                    return it.previousIndex();
                }
            }
        }
        return -1;
    }

    public void replaceMusik(Musik newmedium, MusikList musikList) {
        int index = getIndex(newmedium.getMusik_GUID());
        musikList.set(index, newmedium);
    }

}
