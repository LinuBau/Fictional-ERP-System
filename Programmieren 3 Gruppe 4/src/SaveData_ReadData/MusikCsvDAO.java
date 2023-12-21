
package SaveData_ReadData;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import GeschaftsObejekt.Musik;

public class MusikCsvDAO extends AbstractCsvDOA {

    private String splitCondition = ",";

    public String getSplitCondition() {
        return this.splitCondition;
    }

    public void setSplitCondition(String splitCondition) {
        this.splitCondition = splitCondition;
    }

    public MusikCsvDAO(String filename, boolean writing){
        super(filename, writing);
    }
    
      public MusikCsvDAO(PrintWriter out, BufferedReader in) {
        super(out, in);
    }
      public MusikCsvDAO(){
          
      }
    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
              Musik MM = (Musik) obj;
             out.println(this.tocvs(splitCondition,MM));
        }
     
    }

    @Override
    public void read(Object obj) throws IOException {
        if (in != null ) {
            Musik MM = (Musik) obj;
         String hhh = in.readLine();
         this.toMusik_Medium(hhh, splitCondition,MM);
        }
        
    }

    protected void toMusik_Medium(String csv, String splitCondition,Musik MM) {
        String[] csvStrings = csv.split(splitCondition);
        
         try 
         {
        MM.setMusik_GUID(Integer.parseInt(csvStrings[0].trim())); // trim() entfernt Leerzeichen
    } catch (NumberFormatException e) {
        System.err.println("Fehler beim Parsen von Musik_GUID: " + csvStrings[0]);
    }
        MM.setMusiker(csvStrings[1]);
        MM.setAlbum(csvStrings[2]);
        MM.setSongName(csvStrings[3]);
        MM.setRegal_PlatzCD(csvStrings[4]);
        MM.setRegal_PlatzPlatte(csvStrings[5]);
        
        try
        {
        MM.setCDListenpreis(Double.parseDouble(csvStrings[6]));
        MM.setPlatteListenpreis(Double.parseDouble(csvStrings[7]));
        MM.setMp3Listenpreis(Double.parseDouble(csvStrings[8]));
        MM.setCDEinkaufpreis(Double.parseDouble(csvStrings[9]));
        MM.setPlatteEinkaufpreis(Double.parseDouble(csvStrings[10]));
        MM.setMp3Einkaufpreis(Double.parseDouble(csvStrings[11]));
        
        } catch (NumberFormatException e) {
        System.err.println("Fehler beim Parsen der Preise: " + e.getMessage());
    }
        MM.setGenre(csvStrings[12]);
        MM.setIsCD(Boolean.parseBoolean(csvStrings[13]));
        MM.setIsPlatte(Boolean.parseBoolean(csvStrings[14]));
        MM.setIsMp3(Boolean.parseBoolean(csvStrings[15]));
    }
    private String tocvs(String splitKondiotn,Musik MM) {
        return  
             MM.getMusik_GUID() + splitKondiotn +
             MM.getMusiker() + splitKondiotn +
             MM.getAlbum() + splitKondiotn +
             MM.getSongName() + splitKondiotn +
             MM.getRegal_PlatzCD() + splitKondiotn +
             MM.getRegal_PlatzPlatte() + splitKondiotn+
             MM.getCDListenpreis() + splitKondiotn +
             MM.getPlatteListenpreis() + splitKondiotn +
             MM.getMp3Listenpreis() + splitKondiotn +
             MM.getCDEinkaufpreis() + splitKondiotn +
             MM.getPlatteEinkaufpreis() + splitKondiotn +
             MM.getMp3Einkaufpreis() + splitKondiotn +
             MM.getGenre() + splitKondiotn +
             MM.isIsCD() + splitKondiotn +
             MM.isIsPlatte() + splitKondiotn +
             MM.isIsMp3() 
            ;
    }
}



