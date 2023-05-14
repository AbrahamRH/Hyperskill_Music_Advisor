package advisor;

import java.util.List;

public class Main {
    public static int serverPort = (int)(Math.random()*20) +8000;
    //public static int serverPort = 8080;

    public static void main(String[] args) {
        getArgs(args);
        MusicAdvisor advisor = new MusicAdvisor();
        advisor.initApp();
    }

    private static void getArgs(String[] args){
        Session session = Session.getInstance();
        List<String> arguments = List.of(args);
        String resource = "resource";
        String access = "access";
        String page = "page";
        String prevArg = "";

        for(String arg : arguments) {
            if (prevArg.length() == 0) {
                prevArg = arg;
            } else if (prevArg.contains(resource)) {
                resource = arg;
                prevArg = "";
            } else if( prevArg.contains(access)){
                access = arg;
                prevArg= "";
            } else if( prevArg.contains(page)) {
                page = arg;
                prevArg= "";
            }
        }
        Session.parameters.put(Parameters.ACCESS, access);
        Session.parameters.put(Parameters.RESOURCE, resource);
        Session.parameters.put(Parameters.PAGE, page);
    }
}