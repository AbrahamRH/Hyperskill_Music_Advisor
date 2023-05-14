package advisor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {

    final private String redirect_uri = "http://localhost:" + Main.serverPort;
    private final HttpClient client;
    private final String url;


    public Client(String url) {
        this.url = url;
        this.client = HttpClient.newBuilder().build();
    }

    public String getLink() {
        return Parameters.ACCESS.getValue() + "/authorize?client_id="
                + Session.parameters.get(Parameters.CLIENT_ID)
                + "&redirect_uri="
                + redirect_uri
                + "&response_type=code";
    }

    public String sendAuthRequest() {
        String client_secret = Parameters.CLIENT_SECRET.getValue();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(url + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        "grant_type=authorization_code" +
                                "&code=" + Session.parameters.get(Parameters.AUTH_CODE) +
                                "&redirect_uri=" + redirect_uri +
                                "&client_id=" + Session.parameters.get(Parameters.CLIENT_ID) +
                                "&client_secret=" + client_secret
                ))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            return request.toString();
        }
    }

    public String sendApiRequest(String path) {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + Session.parameters.get(Parameters.TOKEN))
                .uri(URI.create(path))
                .GET()
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            return request.toString();
        }
    }

}