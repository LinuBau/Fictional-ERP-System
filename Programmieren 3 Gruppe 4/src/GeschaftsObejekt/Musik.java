
package GeschaftsObejekt;

import java.util.Objects;

public class Musik {
    private int Musik_GUID;
    private int CdCount;
    private int VinylCount;
    private String MBID;
    private String Artist;
    private String Album;
    private String SongName;
    private String Regal_PlatzCD;
    private String Regal_PlatzPlatte;
    private double CDListenpreis;
    private double PlatteListenpreis;
    private double Mp3Listenpreis;
    private double CDEinkaufpreis;
    private double PlatteEinkaufpreis;
    private double Mp3Einkaufpreis;
    private String Genre;
    private boolean CD;
    private boolean Platte;
    private boolean Mp3;
 //   private List<ChangeLogEntry> changeLog = new ArrayList<>();
    
    @Override
    public String toString() {
        return "{\n" +
       " Musik_GUID='" + getMusik_GUID() + "',\n" +
       " Musiker='" + getMusiker() + "',\n" +
       " Album='" + getAlbum() + "',\n" +
       " SongName='" + getSongName() + "',\n" +
       " Regal_PlatzCD='" + getRegal_PlatzCD() + "',\n" +
       " Regal_PlatzPlatte='" + getRegal_PlatzPlatte() + "',\n" +
       " CDListenpreis='" + getCDListenpreis() + "',\n" +
       " PlatteListenpreis='" + getPlatteListenpreis() + "',\n" +
       " Mp3Listenpreis='" + getMp3Listenpreis() + "',\n" +
       " CDEinkaufpreis='" + getCDEinkaufpreis() + "',\n" +
       " PlatteEinkaufpreis='" + getPlatteEinkaufpreis() + "',\n" +
       " Mp3Einkaufpreis='" + getMp3Einkaufpreis() + "',\n" +
       " Genre='" + getGenre() + "',\n" +
       " isCD='" + isIsCD() + "',\n" +
       " isPlatte='" + isIsPlatte() + "',\n" +
       " isMp3='" + isIsMp3() + "',\n" +
       " CdCount='" + getCdCount() + "',\n" +
       " VinylCount='" + getVinylCount() + "'\n" +                               
       "}";

    }
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Musik musik = (Musik) o;
    return Musik_GUID == musik.Musik_GUID &&
           CdCount == musik.CdCount &&
           VinylCount == musik.VinylCount &&
           Double.compare(musik.CDListenpreis, CDListenpreis) == 0 &&
           Double.compare(musik.PlatteListenpreis, PlatteListenpreis) == 0 &&
           Double.compare(musik.Mp3Listenpreis, Mp3Listenpreis) == 0 &&
           Double.compare(musik.CDEinkaufpreis, CDEinkaufpreis) == 0 &&
           Double.compare(musik.PlatteEinkaufpreis, PlatteEinkaufpreis) == 0 &&
           Double.compare(musik.Mp3Einkaufpreis, Mp3Einkaufpreis) == 0 &&
           CD == musik.CD &&
           Platte == musik.Platte &&
           Mp3 == musik.Mp3 &&
           Objects.equals(MBID, musik.MBID) &&
           Objects.equals(Artist, musik.Artist) &&
           Objects.equals(Album, musik.Album) &&
           Objects.equals(SongName, musik.SongName) &&
           Objects.equals(Regal_PlatzCD, musik.Regal_PlatzCD) &&
           Objects.equals(Regal_PlatzPlatte, musik.Regal_PlatzPlatte) &&
           Objects.equals(Genre, musik.Genre);
}

