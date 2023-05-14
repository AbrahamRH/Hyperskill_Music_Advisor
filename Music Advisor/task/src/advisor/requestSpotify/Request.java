package advisor.requestSpotify;
import advisor.Client;
import advisor.Model.Album;
import advisor.Model.Item;
import advisor.Parameters;
import advisor.Session;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.util.List;

public abstract class Request {

    protected String endpoint;
    protected Client client;

    public Request(String endpoint, Client client) {
        this.endpoint = endpoint;
        this.client = client;
    }

    protected JsonObject getResource(){
        String response = client.sendApiRequest(Session.parameters.get(Parameters.RESOURCE) + endpoint);
        return new Gson().fromJson(response,JsonObject.class).getAsJsonObject();
    }

    abstract List<? extends Item> send();
}
