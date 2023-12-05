package DataOutput_DataInput;

import GeschaftsObejekt.MusikList;
import GeschaftsObejekt.Musik;
import java.io.IOException;

public class MusikListDAO extends AbstractDAO {

    public MusikListDAO(String Filename, boolean write) {
        super(Filename, write);

    }

    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
            MusikList mml = (MusikList) obj;
            out.writeInt(mml.size());
            MusikDAO mmdao = new MusikDAO(out, null);
            for (Musik mm : mml) {
                mmdao.write(mm);
            }
        }
    }

    @Override
    public void read(Object obj) throws IOException {
        if (in != null) {
            MusikList mml = (MusikList) obj;
            int size = in.readInt();
            MusikDAO mmdao = new MusikDAO(null, in);
            for (int i = 0; i < size; i++) {
                Musik mm = new Musik();
                mmdao.read(mm);
                mml.add(mm);
            }
        }
    }

}
