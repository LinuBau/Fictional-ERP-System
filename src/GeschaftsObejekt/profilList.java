package GeschaftsObejekt;

import java.util.ArrayList;
import java.util.ListIterator;

public class profilList extends ArrayList<profil> {

    private int indexofLogin;

    public int getIndexofLogin() {
        return this.indexofLogin;
    }

    public void setIndexofLogin(int indexofLogin) {
        this.indexofLogin = indexofLogin;
    }

    public int indexOfLogin(String username, int passwordHash) {
        indexofLogin = 0;
        ListIterator<profil> it = listIterator();
        while (it.hasNext()) {
            if (it.next().login(username, passwordHash)) {
                indexofLogin = it.previousIndex();
                return it.previousIndex();
            }
        }
        return -1;
    }

}
