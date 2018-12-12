package Services;

import model.customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DBConnection;

//DONE

public class customerDB{
    
    

 
    public String registerCustomer(customer c1)
    {
            String nic = c1.getNIC() ;
            String firstname = c1.getFirstName();
            String lastName = c1.getLastname();
            String address = c1.getAddress();
            String phoneNumber = c1.getPhoneNumber();
            String age = c1.getAge();
            
 
            Connection con = null;
            PreparedStatement preparedStatement = null;
 
        try
        {
            con = DBConnection.getDBConnection();
            String query = "insert into customer(nic,firstname,lastname,address,phonenumber,age) values (?,?,?,?,?,?)"; 
            preparedStatement = con.prepareStatement(query); 
            preparedStatement.setString(1,nic);
             preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, phoneNumber);
            preparedStatement.setString(6, age);
            int i= preparedStatement.executeUpdate();
 
            if (i>0)  
                    return "SUCCESS"; 
        }
 
        catch(SQLException | ClassNotFoundException e)
        {
                //Logger.getLogger(st.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
        } 
                return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
        }
}
    