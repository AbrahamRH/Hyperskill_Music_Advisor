package advisor;

import advisor.Model.Album;
import advisor.Model.Category;
import advisor.Model.Item;
import advisor.Model.Playlist;
import advisor.View.Menu;
import advisor.requestSpotify.Categories;
import advisor.requestSpotify.Featured;
import advisor.requestSpotify.NewReleases;
import advisor.requestSpotify.Playlists;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MusicAdvisor {
    private boolean authSuccess = false;
    private final Session session;
    private final Client client;
    private int page = 0;
    private int totalPages;
    private static List<Item> items;

    Boolean isActive = false;

    public MusicAdvisor() {
        this.session = Session.getInstance();
        this.client = new Client(Session.parameters.get(Parameters.ACCESS));
    }

    public void initApp() {
        boolean continuar = true;
        while (continuar) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] option = input.split(" ");
            if (authSuccess || option[0].equals("exit") || option[0].equals("auth")) {
                continuar = printOption(option);
            } else {
                System.out.println("Please, provide access for application.");
            }
        }
    }

    private boolean auth() {
        System.out.println("use this link to request the access code:");
        System.out.println(client.getLink());
        session.getRequest();
        System.out.println("waiting for code...");
        System.out.println("code received");
        System.out.println("Making http request for access_token...");
        String response = client.sendAuthRequest();
        JsonObject tokenJson = JsonParser.parseString(response).getAsJsonObject();
        session.setTOKEN(tokenJson.get("access_token").getAsString());
        response = client.sendApiRequest(Session.parameters.get(Parameters.RESOURCE));
        System.out.println("Success!");
        return true;
    }

    private void printPage() {
        int pageSize = Integer.parseInt(Session.parameters.get(Parameters.PAGE));
        totalPages = items.size() / pageSize;
        int offset = page * pageSize;
        for (int i = 0; i < pageSize; i++) {
            int index = i + offset;
            if (index >= items.size()) {
                break;
            }
            items.get(index).print();
        }
        System.out.println("---PAGE " + (page + 1) + " OF " + totalPages + "---");
    }


    private boolean printOption(String[] option) {
        String command = option[0];
        StringBuilder categoryBuilder = new StringBuilder();
        String categoryName = null;

        if (option.length != 1) {
            for (int i = 1; i < option.length; i++) {
                categoryBuilder.append(option[i].toLowerCase());
            }
            categoryName = categoryBuilder.toString();
        }

        switch (command) {
            case "auth" -> authSuccess = auth();
            case "new" -> {
                NewReleases releases = new NewReleases("/v1/browse/new-releases", client);
                items = (List<Item>) releases.send();
                page = 0;
                printPage();
            }
            case "featured" -> {
                Featured respond = new Featured("/v1/browse/featured-playlists", client);
                items = (ArrayList<Item>) respond.send();
                page = 0;
                printPage();
            }
            case "categories" -> {
                Categories respond = new Categories("/v1/browse/categories", client);
                items = (ArrayList<Item>) respond.send();
                page = 0;
                printPage();
            }
            case "playlists" -> {
                String endpoint = null;
                if (categoryName.contains("party")) {
                    endpoint = "/v1/browse/categories/party/playlists";
                } else {
                    endpoint = "/v1/browse/categories/" + categoryName + "/playlists";
                }
                Playlists respond = new Playlists(endpoint, client);
                page = 0;
                items = (ArrayList<Item>) respond.send();

                if (items == null) {
                    System.out.println("Unknown category name.");
                    break;
                }
                printPage();
            }
            case "exit" -> {
                if (isActive) {
                    isActive = false;
                    return true;
                } else {
                    return false;
                }
            }
            case "next" -> {
                isActive = true;
                if (page == totalPages - 1) {
                    System.out.println("No more pages.");
                } else {
                    page++;
                    printPage();
                }
            }
            case "prev" -> {
                isActive = true;
                if (page == 0) {
                    System.out.println("No more pages.");
                } else {
                    page--;
                    printPage();
                }
            }
            default -> {
            }
        }
        return true;
    }
}