package DataOutput_DataInput;

import GeschaftsObejekt.Musik_MediumList;
import GeschaftsObejekt.Musik_Medium;
import java.io.IOException;

public class Musik_MediumListDAO extends AbstractDAO {

    public Musik_MediumListDAO(String Filename, boolean write) {
        super(Filename, write);

    }

    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
            Musik_MediumList mml = (Musik_MediumList) obj;
            out.writeInt(mml.size());
            Musik_MediumDAO mmdao = new Musik_MediumDAO(out, null);
            for (Musik_Medium mm : mml) {
                mmdao.write(mm);
            }
        }
    }

    @Override
    public void read(Object obj) throws IOException {
        if (in != null) {
            Musik_MediumList mml = (Musik_MediumList) obj;
            int size = in.readInt();
            Musik_MediumDAO mmdao = new Musik_MediumDAO(null, in);
            for (int i = 0; i < size; i++) {
                Musik_Medium mm = new Musik_Medium();
                mmdao.read(mm);
                mml.add(mm);
            }
        }
    }

}
