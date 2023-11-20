package DataOutput_DataInput;

import java.io.IOException;

import GeschaftsObejekt.Musik_Medium;
import GeschaftsObejekt.Musik_MediumList;

public class Musik_MediumCsvListDAO extends Musik_MediumCsvDAO {
    
    public Musik_MediumCsvListDAO(String filename, boolean writing) {
        super(filename, writing);
    }

    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
            Musik_MediumList mml = (Musik_MediumList) obj;
            Musik_MediumCsvDAO mmd = new Musik_MediumCsvDAO(out, null);
            for (Musik_Medium mm : mml) {
                mmd.write(mm);
            }
        }
    }

    @Override
    public void read(Object obj) throws IOException {
        if (in !=null) {
            Musik_MediumList mml = (Musik_MediumList) obj;
        Musik_MediumCsvDAO mmd = new Musik_MediumCsvDAO();
           String line;
         while ((line = in.readLine()) != null) {
            Musik_Medium MM = new Musik_Medium();
            mmd.toMusik_Medium(line, mmd.getSplitCondition(), MM);
            mml.add(MM);
         }
        }
        
    }
}
