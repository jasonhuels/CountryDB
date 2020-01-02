package JDBC_prep;

/**
 * @author Jason Huels
 * @version 4/25/2016.
 */

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("FiredUp customers: ");
        FiredUpDB firedUp = new FiredUpDB();
        firedUp.readCustomers();
        ArrayList<Customer> customers =	firedUp.readCustomers();
        for(Customer cust : customers){
            System.out.println("ID: " + cust.getId() + ", Name: " + cust.getName() + ", City: " + cust.getCity()
                    + ", State: "	+ cust.getState() + ", Email Address: " + cust.getEmailAddress());}
    }
}
