package programmieren.pkg3.gruppe.pkg4;

import DataOutput_DataInput.MusikDAO;
import GeschaftsObejekt.Musik;

public class Programmieren3Gruppe4 {

    public static void main(String[] args) {
        // TODO code application logic here
        String filepath = "build/data/neu.data";
        Musik gg = new Musik(1, "fg", "er", "tr", "A1", 4.9, 49, 1, 1, 1, 1, "hg", true, false, false);
        // String fileName = "C:/Users/linus/OneDrive/Desktop/gj.data/";
        MusikDAO output = new MusikDAO(filepath, true);
        MusikDAO input = new MusikDAO(filepath, false);

        Musik hr = new Musik();
        try {
            output.write(gg);
            output.close();
            input.read(hr);
            System.out.println(hr.toString());
            input.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}


