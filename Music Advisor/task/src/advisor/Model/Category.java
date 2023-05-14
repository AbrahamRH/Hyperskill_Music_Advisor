package advisor.Model;

public class Category implements Item{

    private String name;

    public Category(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void print(){
        System.out.println(name);
    }


}
