
package GeschaftsObejekt;


public class Musik_Medium {
     private int Musik_GUID;
    private String Musiker;
    private String Album; 
    private  String SongName;
    private String Regal_Platz;
    private double CDListenpreis;
    private double PlatteListenpreis;
    private double Mp3Listenpreis;
    private double CDEinkaufpreis;
    private double PlatteEinkaufpreis;
    private double Mp3Einkaufpreis;
    private String Genre;
    private boolean isCD;
    private boolean isPlatte;
    private boolean isMp3;

    @Override
    public String toString() {
        return "{" +
            " Musik_GUID='" + getMusik_GUID() + "'" +
            ", Musiker='" + getMusiker() + "'" +
            ", Album='" + getAlbum() + "'" +
            ", SongName='" + getSongName() + "'" +
            ", Regal_Platz='" + getRegal_Platz() + "'" +
            ", CDListenpreis='" + getCDListenpreis() + "'" +
            ", PlatteListenpreis='" + getPlatteListenpreis() + "'" +
            ", Mp3Listenpreis='" + getMp3Listenpreis() + "'" +
            ", CDEinkaufpreis='" + getCDEinkaufpreis() + "'" +
            ", PlatteEinkaufpreis='" + getPlatteEinkaufpreis() + "'" +
            ", Mp3Einkaufpreis='" + getMp3Einkaufpreis() + "'" +
            ", Genre='" + getGenre() + "'" +
            ", isCD='" + isIsCD() + "'" +
            ", isPlatte='" + isIsPlatte() + "'" +
            ", isMp3='" + isIsMp3() + "'" +
            "}";
    }

    public Musik_Medium(int Musik_GUID, String Musiker, String Album, String SongName, String Regal_Platz, double CDListenpreis, double PlatteListenpreis, double Mp3Listenpreis, double CDEinkaufpreis, double PlatteEinkaufpreis, double Mp3Einkaufpreis, String Genre, boolean isCD, boolean isPlatte, boolean isMp3) {
        this.Musik_GUID = Musik_GUID;
        this.Musiker = Musiker;
        this.Album = Album;
        this.SongName = SongName;
        this.Regal_Platz = Regal_Platz;
        this.CDListenpreis = CDListenpreis;
        this.PlatteListenpreis = PlatteListenpreis;
        this.Mp3Listenpreis = Mp3Listenpreis;
        this.CDEinkaufpreis = CDEinkaufpreis;
        this.PlatteEinkaufpreis = PlatteEinkaufpreis;
        this.Mp3Einkaufpreis = Mp3Einkaufpreis;
        this.Genre = Genre;
        this.isCD = isCD;
        this.isPlatte = isPlatte;
        this.isMp3 = isMp3;
    }

    public int getMusik_GUID() {
        return this.Musik_GUID;
    }

    public void setMusik_GUID(int Musik_GUID) {
        this.Musik_GUID = Musik_GUID;
    }

    public String getMusiker() {
        return this.Musiker;
    }

    public void setMusiker(String Musiker) {
        this.Musiker = Musiker;
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

    public String getRegal_Platz() {
        return this.Regal_Platz;
    }

    public void setRegal_Platz(String Regal_Platz) {
        this.Regal_Platz = Regal_Platz;
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
        return this.isCD;
    }

    public boolean getIsCD() {
        return this.isCD;
    }

    public void setIsCD(boolean isCD) {
        this.isCD = isCD;
    }

    public boolean isIsPlatte() {
        return this.isPlatte;
    }

    public boolean getIsPlatte() {
        return this.isPlatte;
    }

    public void setIsPlatte(boolean isPlatte) {
        this.isPlatte = isPlatte;
    }

    public boolean isIsMp3() {
        return this.isMp3;
    }

    public boolean getIsMp3() {
        return this.isMp3;
    }

    public void setIsMp3(boolean isMp3) {
        this.isMp3 = isMp3;
    }
    public Musik_Medium() {
    }

   
}
