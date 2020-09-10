package models;

import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    private List<User> users;

    public UserStorage() {
        this.users = new ArrayList<>();
    }


    public List<User> getUsers() {
        if(users.isEmpty()){
            users.add(new User(1, "Sviat"));
            users.add(new User(2, "Julia"));
            users.add(new User(3, "Vladislav"));
        }
        return users;
    }


}
