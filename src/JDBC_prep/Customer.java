package JDBC_prep;

import java.util.ArrayList;

/**
 * Created by jason_000 on 4/25/2016.
 */
public class Customer {
    private int id;
    private String name;
    private String city;
    private String state;
    private ArrayList<String> emailAddressess;

    public Customer(int id, String name, String city, String state) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        emailAddressess = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void addEmailAddress(String emailAddress){
        emailAddressess.add(emailAddress);
    }

    public String getEmailAddress(){
        return emailAddressess.toString();
    }
}
