package programmieren.pkg3.gruppe.pkg4;

import App_GUI.Gui;
import GeschaftsObejekt.Musik;
import SaveData_ReadData.MusikDAO;

public class Programmieren3Gruppe4 {

    public static void main(String[] args) {
        // TODO code application logic here
        String filepath = "build/data/neu.data";
        MusikDAO output = new MusikDAO(filepath, true);
        MusikDAO input = new MusikDAO(filepath, false);

        Musik hr = new Musik();
        try {
            //output.write();
            output.close();
            input.read(hr);
            System.out.println(hr.toString());
            input.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        Gui.main(args);
    }
}


