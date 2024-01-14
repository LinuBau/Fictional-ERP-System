package SaveData_ReadData;

import java.io.IOException;

import GeschaftsObejekt.Musik;
import GeschaftsObejekt.MusikList;

public class MusikCsvListDAO extends MusikCsvDAO {
    
    public MusikCsvListDAO(String filename, boolean writing) {
        super(filename, writing);
    }

    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
            MusikList mml = (MusikList) obj;
            MusikCsvDAO mmd = new MusikCsvDAO(out, null);
            for (Musik mm : mml) {
                mmd.write(mm);
            }
        }
    }

    @Override
 public void read(Object obj) throws IOException {
    if (in != null) {
        MusikList mml = (MusikList) obj;
        MusikCsvDAO mmd = new MusikCsvDAO(null, in);
        String line;
        while ((line = in.readLine()) != null) {
            try {
                Musik MM = new Musik();
                mmd.toMusik_Medium(line, mmd.getSplitCondition(), MM);
                mml.add(MM);
            } catch (Exception e) {
                e.printStackTrace(); // Hier wird der Stack Trace ausgedruckt, wenn ein Fehler auftritt
            }
        }
    }
}
}
