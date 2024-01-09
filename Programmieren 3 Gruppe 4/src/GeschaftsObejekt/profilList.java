package GeschaftsObejekt;

import java.util.ArrayList;

public class profilList extends ArrayList<profil>  {
    private int indexofLogin;

    public int getIndexofLogin() {
        return this.indexofLogin;
    }

    public void setIndexofLogin(int indexofLogin) {
        this.indexofLogin = indexofLogin;
    }
    public int indexOfLogin(String username, int passwordHash,profilList profilList){
        indexofLogin = 0;
        for (profil profil : profilList) {
            if(profil.login(username, passwordHash)){
                return indexofLogin;
            }
            indexofLogin++;
        }
        return -1;
    }
    
}
