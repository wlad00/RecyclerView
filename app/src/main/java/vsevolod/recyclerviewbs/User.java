package vsevolod.recyclerviewbs;

/**
 * Created by Owner on 06.03.2017.
 */

public class User {
    private String name, phoneNumber;
//    private String classType;

    public User() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User(String name, String phoneNumber) {

        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public User(String name) {

        this.name = name;
    }
}
