package advisor.requestSpotify;

import advisor.Client;
import advisor.Model.Item;
import advisor.Model.Playlist;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Featured extends Request {

    private List<Playlist> playlists = new ArrayList<>();

    public Featured(String endpoint, Client client) {
        super(endpoint, client);
    }

    @Override
    public List<? extends Item> send() {
        JsonObject featured = this.getResource();
        JsonArray featuredArray = featured.getAsJsonObject("playlists").getAsJsonArray("items");
        for (JsonElement playlist : featuredArray) {
            JsonObject playlistJO = playlist.getAsJsonObject();
            String name = playlistJO.get("name").getAsString();
            String url = playlistJO.getAsJsonObject("external_urls").get("spotify").getAsString();
            this.playlists.add(new Playlist(name, url));
        }
        return playlists;
    }
}
