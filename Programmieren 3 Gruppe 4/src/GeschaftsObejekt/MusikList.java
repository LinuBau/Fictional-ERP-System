
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
    public int getIndex(int id, MusikList ml){
        for(int i=0;i<ml.size();i++){
            if(ml.get(i).getMusik_GUID()==id){
                return i;
            }
        }
        return -1;
    }
    public void replaceMusik( Musik newmedium,MusikList musikList){
        int index = getIndex(newmedium.getMusik_GUID(), musikList);
        musikList.set(index, newmedium);
    }
  
}
