package SaveData_ReadData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class AbstractCsvDAO {

    protected PrintWriter out;
    protected BufferedReader in;

    public AbstractCsvDAO() {
    }

    public AbstractCsvDAO(String filename, boolean writing) {
        try {
            if (writing) {
                out = new PrintWriter(filename);
            } else {
                in = new BufferedReader(new FileReader(filename));
            }
        } catch (FileNotFoundException e) {
        }
    }

    public AbstractCsvDAO(PrintWriter out, BufferedReader in) {
        this.in = in;
        this.out = out;
    }

    public void close() throws IOException {
        if (out != null) {
            out.close();
        }
        if (in != null) {
            in.close();
        }
    }

    public abstract void write(Object obj) throws IOException;

    public abstract void read(Object obj) throws IOException;
}
