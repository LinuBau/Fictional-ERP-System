package SaveData_ReadData;

import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import GeschaftsObejekt.Profil;

public class ProfilDAO extends AbstractDAO {

    public ProfilDAO(String Filename, boolean write) {
        super(Filename, write);
    }

    public ProfilDAO(DataOutputStream Ouput, DataInputStream Input) {
        super(Ouput, Input);
    }

    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
            Profil p = (Profil) obj;
            out.writeUTF(p.getUsername());
            out.writeInt(p.getPasswordHash());
            out.writeBoolean(p.getIsmitarbeiter());

            int idSize = p.getMusikId().size();
            out.writeInt(idSize);
            if (idSize > 0) {
                for (Integer i : p.getMusikId()) {
                    out.writeInt(i);
                }
            }
            int pallteSize = p.getPallteStückZahlList().size();
            out.writeInt(pallteSize);
            if (pallteSize > 0) {
                for (Integer i : p.getPallteStückZahlList()) {
                    out.writeInt(i);
                }
            }
            int cdSize = p.getCdStückZahlList().size();
            out.writeInt(cdSize);
            if (cdSize > 0) {
                for (Integer i : p.getCdStückZahlList()) {
                    out.writeInt(i);
                }
            }
            int mp3Size = p.getMp3Gekauft().size();
            out.writeInt(mp3Size);
            if (mp3Size > 0) {
                for (Boolean b : p.getMp3Gekauft()) {
                    out.writeBoolean(b);
                }
            }
        }
    }

    @Override
    public void read(Object obj) throws IOException {
        if (in != null) {
            String username = in.readUTF();
            int passwordHash = in.readInt();
            boolean ismitarbeiter = in.readBoolean();

            int musikIdSize = in.readInt();
            List<Integer> musikId = new ArrayList<>(musikIdSize);
            for (int i = 0; i < musikIdSize; i++) {
                musikId.add(in.readInt());
            }

            int pallteStückSize = in.readInt();
            List<Integer> pallteStück = new ArrayList<>(pallteStückSize);
            for (int i = 0; i < pallteStückSize; i++) {
                pallteStück.add(in.readInt());
            }

            int cdStückSize = in.readInt();
            List<Integer> cdStück = new ArrayList<>(cdStückSize);
            for (int i = 0; i < cdStückSize; i++) {
                cdStück.add(in.readInt());
            }

            int mp3Size = in.readInt();
            List<Boolean> mp3 = new ArrayList<>(mp3Size);
            for (int i = 0; i < mp3Size; i++) {
                mp3.add(in.readBoolean());
            }
            obj = new Profil(username, passwordHash, ismitarbeiter, musikId, pallteStück, cdStück, mp3);
        }
    }
    public Profil read() throws IOException{
            if (in != null) {
            String username = in.readUTF();
            int passwordHash = in.readInt();
            boolean ismitarbeiter = in.readBoolean();

            int musikIdSize = in.readInt();
            List<Integer> musikId = new ArrayList<>(musikIdSize);
            for (int i = 0; i < musikIdSize; i++) {
                musikId.add(in.readInt());
            }

            int pallteStückSize = in.readInt();
            List<Integer> pallteStück = new ArrayList<>(pallteStückSize);
            for (int i = 0; i < pallteStückSize; i++) {
                pallteStück.add(in.readInt());
            }

            int cdStückSize = in.readInt();
            List<Integer> cdStück = new ArrayList<>(cdStückSize);
            for (int i = 0; i < cdStückSize; i++) {
                cdStück.add(in.readInt());
            }

            int mp3Size = in.readInt();
            List<Boolean> mp3 = new ArrayList<>(mp3Size);
            for (int i = 0; i < mp3Size; i++) {
                mp3.add(in.readBoolean());
            }
            Profil p = new Profil(username, passwordHash, ismitarbeiter, musikId, pallteStück, cdStück, mp3);
            return p;
        }else{
            return null;
        }
    }
}
