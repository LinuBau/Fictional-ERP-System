package GeschaftsObejekt;

import java.util.ArrayList;

public class profil {
    private final String username;
    private final String passwordHash;
    private ArrayList<Integer> musikId;
    private ArrayList<Integer> pallteStückZahlList;
    private ArrayList<Integer> cdStückZahlList;
    private ArrayList<Boolean> mp3Gekauft;

    public profil(String username, String passwordHash){
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public boolean login(String user, String password){
        if(username.equals(user) && passwordHash.equals(password)){
            return true;
        }else{
            return false;
        }  
    }

}
