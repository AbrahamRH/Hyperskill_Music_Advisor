package advisor;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.EnumMap;

public class Session {

    private HttpServer server;
    private static Session instance;
    public static EnumMap<Parameters, String> parameters = new EnumMap<>(Parameters.class);

    static {
        parameters.put(Parameters.CLIENT_ID, Parameters.CLIENT_ID.getValue());
        parameters.put(Parameters.ACCESS, Parameters.ACCESS.getValue());
        parameters.put(Parameters.RESOURCE, Parameters.RESOURCE.getValue());
        parameters.put(Parameters.CLIENT_SECRET, Parameters.CLIENT_SECRET.getValue());
        parameters.put(Parameters.PAGE, Parameters.PAGE.getValue());
    }


    private Session() {
        try {
            this.server = HttpServer.create();
            this.server.bind(new InetSocketAddress(Main.serverPort), 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Session getInstance(){
        if(instance == null){
            instance = new Session();
        }
        return  instance;
    }

    public void setTOKEN(String token) {
        parameters.put(Parameters.TOKEN, token);
    }

    public void getRequest() {
        server.createContext("/", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            if (query != null && query.contains("code")) {
                parameters.put(Parameters.AUTH_CODE,query.substring(5));
                query = "Got the code. Return back to your program.";
            } else {
                query = "Authorization code not found. Try again.";
            }
            exchange.sendResponseHeaders(200, query.length());
            exchange.getResponseBody().write(query.getBytes());
            exchange.getResponseBody().close();
        });
        server.start();
        while(parameters.get(Parameters.AUTH_CODE) == null) {
            try {Thread.sleep(10);}
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        server.stop(10);
    }
}