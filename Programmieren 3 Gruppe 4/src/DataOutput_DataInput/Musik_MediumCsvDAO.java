/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataOutput_DataInput;



import GeschaftsObejekt.Musik_Medium;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Musik_MediumCsvDAO extends AbstractCsvDOA {

    private String splitCondition = ",";

    public String getSplitCondition() {
        return this.splitCondition;
    }

    public void setSplitCondition(String splitCondition) {
        this.splitCondition = splitCondition;
    }

    public Musik_MediumCsvDAO(String filename, boolean writing){
        super(filename, writing);
    }
    
      public Musik_MediumCsvDAO(PrintWriter out, BufferedReader in) {
        super(out, in);
    }
      public Musik_MediumCsvDAO(){
          
      }
    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
              Musik_Medium MM = (Musik_Medium) obj;
             out.println(this.tocvs(splitCondition,MM));
        }
     
    }

    @Override
    public void read(Object obj) throws IOException {
        if (in != null ) {
            Musik_Medium MM = (Musik_Medium) obj;
         String hhh = in.readLine();
         this.toMusik_Medium(hhh, splitCondition,MM);
        }
        
        
    }

    protected void toMusik_Medium(String csv, String splitCondition,Musik_Medium MM) {
        String[] csvStrings = csv.split(splitCondition);
    
        MM.setMusik_GUID(Integer.parseInt(csvStrings[0]));
        MM.setMusiker(csvStrings[1]);
        MM.setAlbum(csvStrings[2]);
        MM.setSongName(csvStrings[3]);
        MM.setRegal_Platz(csvStrings[4]);
        MM.setCDListenpreis(Double.parseDouble(csvStrings[5]));
        MM.setPlatteListenpreis(Double.parseDouble(csvStrings[6]));
        MM.setMp3Listenpreis(Double.parseDouble(csvStrings[7]));
        MM.setCDEinkaufpreis(Double.parseDouble(csvStrings[8]));
        MM.setPlatteEinkaufpreis(Double.parseDouble(csvStrings[9]));
        MM.setMp3Einkaufpreis(Double.parseDouble(csvStrings[10]));
        MM.setGenre(csvStrings[11]);
        MM.setIsCD(Boolean.parseBoolean(csvStrings[12]));
        MM.setIsPlatte(Boolean.parseBoolean(csvStrings[13]));
        MM.setIsMp3(Boolean.parseBoolean(csvStrings[14]));
    }
    private String tocvs(String splitKondiotn,Musik_Medium MM) {
        return  
             MM.getMusik_GUID() + splitKondiotn +
             MM.getMusiker() + splitKondiotn +
             MM.getAlbum() + splitKondiotn +
             MM.getSongName() + splitKondiotn +
             MM.getRegal_Platz() + splitKondiotn +
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



