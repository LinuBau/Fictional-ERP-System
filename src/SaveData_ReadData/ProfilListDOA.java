package SaveData_ReadData;

import java.io.IOException;

import GeschaftsObejekt.profil;
import GeschaftsObejekt.profilList;

public class ProfilListDOA extends AbstractDAO {

    public ProfilListDOA(String Filename, boolean write) {
        super(Filename, write);
    }

    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
            profilList pl = (profilList) obj;
            out.writeInt(pl.size());
            ProfilDOA pd = new ProfilDOA(out, null);
            for (profil p : pl) {
                pd.write(p);
            }
        }
    }

    @Override
    public void read(Object obj) throws IOException {
        if (in != null) {
            profilList pl = (profilList) obj;
            int size = in.readInt();
            ProfilDOA pd = new ProfilDOA(out, in);
            for (int i = 0; i < size; i++) {
                pl.add(pd.read());
                System.out.println(pl.get(i).getUsername());
            }
        }
    }

}
