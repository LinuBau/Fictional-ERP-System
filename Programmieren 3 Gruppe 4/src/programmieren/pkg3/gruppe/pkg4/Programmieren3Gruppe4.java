package programmieren.pkg3.gruppe.pkg4;

import DataOutput_DataInput.Musik_MediumDAO;
import GeschaftsObejekt.Musik_Medium;

public class Programmieren3Gruppe4 {

    public static void main(String[] args) {
        // TODO code application logic here
        String filepath = "build/data/neu.data";
        Musik_Medium gg = new Musik_Medium(1, "fg", "er", "tr", "A1", 4.9, 49, 1, 1, 1, 1, "hg", true, false, false);
        // String fileName = "C:/Users/linus/OneDrive/Desktop/gj.data/";
        Musik_MediumDAO output = new Musik_MediumDAO(filepath, true);
        Musik_MediumDAO input = new Musik_MediumDAO(filepath, false);

        Musik_Medium hr = new Musik_Medium();
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


