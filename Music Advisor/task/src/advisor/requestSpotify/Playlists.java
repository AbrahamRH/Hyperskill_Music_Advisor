package advisor.requestSpotify;

import advisor.Client;
import advisor.Model.Item;
import advisor.Model.Playlist;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Playlists extends Request {

    private final List<Playlist> playlists = new ArrayList<>();

    public Playlists(String endpoint, Client client) {
        super(endpoint, client);
    }

    @Override
    public List<? extends Item> send() {
        JsonObject categoryJson = this.getResource();

        try {
            JsonObject error = categoryJson.get("error").getAsJsonObject();
            return null;
        } catch (Exception ignored) {}

        JsonArray playlistsArray = categoryJson.getAsJsonObject("playlists").getAsJsonArray("items");
        for (JsonElement playlist : playlistsArray) {
            JsonObject playlistJO = playlist.getAsJsonObject();
            String name = playlistJO.get("name").getAsString();
            String url = playlistJO.getAsJsonObject("external_urls").get("spotify").getAsString();
            this.playlists.add(new Playlist(name, url));
        }
        return playlists;
    }
}
