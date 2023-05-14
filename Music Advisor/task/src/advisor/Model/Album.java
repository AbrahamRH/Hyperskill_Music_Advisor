package advisor.Model;

import java.util.ArrayList;
import java.util.List;

public class Album implements Item {
    private final String name;
    private final List<String> artists;
    private final String url;

    public Album(String name, ArrayList<String> artists, String url) {
        this.name = name;
        this.artists = artists;
        this.url = url;
    }

    public void print(){
        System.out.println(name);
        System.out.println(artists);
        System.out.println(url);
        System.out.println();
    }

    public String getName() {
        return name;
    }


    public List<String> getArtists() {
        return artists;
    }

    public String getUrl() {
        return url;
    }

}
