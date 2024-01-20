package SaveData_ReadData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import GeschaftsObejekt.Musik;

public class MusikDAO extends AbstractDAO {

    public MusikDAO(DataOutputStream Ouput, DataInputStream Input) {
        super(Ouput, Input);
    }

    public MusikDAO(String filename, boolean write) {
        super(filename, write);
    }

    /**
     * Implimiert das schreiben des Objetks Musik
     *
     * @param obj muss ein Musik sein
     * @throws IOException muss weil wir schreiben
     */
    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
            Musik MM = (Musik) obj;
            out.writeInt(MM.getMusik_GUID());
            out.writeUTF(MM.getMusiker());
            out.writeUTF(MM.getAlbum());
            out.writeUTF(MM.getSongName());
            out.writeUTF(MM.getRegal_PlatzCD());
            out.writeUTF(MM.getRegal_PlatzPlatte());
            out.writeDouble(MM.getCDListenpreis());
            out.writeDouble(MM.getPlatteListenpreis());
            out.writeDouble(MM.getMp3Listenpreis());
            out.writeDouble(MM.getCDEinkaufpreis());
            out.writeDouble(MM.getPlatteEinkaufpreis());
            out.writeDouble(MM.getMp3Einkaufpreis());
            out.writeUTF(MM.getGenre());
            out.writeBoolean(MM.getIsMp3());
            out.writeInt(MM.getCdCount());
            out.writeInt(MM.getVinylCount());
            out.writeUTF(MM.getMBID());
        }
    }

    /**
     * Implimiert das lesen des Obejects Musik
     *
     * @param obj muss ein Musik sein
     * @throws IOException muss weil wir schreiben
     */
    @Override
    public void read(Object obj) throws IOException {
        if (in != null) {
            Musik MM = (Musik) obj;
            MM.setMusik_GUID(in.readInt());

            MM.setMusiker(in.readUTF());
            MM.setAlbum(in.readUTF());
            MM.setSongName(in.readUTF());
            MM.setRegal_PlatzCD(in.readUTF());
            MM.setRegal_PlatzPlatte(in.readUTF());
            MM.setCDListenpreis(in.readDouble());
            MM.setPlatteListenpreis(in.readDouble());
            MM.setMp3Listenpreis(in.readDouble());
            MM.setCDEinkaufpreis(in.readDouble());
            MM.setPlatteEinkaufpreis(in.readDouble());
            MM.setMp3Einkaufpreis(in.readDouble());
            MM.setGenre(in.readUTF());
            MM.setIsMp3(in.readBoolean());
            MM.setCdCount(in.readInt());
            MM.setVinylCount(in.readInt());
            MM.setMBID(in.readUTF());
        }
    }

}
