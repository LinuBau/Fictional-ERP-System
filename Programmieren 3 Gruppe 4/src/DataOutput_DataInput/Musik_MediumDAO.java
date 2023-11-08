package DataOutput_DataInput;

import GeschaftsObejekt.Musik_Medium;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Musik_MediumDAO extends AbstractDAO {

    public Musik_MediumDAO(DataOutputStream Ouput, DataInputStream Input) {
        super(Ouput, Input);
    }

    public Musik_MediumDAO(String filename, boolean write) {
        super(filename, write);
    }
/**
 * Implimiert das schreiben des Objetks Musik_Medium
 * @param obj muss ein Musik_Medium sein 
 * @throws IOException  muss weil wir schreiben
 */
    @Override
    public void write(Object obj) throws IOException {
        if (out != null) {
            Musik_Medium MM = (Musik_Medium) obj;
            out.writeInt(MM.getMusik_GUID());
            out.writeUTF(MM.getMusiker());
            out.writeUTF(MM.getAlbum());
            out.writeUTF(MM.getSongName());
            out.writeUTF(MM.getRegal_Platz());
            out.writeDouble(MM.getCDListenpreis());
            out.writeDouble(MM.getPlatteListenpreis());
            out.writeDouble(MM.getMp3Listenpreis());
            out.writeDouble(MM.getCDEinkaufpreis());
            out.writeDouble(MM.getPlatteEinkaufpreis());
            out.writeDouble(MM.getMp3Einkaufpreis());
            out.writeUTF(MM.getGenre());
            out.writeBoolean(MM.getIsCD());
            out.writeBoolean(MM.getIsPlatte());
            out.writeBoolean(MM.getIsMp3());
        }
    }
/**
 * Implimiert das lesen des Obejects Musik_Medium
 * @param obj muss ein Musik_Medium sein 
 * @throws IOException muss weil wir schreiben
 */
    @Override
    public void read(Object obj) throws IOException {
        if (in != null) {
            Musik_Medium MM = (Musik_Medium) obj;
            MM.setMusik_GUID(in.readInt());
            MM.setMusiker(in.readUTF());
            MM.setAlbum(in.readUTF());
            MM.setSongName(in.readUTF());
            MM.setRegal_Platz(in.readUTF());
            MM.setCDListenpreis(in.readDouble());
            MM.setPlatteListenpreis(in.readDouble());
            MM.setMp3Listenpreis(in.readDouble());
            MM.setCDEinkaufpreis(in.readDouble());
            MM.setPlatteEinkaufpreis(in.readDouble());
            MM.setMp3Einkaufpreis(in.readDouble());
            MM.setGenre(in.readUTF());
            MM.setIsCD(in.readBoolean());
            MM.setIsPlatte(in.readBoolean());
            MM.setIsMp3(in.readBoolean());
        }
    }

}
