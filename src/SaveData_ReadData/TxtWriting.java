package SaveData_ReadData;

import java.io.IOException;
import java.time.LocalDate;

import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;
import GeschaftsObejekt.profil;

public class TxtWriting extends AbstractCsvDOA {
    private MusikList musikList;
    profil profil;

    public TxtWriting(String filepath, boolean writing, MusikList musikList, profil profil) {
        super(filepath, writing);
        this.musikList = musikList;
        this.profil = profil;
    }

    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
            String filepath = (String)obj;
            String header = "Rechnung\n\nKundenname: " +
                    "nAdresse: Musterstraße 123\nStadt: Musterstadt";
            out.println(header);
            out.println("Datum: " + LocalDate.now());

            out.println("Artikelliste:");
            int counter = 0;
            for (Musik musik : musikList) {
                System.out.println(profil.getPallteStückZahlList().get(counter));
                if (profil.getPallteStückZahlList().get(counter) != 0) {
                    out.println("Name: " + musik.getSongName() + " von " + musik.getMusiker() + "auf Platte");
                    out.println("Preis: " + musik.getPlatteListenpreis() + "€");
                    out.println("Menge: " + profil.getPallteStückZahlList().get(counter));
                }
                counter++;
            }
            counter = 0;
            for (Musik musik : musikList) {
                if (profil.getCdStückZahlList().get(counter) != 0) {
                    out.println("Name: " + musik.getSongName() + " von " + musik.getMusiker() + "auf CD");
                    out.println("Preis: " + musik.getCDListenpreis() + "€");
                    out.println("Menge: " + profil.getCdStückZahlList().get(counter));
                }
                counter++;
            }
            counter = 0;
            for (Musik musik : musikList) {
                if (profil.getMp3Gekauft().get(counter)) {
                    out.println("Name: " + musik.getSongName() + " von " + musik.getMusiker() + "auf MP3");
                    out.println("Preis: " + musik.getMp3Einkaufpreis() + "€");
                    out.println("Menge:  1");
                }
                counter++;
            }

            out.println("Gesamt: " + berechneGesamtPreis() + "€");

            System.out.println("Die Rechnung wurde erfolgreich als Text exportiert: "+filepath);

        }
    }

    private  double berechneGesamtPreis() {
        double summe = 0;
        for (int i = 0; i < musikList.size(); i++) {
            summe += musikList.get(i).getPreisofAll(musikList.get(i), profil.getPallteStückZahlList().get(i),
                    profil.getCdStückZahlList().get(i));
        }
        return summe;
    }

    @Override
    public void read(Object obj) throws IOException {
        throw new UnsupportedOperationException("Soll nichts machen");
    }

}
