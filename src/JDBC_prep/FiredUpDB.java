package JDBC_prep;

/**
 * Created by jason_000 on 4/25/2016.
 */
import java.sql.*;
import java.util.ArrayList;

public class FiredUpDB {
    private static final String FIREDUP_URL = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/FiredUp";
    private static final String USERNAME = "275student";
    private static final String PASSWORD = "275student";
    private static final String CUSTOMER_SQL = "SELECT CustomerID, Name, City, StateProvince FROM CUSTOMER";
    private final static String EMAIL_SQL = "SELECT EmailAddress FROM EMAIL WHERE FK_CustomerID = ?";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(FIREDUP_URL,USERNAME,PASSWORD);
    }

    public ArrayList<Customer> readCustomerBasics(){
        ArrayList<Customer > customers = new ArrayList<>();
        try (
                Connection conn = getConnection();
                //additional resources will go here
                PreparedStatement stmt = conn.prepareStatement(CUSTOMER_SQL);
                ResultSet rs = stmt.executeQuery()
         ) {
                //code	utilizing resources	will go here
                while(rs.next()){
                    customers.add(new Customer(rs.getInt("CustomerID"), rs.getString("Name"),
                            rs.getString("City"), rs.getString("StateProvince")));
                }
            } catch (SQLException e){
                e.printStackTrace();
         }
        return customers;
    }

    public ArrayList<Customer> readCustomers(){
        ArrayList<Customer> customers = readCustomerBasics();
        readEmailAddresses(customers);
        return customers;
    }

    private void readEmailAddresses(ArrayList<Customer> customers){
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(EMAIL_SQL)
        ) {
            for(Customer cust : customers){
                stmt.setInt(1,cust.getId());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    cust.addEmailAddress(rs.getString("EmailAddress"));
                }
            }
        }	catch(SQLException e){
            e.printStackTrace();
        }
    }
}
