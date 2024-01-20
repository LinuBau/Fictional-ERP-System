package ActionListener;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import GeschaftsObejekt.Musik;

public class ReinhörenListener implements ActionListener {
    private Musik medium;

    public void setMedium(Musik medium) {
        this.medium = medium;
    }
    private void searchWeb(Musik m){
        URL url = null;
        String urlVorlange = "https://www.youtube.com/results?search_query=";
        String[] urlEnding = m.getSongName().split(" ");
        for (String urlend : urlEnding) {
            urlVorlange = urlVorlange + "+"+ urlend;
        }
        try {
			url = new URL(urlVorlange);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(url.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        searchWeb(medium);
    }
    
}
