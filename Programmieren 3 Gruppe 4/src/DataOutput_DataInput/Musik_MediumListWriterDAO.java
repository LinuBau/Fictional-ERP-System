package DataOutput_DataInput;

import java.io.IOException;

import GeschaftsObejekt.Musik_Medium;
import GeschaftsObejekt.Musik_MediumList;

public class Musik_MediumListWriterDAO extends Musik_MediumWriterDAO {
    
    public Musik_MediumListWriterDAO(String filename, boolean writing) {
        super(filename, writing);
    }

    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
            Musik_MediumList mml = (Musik_MediumList) obj;
            out.println(mml.size());
            Musik_MediumWriterDAO mmd = new Musik_MediumWriterDAO(out, null);
            for (Musik_Medium mm : mml) {
                mmd.write(mm);
            }
        }
    }

    @Override
    public void read(Object obj) throws IOException {
        if (in !=null) {
            Musik_MediumList mml = (Musik_MediumList) obj;
         int size = Integer.parseInt(in.readLine());
        Musik_MediumWriterDAO mmd = new Musik_MediumWriterDAO(null, in);
        for(int i=0; i<size;i++){
            Musik_Medium mm = new Musik_Medium();
            mmd.read(mm);
            mml.add(mm);
        }
        }
        
    }
}
