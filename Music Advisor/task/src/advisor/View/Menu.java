package advisor.View;

import java.util.Hashtable;

public class Menu {

    Hashtable<String, ActionStrategy> actions;
    public Menu(){
        actions = new Hashtable<>();
    }

    public void addAction(String id, ActionStrategy action) {
        actions.put(id,action);
    }

    public static class Builder {
        private final Menu menu;

        public Builder() {
            menu = new Menu();
        }

        public Builder addAction(String id,ActionStrategy action) {
            menu.addAction(id, action);
            return this;
        }

        public Menu build() {
            return this.menu;
        }

    }

    public void execute(String id) {
        if(actions.containsKey(id)) {
            actions.get(id).action();
        } else {
            System.out.println("Invalid command.");
        }
    }

}

