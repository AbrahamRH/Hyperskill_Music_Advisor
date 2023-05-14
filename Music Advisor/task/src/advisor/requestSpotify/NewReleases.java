package advisor.requestSpotify;

import advisor.Client;
import advisor.Model.Album;
import advisor.Model.Item;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class NewReleases extends Request {

    private List<Album> albums = new ArrayList<>();

    public NewReleases(String endpoint, Client client) {
        super(endpoint, client);
    }

    @Override
    public List<? extends Item> send() {
        JsonObject releases = this.getResource();
        JsonArray albumsArray = releases.getAsJsonObject("albums").getAsJsonArray("items");
        for (JsonElement album : albumsArray) {
            ArrayList<String> artistsNames = new ArrayList<>();
            JsonObject item = album.getAsJsonObject();
            JsonArray artists = item.getAsJsonArray("artists");
            for (JsonElement artist : artists) {
                artistsNames.add(artist.getAsJsonObject().get("name").getAsString());
            }
            this.albums.add(new Album(album.getAsJsonObject().get("name").getAsString(),
                    artistsNames, album.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").getAsString()));
        }
        return albums;
    }
}
