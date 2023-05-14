package advisor.requestSpotify;
import advisor.Client;
import advisor.Model.Category;
import advisor.Model.Item;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Categories extends Request{

    private final List<Category> categories = new ArrayList<>();
    public Categories(String endpoint, Client client) {
        super(endpoint, client);
    }

    @Override
    public List<? extends Item> send() {
        JsonObject categoriesJson = this.getResource();
        JsonArray categoriesArray = categoriesJson.getAsJsonObject("categories").getAsJsonArray("items");

        for(JsonElement category : categoriesArray){
            String name = category.getAsJsonObject().get("name").getAsString();
            categories.add(new Category(name));
        }

        return categories;
    }


}