@Override
public int hashCode() {
    return Objects.hash(Musik_GUID, CdCount, VinylCount, MBID, Artist, Album, SongName, Regal_PlatzCD, Regal_PlatzPlatte, CDListenpreis, PlatteListenpreis, Mp3Listenpreis, CDEinkaufpreis, PlatteEinkaufpreis, Mp3Einkaufpreis, Genre, CD, Platte, Mp3);
}


  
    

    public Musik(int Musik_GUID,String MBID, String Musiker, String Album, String SongName, String Regal_PlatzCD,String Regal_PlatzPlatte,
            double CDListenpreis, double PlatteListenpreis, double Mp3Listenpreis, double CDEinkaufpreis,
            double PlatteEinkaufpreis, double Mp3Einkaufpreis, String Genre, boolean isCD, boolean isPlatte,
            boolean isMp3, int CdCount,int VinylCount ) {
        this.Musik_GUID = Musik_GUID;
        this.MBID = MBID;
        this.Artist = Musiker;
        this.Album = Album;
        this.SongName = SongName;
        this.Regal_PlatzCD = Regal_PlatzCD;
        this.Regal_PlatzPlatte = Regal_PlatzPlatte;
        this.CDListenpreis = CDListenpreis;
        this.PlatteListenpreis = PlatteListenpreis;
        this.Mp3Listenpreis = Mp3Listenpreis;
        this.CDEinkaufpreis = CDEinkaufpreis;
        this.PlatteEinkaufpreis = PlatteEinkaufpreis;
        this.Mp3Einkaufpreis = Mp3Einkaufpreis;
        this.Genre = Genre;
        this.CD = isCD;
        this.Platte = isPlatte;
        this.Mp3 = isMp3;
        this.CdCount = CdCount;
        this.VinylCount = VinylCount;
    }
    public double getPreisofAll(Musik m,int platteMenge,int cdMenge){
        double mp3prise=0;
        if (m.Mp3) {
            mp3prise=Mp3Listenpreis;
        }
        return (m.PlatteListenpreis*platteMenge)+(CDListenpreis*cdMenge)+mp3prise;
    }

    public String getMBID(){
        return this.MBID;
    }
    public void setMBID(String mbid){
        this.MBID = mbid;
    }
    
    public int getCdCount(){
        return this.CdCount;
    }
    
    public void setCdCount(int CdCount){
        this.CdCount = CdCount;
    }
    
    public int getVinylCount(){
        return this.VinylCount;
    }
    
    public void setVinylCount(int VinylCount){
        this.VinylCount = VinylCount;
    }

    public int getMusik_GUID() {
        return this.Musik_GUID;
    }

    public void setMusik_GUID(int Musik_GUID) {
        this.Musik_GUID = Musik_GUID;
    }

    public String getMusiker() {
        return this.Artist;
    }

    public void setMusiker(String Musiker) {
        this.Artist = Musiker;
    }

    public String getAlbum() {
        return this.Album;
    }

    public void setAlbum(String Album) {
        this.Album = Album;
    }

    public String getSongName() {
        return this.SongName;
    }

    public void setSongName(String SongName) {
        this.SongName = SongName;
    }

    public String getRegal_PlatzCD() {
        return this.Regal_PlatzCD;
    }

    public void setRegal_PlatzCD(String Regal_PlatzCD) {
        this.Regal_PlatzCD = Regal_PlatzCD;
    }
     public String getRegal_PlatzPlatte() {
        return this.Regal_PlatzPlatte;
    }

    public void setRegal_PlatzPlatte(String Regal_PlatzPlatte) {
        this.Regal_PlatzPlatte = Regal_PlatzPlatte;
    }

    public double getCDListenpreis() {
        return this.CDListenpreis;
    }

    public void setCDListenpreis(double CDListenpreis) {
        this.CDListenpreis = CDListenpreis;
    }

    public double getPlatteListenpreis() {
        return this.PlatteListenpreis;
    }

    public void setPlatteListenpreis(double PlatteListenpreis) {
        this.PlatteListenpreis = PlatteListenpreis;
    }

    public double getMp3Listenpreis() {
        return this.Mp3Listenpreis;
    }

    public void setMp3Listenpreis(double Mp3Listenpreis) {
        this.Mp3Listenpreis = Mp3Listenpreis;
    }

    public double getCDEinkaufpreis() {
        return this.CDEinkaufpreis;
    }

    public void setCDEinkaufpreis(double CDEinkaufpreis) {
        this.CDEinkaufpreis = CDEinkaufpreis;
    }

    public double getPlatteEinkaufpreis() {
        return this.PlatteEinkaufpreis;
    }

    public void setPlatteEinkaufpreis(double PlatteEinkaufpreis) {
        this.PlatteEinkaufpreis = PlatteEinkaufpreis;
    }

    public double getMp3Einkaufpreis() {
        return this.Mp3Einkaufpreis;
    }

    public void setMp3Einkaufpreis(double Mp3Einkaufpreis) {
        this.Mp3Einkaufpreis = Mp3Einkaufpreis;
    }

    public String getGenre() {
        return this.Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public boolean isIsCD() {
        return this.CD;
    }

    public boolean getIsCD() {
        return this.CD;
    }

    public void setIsCD(boolean isCD) {
        this.CD = isCD;
    }

    public boolean isIsPlatte() {
        return this.Platte;
    }

    public boolean getIsPlatte() {
        return this.Platte;
    }

    public void setIsPlatte(boolean isPlatte) {
        this.Platte = isPlatte;
    }

    public boolean isIsMp3() {
        return this.Mp3;
    }

    public boolean getIsMp3() {
        return this.Mp3;
    }

    public void setIsMp3(boolean isMp3) {
        this.Mp3 = isMp3;
    }

    public Musik() {
    }
    
//    public void addToChangeLog(String action, Musik originalState, Musik newState) {
//        ChangeLogEntry entry = new ChangeLogEntry(action, originalState, newState);
//        changeLog.add(entry);
//    }
//
//    public List<ChangeLogEntry> getChangeLog() {
//        return changeLog;
//    }
    
    public void reduziereCdAnzahl(int menge) {
        if (menge < 0) return; // Negative Mengen werden ignoriert
        this.CdCount = Math.max(this.CdCount - menge, 0);
    }
    
    public void reduziereVinylAnzahl(int menge) {
        if (menge < 0) return; // Negative Mengen werden ignoriert
        this.VinylCount = Math.max(this.VinylCount - menge, 0);
    }
public Musik clone() {
    Musik copy = new Musik();
    copy.setMusik_GUID(this.getMusik_GUID());
    copy.setAlbum(this.getAlbum());
    copy.setCDEinkaufpreis(this.getCDEinkaufpreis());
    copy.setCDListenpreis(this.getCDListenpreis());
    copy.setCdCount(this.getCdCount());
    copy.setGenre(this.getGenre());
    copy.setIsCD(this.getIsCD());
    copy.setIsMp3(this.getIsMp3());
    copy.setIsPlatte(this.getIsPlatte());
    copy.setMBID(this.getMBID());
    copy.setMp3Einkaufpreis(this.getMp3Einkaufpreis());
    copy.setMp3Listenpreis(this.getMp3Listenpreis());
    copy.setMusiker(this.getMusiker());
    copy.setPlatteEinkaufpreis(this.getPlatteEinkaufpreis());
    copy.setPlatteListenpreis(this.getPlatteListenpreis());
    copy.setRegal_PlatzCD(this.getRegal_PlatzCD());
    copy.setRegal_PlatzPlatte(this.getRegal_PlatzPlatte());
    copy.setSongName(this.getSongName());
    copy.setVinylCount(this.getVinylCount());
    return copy;
}


}
