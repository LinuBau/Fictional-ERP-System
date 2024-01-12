package GeschaftsObejekt;

import java.util.ArrayList;
import java.util.List;

public class profil {
    private final String username;
    private final int passwordHash;
    private ArrayList<Integer> musikId;
    private ArrayList<Integer> pallteStückZahlList;
    private ArrayList<Integer> cdStückZahlList;
    private ArrayList<Boolean> mp3Gekauft;
    private final boolean ismitarbeiter;

    public profil(String username, int passwordHash, boolean ismitarbeiter) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.ismitarbeiter = ismitarbeiter;
        this.musikId = new ArrayList<>();
        this.pallteStückZahlList = new ArrayList<>();
        this.cdStückZahlList = new ArrayList<>();
        this.mp3Gekauft = new ArrayList<>();
    }

    public profil(String username, int passwordHash, boolean ismitarbeiter, List<Integer> id, List<Integer> platteStück,
            List<Integer> cdStück, List<Boolean> mp3) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.ismitarbeiter = ismitarbeiter;
        this.musikId = new ArrayList<>(id);
        this.pallteStückZahlList = new ArrayList<>(platteStück);
        this.cdStückZahlList = new ArrayList<>(cdStück);
        this.mp3Gekauft = new ArrayList<>(mp3);
    }

    public boolean login(String user, int password) {
        if (username.equals(user) && passwordHash == password) {
            return true;
        } else {
            return false;
        }
    }
    public void clear(){
        musikId.clear();
        pallteStückZahlList.clear();
        cdStückZahlList.clear();
        mp3Gekauft.clear();
    }

    public int ismitarbeiter(profil p) {
        if (p.getIsmitarbeiter()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void addtoArrayList(int id, int pallteStückZahl, int cdStückZahl, boolean mp3) {
        musikId.add(id);
        pallteStückZahlList.add(pallteStückZahl);
        cdStückZahlList.add(cdStückZahl);
        mp3Gekauft.add(mp3);
    }
     public void replacetoArrayList(int index, int newpallteStückZahl, int newcdStückZahl, boolean newmp3) {
        pallteStückZahlList.set(index, newpallteStückZahl);
        cdStückZahlList.set(index, newcdStückZahl);
        mp3Gekauft.set(index, newmp3);
    }

    public String getUsername() {
        return this.username;
    }

    public int getPasswordHash() {
        return this.passwordHash;
    }

    public ArrayList<Integer> getMusikId() {
        return this.musikId;
    }

    public void setMusikId(ArrayList<Integer> musikId) {
        this.musikId = musikId;
    }

    public ArrayList<Integer> getPallteStückZahlList() {
        return this.pallteStückZahlList;
    }

    public void setPallteStückZahlList(ArrayList<Integer> pallteStückZahlList) {
        this.pallteStückZahlList = pallteStückZahlList;
    }

    public ArrayList<Integer> getCdStückZahlList() {
        return this.cdStückZahlList;
    }

    public void setCdStückZahlList(ArrayList<Integer> cdStückZahlList) {
        this.cdStückZahlList = cdStückZahlList;
    }

    public ArrayList<Boolean> getMp3Gekauft() {
        return this.mp3Gekauft;
    }

    public void setMp3Gekauft(ArrayList<Boolean> mp3Gekauft) {
        this.mp3Gekauft = mp3Gekauft;
    }

    public boolean getIsmitarbeiter() {
        return this.ismitarbeiter;
    }

    public void removeArray(int index) {
        musikId.remove(index);
        pallteStückZahlList.remove(index);
        cdStückZahlList.remove(index);
        mp3Gekauft.remove(index);
    }

}
