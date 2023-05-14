package advisor.Model;

public class Playlist implements Item {
    private String name;
    private String url;

    public Playlist(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
    public void print(){
        System.out.println(name);
        System.out.println(url);
        System.out.println();
    }

}
