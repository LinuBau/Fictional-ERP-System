package SaveData_ReadData;

import java.io.IOException;

import GeschaftsObejekt.Profil;
import GeschaftsObejekt.ProfilList;

public class ProfilListDAO extends AbstractDAO {

    public ProfilListDAO(String Filename, boolean write) {
        super(Filename, write);
    }

    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
            ProfilList pl = (ProfilList) obj;
            out.writeInt(pl.size());
            ProfilDAO pd = new ProfilDAO(out, null);
            for (Profil p : pl) {
                pd.write(p);
            }
        }
    }

    @Override
    public void read(Object obj) throws IOException {
        if (in != null) {
            ProfilList pl = (ProfilList) obj;
            int size = in.readInt();
            ProfilDAO pd = new ProfilDAO(out, in);
            for (int i = 0; i < size; i++) {
                pl.add(pd.read());
                System.out.println(pl.get(i).getUsername());
            }
        }
    }

}
