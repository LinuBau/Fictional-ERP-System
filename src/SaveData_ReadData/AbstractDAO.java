package SaveData_ReadData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class AbstractDAO {

    protected DataInputStream in;
    protected DataOutputStream out;

    public AbstractDAO(String Filename, boolean write) {
        try {
            if (write == true) {
                out = new DataOutputStream(new FileOutputStream(Filename));
            } else {
                in = new DataInputStream(new FileInputStream(Filename));
            }
        } catch (IOException e) {

        }
    }

    public AbstractDAO(DataOutputStream Ouput, DataInputStream Input) {
        this.out = Ouput;
        this.in = Input;
    }

    public void close() {
        try {
            if (this.out != null) {
                out.close();
            }
            if (this.in != null) {
                in.close();
            }
        } catch (Exception e) {
        }

    }

    public abstract void write(Object obj) throws IOException;

    public abstract void read(Object obj) throws IOException;

}
